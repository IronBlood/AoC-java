package y2016.d06;

import java.util.Arrays;

public class Solution {
	static char get_message_by_column(String[] lines, int idx, int part) {
		var freq = new int[26];
		Arrays.fill(freq, 0);
		for (var line : lines) {
			freq[(int) line.charAt(idx) - 97]++;
		}

		int max = freq[0], max_idx = 0, min = freq[0], min_idx = 0;
		for (int i = 0; i < 26; i++) {
			if (max < freq[i]) {
				max = freq[i];
				max_idx = i;
			}
			if (min > freq[i]) {
				min = freq[i];
				min_idx = i;
			}
		}

		return (char) (97 + (part == 1 ? max_idx : min_idx));
	}

	static String get_message(String data, int part) {
		var lines = data.split("\\R");
		StringBuilder sb = new StringBuilder();
		for (int i = 0, len = lines[0].length(); i < len; i++) {
			sb.append(get_message_by_column(lines, i, part));
		}
		return sb.toString();
	}

	public static void run(String input) {
		System.out.println(get_message(input, 1));
		System.out.println(get_message(input, 2));
	}
}
