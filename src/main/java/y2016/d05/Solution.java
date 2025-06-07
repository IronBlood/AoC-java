package y2016.d05;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;

public class Solution {
	public static String find_pw(String str, int part, int digit) {
		StringBuilder sb = new StringBuilder();
		String target = "0".repeat(digit);
		int count = 0;
		char[] pw = new char[8];
		Arrays.fill(pw, '_');

		try {
			var md = MessageDigest.getInstance("MD5");
			for (Integer i = 0; i < Integer.MAX_VALUE; i++) {
				md.reset();
				var hash = md.digest((str + i.toString()).getBytes(StandardCharsets.UTF_8));
				var hex = new StringBuilder();
				for (byte b : hash) {
					hex.append(String.format("%02x", b));
				}
				var checksum = hex.toString();
				if (checksum.startsWith(target)) {
					if (part == 1) {
						sb.append(checksum.charAt(digit));
						count++;
					} else {
						var idx = (int) checksum.charAt(digit) - 48;
						if (idx < 0 || idx >= 8 || pw[idx] != '_') {
							continue;
						}
						count++;
						pw[idx] = checksum.charAt(digit + 1);
					}
					if (count == 8)
						break;
				}
			}

			if (part != 1) {
				for (int i = 0; i < 8; i++) {
					sb.append(pw[i]);
				}
			}
			return sb.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return "";
	}

	public static void run(String input) {
		System.out.println(find_pw(input, 1, 5));
		System.out.println(find_pw(input, 2, 5));
	}
}
