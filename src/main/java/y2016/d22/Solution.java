package y2016.d22;

import java.util.ArrayList;
import java.util.HashMap;

public class Solution {
	private static int size_to_num(String size) {
		return Integer.parseInt(size.substring(0, size.length() - 1));
	}

	public static int count_pairs(String data) {
		HashMap<Integer, Integer> avail_map = new HashMap<>();
		HashMap<Integer, Integer> used_map = new HashMap<>();

		var lines = data.split("\n");
		for (int i = 2; i < lines.length; i++) {
			var line = lines[i];
			var segments = line.split(" ");
			var list = new ArrayList<String>();
			for (var s : segments) {
				if (s.length() > 0) {
					list.add(s);
				}
			}

			if (list.size() != 5) {
				return -1;
			}

			var avail = size_to_num(list.get(3));
			var used = size_to_num(list.get(2));

			avail_map.put(avail, avail_map.getOrDefault(avail, 0) + 1);
			if (used > 0) {
				used_map.put(used, used_map.getOrDefault(used, 0) + 1);
			}
		}

		int pairs = 0;
		for (var u : used_map.keySet()) {
			for (var a : avail_map.keySet()) {
				if (u <= a) {
					pairs += used_map.get(u) * avail_map.get(a);
				}
			}
		}
		return pairs;
	}

	public static void run(String input) {
		System.out.println(count_pairs(input));
	}
}
