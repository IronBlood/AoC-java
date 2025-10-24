package y2016.d24;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;

public class Solution {
	private static int[] DIRECTIONS = new int[] { 0, -1, 0, 1, 0 };

	private static boolean in_grid(char[][] grid, int x, int y) {
		return x >= 0 && y >= 0 && x < grid.length && y < grid[0].length;
	}

	private static HashMap<Character, Integer> bfs_min_distance(char[][] grid, int[] pos) {
		var M = grid.length;
		var N = grid[0].length;
		int[] visited = new int[M * N];
		HashMap<Character, Integer> adj = new HashMap<>();
		ArrayDeque<int[]> queue = new ArrayDeque<>();
		queue.offer(pos);
		int move = 0;
		int[] entry;

		while (queue.size() > 0) {
			ArrayDeque<int[]> next_queue = new ArrayDeque<>();
			while ((entry = queue.poll()) != null) {
				var x = entry[0];
				var y = entry[1];

				if (!in_grid(grid, x, y) || grid[x][y] == '#' || visited[x * N + y] == 1) {
					continue;
				}
				visited[x * N + y] = 1;

				if (grid[x][y] != '.' && !(x == pos[0] && y == pos[1])) {
					adj.put(grid[x][y], move);
				}

				for (int i = 0; i < 4; i++) {
					next_queue.offer(new int[] {
							x + DIRECTIONS[i],
							y + DIRECTIONS[i + 1]
					});
				}
			}
			move++;
			queue = next_queue;
		}

		return adj;
	}

	private static int min = Integer.MAX_VALUE;

	private static void dfs(char curr_node, HashSet<Character> visited, int curr_steps, int nodes_to_visit, int part,
			HashMap<Character, HashMap<Character, Integer>> adjs) {
		visited.add(curr_node);
		if (visited.size() == nodes_to_visit) {
			min = part == 1
					? Math.min(min, curr_steps)
					: Math.min(min, curr_steps + adjs.get(curr_node).get('0'));
			return;
		}

		var adj = adjs.get(curr_node);
		for (var entry : adj.entrySet()) {
			var next_node = entry.getKey();
			var dist = entry.getValue();
			if (!visited.contains(next_node)) {
				dfs(next_node, new HashSet<>(visited), curr_steps + dist, nodes_to_visit, part, adjs);
			}
		}
		visited.remove(curr_node);
	}

	public static int fewest_steps(String data, int part) {
		min = Integer.MAX_VALUE;
		var lines = data.split("\n");
		char[][] grid = new char[lines.length][];
		for (int i = 0; i < lines.length; i++) {
			grid[i] = lines[i].toCharArray();
		}

		HashMap<Character, HashMap<Character, Integer>> adjs = new HashMap<>();
		int nodes_to_visit = 0;

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				var cell = grid[i][j];
				if (cell != '#' && cell != '.') {
					var pos = new int[] { i, j };
					adjs.put(cell, bfs_min_distance(grid, pos));
					nodes_to_visit++;
				}
			}
		}

		dfs('0', new HashSet<>(), 0, nodes_to_visit, part, adjs);
		return min;
	}

	public static void run(String input) {
		System.out.println(fewest_steps(input, 1));
		System.out.println(fewest_steps(input, 2));
	}
}
