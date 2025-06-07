package y2016.d04;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Solution {
	private static record CharFreq(char c, int count) {
	}

	public static int is_real_room(String str) {
		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0, end = str.lastIndexOf('-'); i < end; i++) {
			var c = str.charAt(i);
			if (c == '-') {
				continue;
			}
			map.put(c, map.getOrDefault(c, 0) + 1);
		}

		List<CharFreq> arr = map.entrySet().stream()
				.map(e -> new CharFreq(e.getKey(), e.getValue()))
				.collect(Collectors.toList());
		arr.sort((a, b) -> (a.count() == b.count()) ? (a.c() - b.c()) : (b.count() - a.count()));

		for (int i = str.length() - 6, j = 0; j < 5; i++, j++) {
			if (str.charAt(i) != arr.get(j).c) {
				return 0;
			}
		}

		return 1;
	}

	static int count_real_rooms(String data) {
		int sum = 0;
		for (var line : data.split("\\R")) {
			if (is_real_room(line) == 1) {
				var sector_id = line.substring(line.lastIndexOf('-') + 1, line.lastIndexOf('['));
				sum += Integer.parseInt(sector_id);
			}
		}
		return sum;
	}

	static String decrypt(String str) {
		var arr = str.split("-");
		var num = Integer.parseInt(arr[arr.length - 1]) % 26;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 3; i++) {
			if (i > 0) {
				sb.append(' ');
			}
			var buf = arr[i].getBytes(StandardCharsets.US_ASCII);
			for (int j = 0; j < buf.length; j++) {
				buf[j] = (byte) (97 + (buf[j] - 97 + num) % 26);
			}
			sb.append(new String(buf, StandardCharsets.US_ASCII));
		}
		return sb.toString();
	}

	static String find_storage(String data) {
		for (var line : data.split("\\R")) {
			if (is_real_room(line) == 1) {
				var str = line.substring(0, line.lastIndexOf('['));
				var msg = decrypt(str);
				if ("northpole object storage".equals(msg)) {
					return str.substring(str.lastIndexOf("-") + 1);
				}
			}
		}
		return "";
	}

	public static void run(String input) {
		System.out.println(count_real_rooms(input));
		System.out.println(find_storage(input));
	}
}
