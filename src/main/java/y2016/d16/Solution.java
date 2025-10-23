package y2016.d16;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Solution {
	private static List<String> dragon_curve(List<String> x) {
		var y = new ArrayList<String>();
		for (int i = x.size() - 1; i >= 0; i--) {
			y.add(x.get(i).equals("0") ? "1" : "0");
		}

		ArrayList<String> result = new ArrayList<>(x);
		result.add("0");
		result.addAll(y);
		return result;
	}

	public static String get_checksum(String str) {
		return get_checksum(Arrays.asList(str.split("")));
	}

	public static String get_checksum(List<String> x) {
		do {
			var list = new ArrayList<String>();
			for (int i = 0, len = x.size(); i < len; i += 2) {
				list.add(x.get(i).equals(x.get(i + 1)) ? "1" : "0");
			}
			x = list;
		} while ((x.size() & 1) == 0);
		var builder = new StringBuilder();
		for (String s : x) {
			builder.append(s);
		}
		return builder.toString();
	}

	public static String disk_checksum(String data, int disk_size) {
		List<String> arr = Arrays.asList(data.split(""));
		do {
			arr = dragon_curve(arr);
		} while (arr.size() < disk_size);
		arr.subList(disk_size, arr.size()).clear();
		return get_checksum(arr);
	}

	public static void run(String input) {
		System.out.println(disk_checksum(input, 272));
		System.out.println(disk_checksum(input, 35651584));
	}
}
