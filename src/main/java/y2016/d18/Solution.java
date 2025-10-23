package y2016.d18;

import java.util.Arrays;

public class Solution {
	private static String[] patterns = new String[] {
			"^^.",
			".^^",
			"^..",
			"..^",
	};

	public static int count_safe_tiles(String data, int rows) {
		char[] row = new char[data.length() + 2];
		row[0] = '.';
		row[row.length - 1] = '.';
		for (int i = 0; i < data.length(); i++) {
			row[i + 1] = data.charAt(i);
		}

		int count = 0;

		do {
			char[] next_row = new char[row.length];
			Arrays.fill(next_row, '.');

			for (int i = 1; i < row.length - 1; i++) {
				if (row[i] == '.') {
					count++;
				}

				StringBuilder builder = new StringBuilder();
				builder.append(row[i - 1]);
				builder.append(row[i]);
				builder.append(row[i + 1]);
				String pattern = builder.toString();

				for (int p = 0; p < patterns.length; p++) {
					if (patterns[p].equals(pattern)) {
						next_row[i] = '^';
					}
				}
			}

			row = next_row;
		} while (--rows > 0);
		return count;
	}

	public static void run(String input) {
		System.out.println(count_safe_tiles(input, 40));
		System.out.println(count_safe_tiles(input, 400000));
	}
}
