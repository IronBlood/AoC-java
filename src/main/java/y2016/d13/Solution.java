package y2016.d13;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

public class Solution {
	private static String[][] make_grid(int num, int height, int width) {
		String[][] grid = new String[height][width];
		for (int i = 0; i < height; i++) {
			Arrays.fill(grid[i], ".");
			for (int j = 0; j < width; j++) {
				int sum = i * i + 3 * i + 2 * i * j + j + j * j + num;
				int count = 0;
				while (sum > 0) {
					count += (sum & 1);
					sum >>= 1;
				}
				if ((count & 1) == 1) {
					grid[i][j] = "#";
				}
			}
		}
		return grid;
	}

	private static <T> boolean in_grid(T[][] grid, int x, int y) {
		return x >= 0 && y >= 0 && x < grid.length && y < grid[0].length;
	}

	public static int shortest_steps(String str, int[] target) {
		int num = Integer.parseInt(str);
		String[][] grid = make_grid(num, 4 * target[0], 4 * target[1]);
		var dirs = new int[] { 0, 1, 0, -1, 0 };

		var queue = new ArrayDeque<int[]>();
		queue.offer(new int[] { 1, 1, 0 });
		while (queue.size() > 0) {
			var el = queue.poll();
			var x = el[0];
			var y = el[1];
			var move = el[2];

			if (!in_grid(grid, x, y) || !".".equals(grid[x][y])) {
				continue;
			}

			if (x == target[0] && y == target[1]) {
				return move;
			}

			grid[x][y] = "o";
			for (int i = 0; i < 4; i++) {
				queue.offer(new int[] {
						x + dirs[i],
						y + dirs[i + 1],
						move + 1,
				});
			}
		}
		throw new Error("Unreachable");
	}

	public static int count_locations(String str, int move) {
		int num = Integer.parseInt(str);
		int size = Math.max(move, 3) * 4;
		String[][] grid = make_grid(num, size, size);

		var dirs = new int[] { 0, 1, 0, -1, 0 };

		var queue = new ArrayList<int[]>();
		queue.add(new int[] { 1, 1 });
		int count = 0;
		while (move-- >= 0) {
			var next_queue = new ArrayList<int[]>();
			for (var el : queue) {
				var x = el[0];
				var y = el[1];
				if (!in_grid(grid, x, y) || !(".".equals(grid[x][y]))) {
					continue;
				}

				grid[x][y] = "o";
				count++;

				for (int i = 0; i < 4; i++) {
					next_queue.add(new int[] {
							x + dirs[i],
							y + dirs[i + 1],
					});
				}
			}

			queue = next_queue;
		}

		return count;
	}

	public static void run(String input) {
		System.out.println(shortest_steps(input, new int[] { 31, 39 }));
		System.out.println(count_locations(input, 50));
	}
}
