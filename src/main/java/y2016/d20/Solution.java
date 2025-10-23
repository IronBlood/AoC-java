package y2016.d20;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ArrayDeque;

public class Solution {
	public static long lowest_ip(String cfg, long part) {
		return lowest_ip(cfg, part, 4294967296L);
	}

	public static long lowest_ip(String cfg, long part, long range) {
		var lines = cfg.split("\n");
		long[][] settings = new long[lines.length][];
		for (int i = 0; i < lines.length; i++) {
			var strs = lines[i].split("-");
			var arr = new long[strs.length];
			for (int j = 0; j < strs.length; j++) {
				arr[j] = Long.parseLong(strs[j]);
			}
			settings[i] = arr;
		}

		Arrays.sort(settings, (a, b) -> {
			return (a[0] == b[0])
					? Long.compare(b[1], a[1])
					: Long.compare(a[0], b[0]);
		});

		if (settings[0][0] != 0 && part == 1) {
			return 0;
		}

		ArrayDeque<long[]> stack = new ArrayDeque<>();
		for (int i = 0; i < settings.length; i++) {
			var curr = settings[i];
			if (stack.size() == 0) {
				stack.push(curr);
				continue;
			}

			var peek = stack.peek();
			if (curr[1] < peek[1]) {
				continue;
			}
			if (curr[0] <= peek[1] + 1) {
				peek[1] = curr[1];
				continue;
			}

			if (part == 1) {
				return peek[1] + 1;
			}

			stack.push(curr);
		}

		long prev = -1, count = 0;
		long[] curr;
		while ((curr = stack.poll()) != null) {
			count += curr[0] - prev - 1;
			prev = curr[1];
		}
		count += range - prev - 1;
		return count;
	}

	public static void run(String input) {
		System.out.println(lowest_ip(input, 1));
		System.out.println(lowest_ip(input, 2));
	}
}
