package y2016.d08;

import java.util.Arrays;

public class Solution {
	static void flip_row(char[][] grid, int idx, int start, int end) {
		while (start < end) {
			var tmp = grid[idx][start];
			grid[idx][start] = grid[idx][end];
			grid[idx][end] = tmp;
			start++;
			end--;
		}
	}

	static void flip_col(char[][] grid, int idx, int start, int end) {
		while (start < end) {
			var tmp = grid[start][idx];
			grid[start][idx] = grid[end][idx];
			grid[end][idx] = tmp;
			start++;
			end--;
		}
	}

	static void shift_row(char[][] grid, int idx, int offset) {
		flip_row(grid, idx, 0, grid[0].length - 1 - offset);
		flip_row(grid, idx, grid[0].length - offset, grid[0].length - 1);
		flip_row(grid, idx, 0, grid[0].length - 1);
	}

	static void shift_col(char[][] grid, int idx, int offset) {
		flip_col(grid, idx, 0, grid.length - 1 - offset);
		flip_col(grid, idx, grid.length - offset, grid.length - 1);
		flip_col(grid, idx, 0, grid.length - 1);
	}

	public static void exec(char[][] grid, String cmd) {
		if (cmd.startsWith("rect")) {
			var cols_and_rows = cmd.split(" ")[1].split("x");
			int cols = Integer.parseInt(cols_and_rows[0]);
			int rows = Integer.parseInt(cols_and_rows[1]);
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					grid[i][j] = '#';
				}
			}
		} else {
			var arr = cmd.split(" ");
			int idx = Integer.parseInt(arr[2].split("=")[1]);
			int offset = Integer.parseInt(arr[4]);
			if ("row".equals(arr[1])) {
				shift_row(grid, idx, offset % grid[0].length);
			} else {
				shift_col(grid, idx, offset % grid.length);
			}
		}
	}

	static int count_pixels_after_execution(char[][] screen, String data) {
		for (var cmd : data.split("\\R")) {
			exec(screen, cmd);
		}

		int sum = 0;
		for (var row : screen) {
			System.out.println(new String(row));
			for (var c : row) {
				if (c == '#') {
					sum++;
				}
			}
		}
		return sum;
	}

	public static void run(String input) {
		char[][] screen = new char[6][50];
		for (var row : screen) {
			Arrays.fill(row, '.');
		}
		System.out.println(count_pixels_after_execution(screen, input));
	}
}
