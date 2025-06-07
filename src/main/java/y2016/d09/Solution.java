package y2016.d09;

public class Solution {
	static char CHAR_LP = '(', CHAR_RP = ')';

	public static int count_decompressed_length(String data) {
		int len = 0, idx = 0, idx_lp = -1, idx_rp = -1;

		while ((idx_lp = data.indexOf(CHAR_LP, idx)) >= 0) {
			len += idx_lp - idx;
			idx_rp = data.indexOf(CHAR_RP, idx_lp + 1);

			if (idx_rp < -1) {
				len += data.length() - idx_lp;
				idx = data.length();
				break;
			}

			var repeat_pattern = data.substring(idx_lp + 1, idx_rp);
			var arr = repeat_pattern.split("x");
			int count = Integer.parseInt(arr[0]), times = Integer.parseInt(arr[1]);
			len += count * times;
			idx = idx_rp + 1 + count;
		}

		return len + (data.length() - idx);
	}

	public static long count_decompressed_length_2(String data) {
		return count_decompressed_length_2(data, 0, data.length());
	}

	static long count_decompressed_length_2(String data, int start, int end) {
		long len = 0;
		int idx = start, idx_lp = -1, idx_rp = -1;

		while ((idx_lp = data.indexOf(CHAR_LP, idx)) >= 0 && (idx_lp < end)) {
			len += idx_lp - idx;
			idx_rp = data.indexOf(CHAR_RP, idx_lp + 1);

			var marker_str = data.substring(idx_lp + 1, idx_rp);
			var arr = marker_str.split("x");
			int count = Integer.parseInt(arr[0]), times = Integer.parseInt(arr[1]);
			len += times * count_decompressed_length_2(data, idx_rp + 1, idx_rp + 1 + count);
			idx = idx_rp + 1 + count;
		}

		return len + Math.max(end - idx, 0);
	}

	public static void run(String input) {
		System.out.println(count_decompressed_length(input));
		System.out.println(count_decompressed_length_2(input));
	}
}
