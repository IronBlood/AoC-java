package y2016.d15;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
	private static class Disc {
		int mod;
		int start_idx;
	}

	private static Disc parse_disc(String line) {
		ArrayList<Integer> nums = new ArrayList<>();
		Matcher m = Pattern.compile("\\d+").matcher(line);
		while (m.find()) {
			nums.add(Integer.parseInt(m.group()));
		}

		var d = new Disc();
		d.mod = nums.get(1);
		d.start_idx = nums.get(3);
		return d;
	}

	private static boolean sim(int num, ArrayList<Disc> discs) {
		for (int i = 0; i < discs.size(); i++) {
			int pos = (num + 1 + i + discs.get(i).start_idx) % discs.get(i).mod;
			if (pos != 0) {
				return false;
			}
		}
		return true;
	}

	public static int first_time(String data, int part) {
		ArrayList<Disc> discs = new ArrayList<>();
		for (var line : data.split("\n")) {
			discs.add(parse_disc(line));
		}

		if (part == 2) {
			var extra = new Disc();
			extra.mod = 11;
			extra.start_idx = 0;
			discs.add(extra);
		}

		int i = 0;
		do {
			if (sim(i, discs)) {
				return i;
			}
			i++;
		} while (true);
	}

	public static void run(String input) {
		System.out.println(first_time(input, 1));
		System.out.println(first_time(input, 2));
	}
}
