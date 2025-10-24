package y2016.d25;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;

public class Solution {
	private static int get_idx(String c) {
		char ch = c.charAt(0);
		return ch >= 'a' ? (ch - 'a') : -1;
	}

	private static boolean is_multiple(String[][] instructions, int pc) {
		if (pc - 1 < 0) {
			return false;
		}
		if (pc + 4 > instructions.length) {
			return false;
		}
		if (false
				|| !instructions[pc - 1][0].equals("cpy")
				|| !instructions[pc + 1][0].equals("dec")
				|| !instructions[pc + 2][0].equals("jnz")
				|| !instructions[pc + 3][0].equals("dec")
				|| !instructions[pc + 4][0].equals("jnz")) {
			return false;
		}
		return true;
	}

	private static void exec(Config cfg, Consumer<Integer> callback, int[] registers) {
		var lines = cfg.data.split("\n");
		String[][] instructions = new String[lines.length][];
		for (int i = 0; i < lines.length; i++) {
			instructions[i] = lines[i].split(" ");
		}

		for (int i = 0; i < instructions.length;) {
			if (cfg.should_break) {
				break;
			}
			var jump = false;
			var op = get_idx(instructions[i][1]);
			var target_idx = 0;

			switch (instructions[i][0]) {
				case "cpy":
					target_idx = get_idx(instructions[i][2]);
					if (target_idx != -1) {
						registers[target_idx] = op == -1 ? Integer.parseInt(instructions[i][1]) : registers[op];
					}
					break;
				case "inc":
					if (op != -1) {
						if (is_multiple(instructions, i)) {
							var src = instructions[i - 1][1];
							var dst = instructions[i - 1][2];
							String dec1_op = instructions[i + 1][1], dec2_op = instructions[i + 3][1];
							String jnz1_op = instructions[i + 2][1], jnz1_off = instructions[i + 2][2],
									jnz2_op = instructions[i + 4][1], jnz2_off = instructions[i + 4][2];
							if (!dst.equals(dec1_op) || !dec1_op.equals(jnz1_op) || !dec2_op.equals(jnz2_op)
									|| !jnz1_off.equals("-2")
									|| !jnz2_off.equals("-5")) {
								System.err.println("invalid");
							} else {
								int b = get_idx(src), c = get_idx(dec1_op), d = get_idx(dec2_op);
								registers[op] += (b == -1 ? Integer.parseInt(src) : registers[b]) * registers[d];
								registers[c] = 0;
								registers[d] = 0;
								i += 5;
								jump = true;
							}
						} else {
							registers[op]++;
						}
					}
					break;
				case "dec":
					if (op != -1)
						registers[op]--;
					break;
				case "jnz":
					if ((op == -1 ? Integer.parseInt(instructions[i][1]) : registers[op]) != 0) {
						target_idx = get_idx(instructions[i][2]);
						var offset = target_idx == -1 ? Integer.parseInt(instructions[i][2]) : registers[target_idx];
						if ((i += offset) >= 0 && i < instructions.length) {
							jump = true;
						} else {
							i -= offset;
						}
					}
					break;
				case "tgl":
					target_idx = i + (op == -1 ? Integer.parseInt(instructions[i][1]) : registers[op]);
					if (target_idx >= 0 && target_idx < instructions.length) {
						var cmd = instructions[target_idx];
						if (cmd.length == 2) {
							cmd[0] = cmd[0].equals("inc") ? "dec" : "inc";
						} else {
							cmd[0] = cmd[0].equals("jnz") ? "cpy" : "jnz";
						}
					}
					break;
				case "out":
					callback.accept(op == -1 ? Integer.parseInt(instructions[i][1]) : registers[op]);
					break;
			}
			if (!jump) {
				i++;
			}
		}

	}

	private static class Config {
		public String data;
		public boolean should_break;

		Config(String data) {
			this.data = data;
			this.should_break = false;
		}
	}

	public static int lowest_positive_integer(String data) {
		int[] registers = new int[] { 0, 0, 0, 0 };
		int i = 0;

		while (true) {
			final ArrayList<Integer> buf = new ArrayList<>();
			final int[] j = new int[] { 0 };
			Arrays.fill(registers, 0);
			registers[0] = i;
			final Config cfg = new Config(data);
			exec(cfg, x -> {
				buf.add(x);
				j[0]++;
				if (j[0] > 10 || x < 0 || x > 1) {
					cfg.should_break = true;
				}
			}, registers);

			if (buf.size() < 10) {
				continue;
			}

			var found = true;
			var tick = 0;
			for (int k = 0; k < buf.size(); k++) {
				if (buf.get(k) != tick) {
					found = false;
					break;
				}
				tick ^= 1;
			}
			if (found) {
				break;
			}

			i++;
		}

		return i;
	}

	public static void run(String input) {
		System.out.println(lowest_positive_integer(input));
	}
}
