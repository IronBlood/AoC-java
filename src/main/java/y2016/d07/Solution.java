package y2016.d07;

import java.util.ArrayList;
import java.util.List;

public class Solution {
	static boolean match_abba(String str, int start) {
		return match_abba(str, start, str.length());
	}

	static boolean match_abba(String str, int start, int end) {
		for (int i = start; i < end - 3; i++) {
			char a = str.charAt(i), b = str.charAt(i + 1), c = str.charAt(i + 2), d = str.charAt(i + 3);
			if (a == d && b == c && a != b)
				return true;
		}
		return false;
	}

	public static int support_tls(String str) {
		boolean outside_abba = false;
		int idx = 0, idx_lsb = -1, idx_rsb;
		while ((idx_lsb = str.indexOf('[', idx)) != -1) {
			idx_rsb = str.indexOf(']', idx_lsb + 1);
			if (!outside_abba && match_abba(str, idx, idx_lsb)) {
				outside_abba = true;
			}
			if (match_abba(str, idx_lsb + 1, idx_rsb)) {
				return 0;
			}
			idx = idx_rsb + 1;
		}
		return (outside_abba || match_abba(str, idx)) ? 1 : 0;
	}

	static int find_aba(String str, int start) {
		return find_aba(str, start, str.length());
	}

	static int find_aba(String str, int start, int end) {
		for (int i = start; i < end - 2; i++) {
			char a = str.charAt(i), b = str.charAt(i + 1), c = str.charAt(i + 2);
			if (a == c && a != b) {
				return i;
			}
		}
		return -1;
	}

	public static int support_ssl(String str) {
		List<String> segments = new ArrayList<>();
		int idx = 0, idx_lsb = -1, idx_rsb;
		while ((idx_lsb = str.indexOf('[', idx)) != -1) {
			idx_rsb = str.indexOf(']', idx_lsb + 1);
			segments.add(str.substring(idx, idx_lsb));
			segments.add(str.substring(idx_lsb + 1, idx_rsb));
			idx = idx_rsb + 1;
		}
		if (idx < str.length()) {
			segments.add(str.substring(idx));
		}

		for (int i = 0, len = segments.size(); i < len - 1; i++) {
			var seg = segments.get(i);
			idx = 0;
			while ((idx = find_aba(seg, idx)) != -1) {
				char a = seg.charAt(idx), b = seg.charAt(idx + 1);
				String target = new String(new char[] { b, a, b });
				for (int j = i + 1; j < len; j += 2) {
					if (segments.get(j).indexOf(target) >= 0) {
						return 1;
					}
				}
				idx++;
			}
		}
		return 0;
	}

	public static void run(String input) {
		int sum = 0;
		var lines = input.split("\\R");

		for (int i = 0, len = lines.length; i < len; i++) {
			sum += support_tls(lines[i]);
		}
		System.out.println(sum);

		sum = 0;
		for (int i = 0, len = lines.length; i < len; i++) {
			sum += support_ssl(lines[i]);
		}
		System.out.println(sum);
	}
}
