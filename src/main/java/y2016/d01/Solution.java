package y2016.d01;

import java.util.Set;
import java.util.HashSet;

public class Solution {
	public static int count_blocks(String commands) {
		int[] direction = { 0, -1, 0, 1, 0 };
		int i = 0, x = 0, y = 0;

		String[] parts = commands.split(", ");
		for (String cmd : parts) {
			int turn = cmd.charAt(0) == 'R' ? 1 : -1;
			int distance = Integer.parseInt(cmd.substring(1));
			i = (i + turn + 4) % 4;
			x += distance * direction[i];
			y += distance * direction[i + 1];
		}

		return Math.abs(x) + Math.abs(y);
	}

	private static class Position {
		int x;
		int y;

		Position(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return x + "," + y;
		}
	}

	public static int count_blocks_HQ(String commands) {
		int[] direction = { 0, -1, 0, 1, 0 };
		int i = 0;
		Position pos = new Position(0, 0);
		Position first = null;

		Set<String> visited = new HashSet<>();
		visited.add(pos.toString());
		for (String cmd : commands.split(", ")) {
			int turn = cmd.charAt(0) == 'R' ? 1 : -1;
			int distance = Integer.parseInt(cmd.substring(1));
			i = (i + turn + 4) % 4;

			for (int steps = 0; steps < distance; steps++) {
				pos.x += direction[i];
				pos.y += direction[i + 1];
				String str = pos.toString();
				if (visited.contains(str)) {
					first = new Position(pos.x, pos.y);
					break;
				}
				visited.add(str);
			}
			if (first != null) {
				break;
			}
		}

		return Math.abs(pos.x) + Math.abs(pos.y);
	}

	public static void run(String input) {
		System.out.println(count_blocks(input));
		System.out.println(count_blocks_HQ(input));
	}
}
