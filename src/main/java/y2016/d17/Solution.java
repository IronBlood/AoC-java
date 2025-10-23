package y2016.d17;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
	private static String get_state(String str) {
		try {
			var md = MessageDigest.getInstance("MD5");
			var hash = md.digest(str.getBytes(StandardCharsets.UTF_8));
			var hex = new StringBuilder();
			for (int i = 0; i < 4; i++) {
				hex.append(String.format("%02x", hash[i]));
			}
			return hex.toString();
		} catch (Exception ex) {
			return "";
		}
	}

	private static boolean in_grid(int x, int y) {
		return x >= 0 && y >= 0 && x < 4 && y < 4;
	}

	public static String find_path1(String code) {
		return (String) find_path(code, 1);
	}

	public static int find_path2(String code) {
		return (int) find_path(code, 2);
	}

	private static class QueueEntry {
		public int[] pos;
		public String path;

		QueueEntry(int a, int b, String path) {
			this.pos = new int[] { a, b };
			this.path = path;
		}
	}

	private static class DirEntry {
		public int[] v;
		public String c;

		DirEntry(int[] v, String c) {
			this.v = v;
			this.c = c;
		}
	}

	private static boolean is_door_open(char c) {
		return "bcdef".indexOf(c) >= 0;
	}

	private static Object find_path(String code, int part) {
		Deque<QueueEntry> queue = new ArrayDeque<>();
		queue.offer(new QueueEntry(0, 0, ""));

		DirEntry[] dirs = new DirEntry[] {
				new DirEntry(new int[] { -1, 0 }, "U"),
				new DirEntry(new int[] { 1, 0 }, "D"),
				new DirEntry(new int[] { 0, -1 }, "L"),
				new DirEntry(new int[] { 0, 1 }, "R"),
		};

		int longest_step = 0;
		int step = 0;
		while (queue.size() > 0) {
			ArrayDeque<QueueEntry> next_queue = new ArrayDeque<>();
			QueueEntry curr;
			while ((curr = queue.poll()) != null) {
				int x = curr.pos[0];
				int y = curr.pos[1];
				if (x == 3 && y == 3) {
					if (part == 1) {
						return curr.path;
					} else {
						longest_step = step;
						continue;
					}
				}

				if (!in_grid(x, y)) {
					continue;
				}

				var state = get_state(code + curr.path);
				for (int i = 0; i < 4; i++) {
					var dir = dirs[i];
					if (is_door_open(state.charAt(i))) {
						next_queue.offer(new QueueEntry(
								x + dir.v[0],
								y + dir.v[1],
								curr.path + dir.c));
					}
				}
			}
			queue = next_queue;
			step++;
		}
		return longest_step;
	}

	public static void run(String input) {
		System.out.println(find_path1(input));
		System.out.println(find_path2(input));
	}
}
