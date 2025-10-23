package y2016.d12;

import java.util.Arrays;

public class Solution {
	public static int get_idx(Character c) {
		int code = c.charValue();
		return code >= 97 ? (code - 97) : -1;
	}

	public static int exec(String data, int part) {
		var registers = new int[4];
		if (part == 2) {
			registers[2] = 1;
		}

		var instructions = Arrays.stream(data.split("\n"))
				.map(line -> line.split(" "))
				.toArray(String[][]::new);

		for (int i = 0; i < instructions.length;) {
			var jump = false;
			var op = get_idx(instructions[i][1].charAt(0));
			switch (instructions[i][0]) {
				case "cpy":
					registers[get_idx(instructions[i][2].charAt(0))] = op == -1
							? Integer.parseInt(instructions[i][1])
							: registers[op];
					break;
				case "inc":
					registers[op]++;
					break;
				case "dec":
					registers[op]--;
					break;
				case "jnz":
					if ((op == -1 ? Integer.parseInt(instructions[i][1]) : registers[op]) != 0) {
						jump = true;
						i += Integer.parseInt(instructions[i][2]);
					}
					break;
			}
			if (!jump) {
				i++;
			}
		}
		return registers[0];
	}

	public static void run(String input) {
		System.out.println(exec(input, 1));
		System.out.println(exec(input, 2));
	}
}
