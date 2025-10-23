package y2016.d11;

import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;

public class Solution {
	private static final int FLOORS = 4;

	private static class Utils {
		public static <T> void backtracking_up_to_k(List<List<T>> res, Deque<T> stack, int idx, List<T> candidates,
				int k) {
			if (stack.size() > k) {
				return;
			}

			if (stack.size() > 0) {
				res.add(new ArrayList(stack));
			}

			for (int i = idx + 1; i < candidates.size(); i++) {
				stack.push(candidates.get(i));
				backtracking_up_to_k(res, stack, i, candidates, k);
				stack.pop();
			}
		}

		public static int get_id(int num) {
			int id = 0;
			while (num > 1) {
				num >>= 1;
				id++;
			}
			return id;
		}

		public static boolean is_valid_floor(List<Integer> floor) {
			int total = floor.size(), m_count = 0;
			for (var x : floor) {
				if (x < 0) {
					m_count++;
				}
			}
			int g_count = total - m_count;

			if (m_count == 0 || g_count == 0) {
				return true;
			}

			for (var x : floor) {
				if (x > 0) {
					continue;
				}

				int target = ~x;
				if (!floor.contains(target)) {
					return false;
				}
			}
			return true;
		}
	}

	private static class SimulatorState {
		final List<Integer>[] floors;
		int elevator;
		int moves;

		@SuppressWarnings("unchecked")
		public SimulatorState() {
			this.floors = (ArrayList<Integer>[]) new ArrayList[FLOORS];
			for (int i = 0; i < FLOORS; i++) {
				this.floors[i] = new ArrayList<>();
			}
			this.elevator = 0;
			this.moves = 0;
		}

		public SimulatorState(List<Integer>[] floors, int elevator, int moves) {
			this.floors = floors;
			this.elevator = elevator;
			this.moves = moves;
		}

		public SimulatorState dup() {
			List<Integer>[] floors = new ArrayList[FLOORS];
			for (int i = 0; i < FLOORS; i++) {
				floors[i] = new ArrayList<>(this.floors[i]);
			}
			return new SimulatorState(floors, this.elevator, this.moves);
		}

		public int serialize_key() {
			int res = 0;
			for (int i = 0; i < FLOORS; i++) {
				int g_count = 0, m_count = 0;
				for (Integer item : this.floors[i]) {
					if (item > 0) {
						m_count++;
					} else {
						g_count++;
					}
				}

				res |= ((m_count << 3) | g_count) << (6 * i);
			}

			return (res << 2) | this.elevator;
		}

		public boolean is_finished() {
			for (int i = 0; i < FLOORS - 1; i++) {
				if (this.floors[i].size() > 0) {
					return false;
				}
			}
			return true;
		}

		public boolean is_valid() {
			for (int i = 0; i < FLOORS; i++) {
				if (!Utils.is_valid_floor(this.floors[i])) {
					return false;
				}
			}
			return true;
		}

		public List<SimulatorState> gen_next_states() {
			List<SimulatorState> candidates = new ArrayList<>();

			List<Integer> curr_floor = this.floors[this.elevator];
			List<List<Integer>> possible_combos = new ArrayList<>();
			Utils.backtracking_up_to_k(possible_combos, new ArrayDeque<>(), -1, curr_floor, 2);

			List<Integer> dest = new ArrayList<>();
			if (this.elevator < FLOORS - 1) {
				dest.add(this.elevator + 1);
			}
			if (this.elevator > 0) {
				dest.add(this.elevator - 1);
			}

			for (var d : dest) {
				for (var combo : possible_combos) {
					var next_state = this.dup();
					next_state.elevator = d;
					next_state.moves++;

					var target_floor = next_state.floors[d];
					var source_floor = next_state.floors[this.elevator];

					for (var c : combo) {
						target_floor.add(c);
						// TODO: combo was generated from source_floor, should be safe?
						source_floor.remove(c);
					}

					if (next_state.is_valid()) {
						candidates.add(next_state);
					}
				}
			}

			return candidates;
		}

		// TODO maybe not accurate, but enough for this puzzle
		public int score() {
			int res = -this.moves;
			for (int i = 0; i < FLOORS; i++) {
				res += this.floors[i].size() * i;
			}
			return res;
		}
	}

	public static int minimum_moves(String data, int part) {
		var lines = data.split("\n");
		if (part == 2) {
			lines[0] += " elerium generator, elerium-compatible microchip, dilithium generator, dilithium-compatible microchip";
		}

		int id = 0;
		Map<String, Integer> id_map = new HashMap<>();
		Pattern re_g = Pattern.compile("(\\w+) generator");
		Pattern re_m = Pattern.compile("(\\w+)-compatible microchip");

		List<Integer>[] floors = new ArrayList[FLOORS];
		for (int i = 0; i < FLOORS; i++) {
			var line = lines[i];
			floors[i] = new ArrayList<>();

			Matcher mg = re_g.matcher(line);
			while (mg.find()) {
				String name = mg.group(1);
				id_map.putIfAbsent(name, id++);
				int gen_id = id_map.get(name);
				floors[i].add(1 << gen_id);
			}

			Matcher mm = re_m.matcher(line);
			while (mm.find()) {
				String name = mm.group(1);
				id_map.putIfAbsent(name, id++);
				int chip_id = id_map.get(name);
				floors[i].add(~(1 << chip_id));
			}
		}

		SimulatorState state = new SimulatorState(floors, 0, 0);
		Set<Integer> visited = new HashSet<>();
		PriorityQueue<SimulatorState> queue = new PriorityQueue<>((a, b) -> b.score() - a.score());
		queue.offer(state);

		while (queue.size() > 0) {
			state = queue.poll();
			if (state.is_finished()) {
				return state.moves;
			}

			var key = state.serialize_key();
			if (visited.contains(key)) {
				continue;
			}
			visited.add(key);

			for (var s : state.gen_next_states()) {
				queue.offer(s);
			}
		}

		// unreachable
		return -1;
	}

	public static void run(String input) {
		System.out.println(minimum_moves(input, 1));
		System.out.println(minimum_moves(input, 2));
	}
}
