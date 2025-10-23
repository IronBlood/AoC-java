package y2016.d14;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Comparator;

public class Solution {
	private static String get_md5(String str, int times) throws NoSuchAlgorithmException {
		while (times-- > 0) {
			var md = MessageDigest.getInstance("MD5");
			var hash = md.digest(str.getBytes(StandardCharsets.UTF_8));
			var hex = new StringBuilder();
			for (var b : hash) {
				hex.append(String.format("%02x", b));
			}
			str = hex.toString();
		}
		return str;
	}

	private static class Hash {
		public int idx;
		char key;

		Hash(int idx, char key) {
			this.idx = idx;
			this.key = key;
		}
	}

	private static class Pair {
		public char c;
		public String s;
	}

	public static int get_index(String salt, int part) throws Exception {
		ArrayList<Hash> triple_hashes = new ArrayList<>();
		ArrayList<Hash> keys = new ArrayList<>();

		int idx = 0;
		ArrayList<Pair> five_times = new ArrayList<>();
		for (char c : "0123456789abcdef".toCharArray()) {
			String ch = String.valueOf(c);
			var pair = new Pair();
			pair.c = c;
			pair.s = ch.repeat(5);
			five_times.add(pair);
		}

		while (keys.size() < 64) {
			var checksum = get_md5(salt + String.valueOf(idx), part == 1 ? 1 : 2017);
			for (int i = 0; i < checksum.length() - 2; i++) {
				var c = checksum.charAt(i);
				if (c == checksum.charAt(i + 1) && c == checksum.charAt(i + 2)) {
					triple_hashes.add(new Hash(idx, c));
					break;
				}
			}

			for (var entry : five_times) {
				if (checksum.indexOf(entry.s) >= 0) {
					for (int i = 0; i < triple_hashes.size(); i++) {
						int diff = 0;
						var h = triple_hashes.get(i);
						if (h.key == entry.c && (diff = (idx - h.idx)) > 0 && diff <= 1000) {
							keys.add(h);
							triple_hashes.remove(h);
							i--;
						}
					}
				}
			}

			idx++;
		}

		keys.sort(Comparator.comparingInt(k -> k.idx));
		return keys.get(63).idx;
	}

	public static void run(String input) {
		try {
			System.out.println(get_index(input, 1));
			System.out.println(get_index(input, 2));
		} catch (Exception ex) {
			System.err.println("FAILED");
		}
	}
}
