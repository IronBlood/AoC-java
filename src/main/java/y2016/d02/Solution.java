package y2016.d02;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Solution {
	private static String[][] get_numpad(String format) {
		String[] lines = format.split("\\R");
		return Arrays.stream(lines)
				.map(line -> line.split(""))
				.toArray(String[][]::new);
	}

	public static String get_code(String data) {
		int[] pos = { 1, 1 };
		String[][] keypad = get_numpad("""
				123
				456
				789\
				""");
		return Arrays.stream(data.split("\\R"))
				.map(line -> {
					int n = line.length();
					for (int i = 0; i < n; i++) {
						switch (line.charAt(i)) {
							// Java 14+
							case 'U' -> pos[0] = Math.max(pos[0] - 1, 0);
							case 'D' -> pos[0] = Math.min(pos[0] + 1, 2);
							case 'L' -> pos[1] = Math.max(pos[1] - 1, 0);
							case 'R' -> pos[1] = Math.min(pos[1] + 1, 2);
						}
					}
					return keypad[pos[0]][pos[1]];
				})
				.collect(Collectors.joining());
	}

	public static String get_code2(String data) {
		int x = 2, y = 0;
		String[][] keypad = get_numpad("""
				..1..
				.234.
				56789
				.ABC.
				..D..\
				""");

		StringBuilder sb = new StringBuilder();
		for (String line : data.split("\\R")) {
			int nx = x, ny = y;
			for (int i = 0, n = line.length(); i < n; i++) {
				switch (line.charAt(i)) {
					case 'U' -> nx = Math.max(nx - 1, 0);
					case 'D' -> nx = Math.min(nx + 1, 4);
					case 'L' -> ny = Math.max(ny - 1, 0);
					case 'R' -> ny = Math.min(ny + 1, 4);
				}
				if (!keypad[nx][ny].equals(".")) {
					x = nx;
					y = ny;
				} else {
					nx = x;
					ny = y;
				}
			}
			sb.append(keypad[x][y]);
		}

		return sb.toString();
	}

	public static void run(String input) {
		System.out.println(get_code(input));
		System.out.println(get_code2(input));
	}
}
