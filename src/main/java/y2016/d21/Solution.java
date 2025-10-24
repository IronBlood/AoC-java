package y2016.d21;

import java.util.ArrayList;

public class Solution {
	private static char[] dup_arr(char[] arr) {
		var res = new char[arr.length];
		for (int i = 0; i < res.length; i++) {
			res[i] = arr[i];
		}
		return res;
	}

	private static void reverse_range(char[] arr, int l, int r) {
		while (l < r) {
			var tmp = arr[l];
			arr[l] = arr[r];
			arr[r] = tmp;
			l++;
			r--;
		}
	}

	private static void rotate_left(char[] arr, int n) {
		n %= arr.length;
		if (n == 0) {
			return;
		}

		reverse_range(arr, 0, n - 1);
		reverse_range(arr, n, arr.length - 1);
		reverse_range(arr, 0, arr.length - 1);
	}

	private static int char_arr_indexOf(char[] arr, char x) {
		for (int i = 0; i < arr.length; i++) {
			if (x == arr[i]) {
				return i;
			}
		}
		return -1;
	}

	public static String scramble(String data, String input) {
		var arr = input.toCharArray();
		var instructions = data.split("\n");

		for (var ins : instructions) {
			var cmd = ins.split(" ");
			if (ins.startsWith("swap position")) {
				int x = Integer.parseInt(cmd[2]), y = Integer.parseInt(cmd[5]);
				var tmp = arr[x];
				arr[x] = arr[y];
				arr[y] = tmp;
			} else if (ins.startsWith("swap letter")) {
				int x = char_arr_indexOf(arr, cmd[2].charAt(0)), y = char_arr_indexOf(arr, cmd[5].charAt(0));
				arr[x] = cmd[5].charAt(0);
				arr[y] = cmd[2].charAt(0);
			} else if (cmd[0].equals("rotate")) {
				if (cmd[1].equals("left") || cmd[1].equals("right")) {
					int x = Integer.parseInt(cmd[2]);
					x %= arr.length;
					if (cmd[1].equals("right")) {
						x = arr.length - x;
					}
					rotate_left(arr, x);
				} else {
					int idx = char_arr_indexOf(arr, cmd[cmd.length - 1].charAt(0));
					int count = 1 + idx + (idx >= 4 ? 1 : 0);
					count %= arr.length;
					count = arr.length - count;
					rotate_left(arr, count);
				}
			} else if (ins.startsWith("reverse")) {
				int x = Integer.parseInt(cmd[2]), y = Integer.parseInt(cmd[4]);
				char[] next_arr = new char[arr.length];
				for (int i = 0; i < x; i++) {
					next_arr[i] = arr[i];
				}
				for (int i = 0; i <= y - x; i++) {
					next_arr[x + i] = arr[y - i];
				}
				for (int i = y + 1; i < arr.length; i++) {
					next_arr[i] = arr[i];
				}
				arr = next_arr;
			} else if (ins.startsWith("move")) {
				int x = Integer.parseInt(cmd[2]), y = Integer.parseInt(cmd[5]);
				if (x == y) {
					continue;
				}
				var tmp = arr[x];
				// boxing
				ArrayList<Character> list = new ArrayList<>();
				for (var c : arr) {
					list.add(c);
				}
				list.remove(x);
				list.add(y, tmp);
				arr = new char[arr.length];
				// unboxing
				for (int i = 0; i < arr.length; i++) {
					arr[i] = list.get(i);
				}
			}
		}

		return new String(arr);
	}

	public static String unscramble(String data, String input) {
		var arr = input.toCharArray();
		var instructions = data.split("\n");

		for (int I = instructions.length - 1; I >= 0; I--) {
			var ins = instructions[I];
			var cmd = ins.split(" ");

			if (ins.startsWith("swap position")) {
				int x = Integer.parseInt(cmd[2]), y = Integer.parseInt(cmd[5]);
				var tmp = arr[x];
				arr[x] = arr[y];
				arr[y] = tmp;
			} else if (ins.startsWith("swap letter")) {
				int x = char_arr_indexOf(arr, cmd[2].charAt(0)), y = char_arr_indexOf(arr, cmd[5].charAt(0));
				arr[x] = cmd[5].charAt(0);
				arr[y] = cmd[2].charAt(0);
			} else if (ins.startsWith("move")) {
				int x = Integer.parseInt(cmd[2]), y = Integer.parseInt(cmd[5]);
				if (x == y) {
					continue;
				}
				var tmp = arr[y];
				ArrayList<Character> list = new ArrayList<>();
				for (var c : arr) {
					list.add(c);
				}
				list.remove(y);
				list.add(x, tmp);
				arr = new char[arr.length];
				for (int i = 0; i < arr.length; i++) {
					arr[i] = list.get(i);
				}
			} else if (ins.startsWith("reverse")) {
				int x = Integer.parseInt(cmd[2]), y = Integer.parseInt(cmd[4]);
				char[] next_arr = new char[arr.length];
				for (int i = 0; i < x; i++) {
					next_arr[i] = arr[i];
				}
				for (int i = 0; i <= y - x; i++) {
					next_arr[x + i] = arr[y - i];
				}
				for (int i = y + 1; i < arr.length; i++) {
					next_arr[i] = arr[i];
				}
				arr = next_arr;
			} else if (cmd[0].equals("rotate")) {
				if (cmd[1].equals("left") || cmd[1].equals("right")) {
					int x = Integer.parseInt(cmd[2]);
					x %= arr.length;
					if (cmd[1].equals("left")) {
						x = arr.length - x;
					}
					rotate_left(arr, x);
				} else {
					var ch = cmd[cmd.length - 1].charAt(0);
					var str = new String(arr);
					var dup = dup_arr(arr);
					for (int i = 0; i < arr.length; i++) {
						rotate_left(dup, 1);
						var idx = char_arr_indexOf(dup, ch);
						var count = (1 + idx + (idx >= 4 ? 1 : 0)) % arr.length;
						count = arr.length - count;
						var tmp = dup_arr(dup);
						rotate_left(tmp, count);
						if (str.equals(new String(tmp))) {
							arr = dup;
							break;
						}
					}
				}
			}
		}

		return new String(arr);
	}

	public static void run(String input) {
		System.out.println(scramble(input, "abcdefgh"));
		System.out.println(unscramble(input, "fbgdceah"));
	}
}
