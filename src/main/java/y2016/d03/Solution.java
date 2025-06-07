package y2016.d03;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class Solution {
	public static int can_form_a_triangle(Integer[] nums) {
		Arrays.sort(nums);
		return nums[0] + nums[1] > nums[2] ? 1 : 0;
	}

	private static List<Integer[]> parse_data(String data) throws IllegalArgumentException {
		// Java 10+
		var pattern = Pattern.compile("^\\s*(\\d+)\\s*(\\d+)\\s*(\\d+)\\s*$");
		List<Integer[]> res = new ArrayList<>();
		for (String line : data.split("\\R")) {
			var match = pattern.matcher(line);
			if (!match.matches()) {
				throw new IllegalArgumentException("Wrong format");
			}
			res.add(new Integer[] {
					Integer.parseInt(match.group(1)),
					Integer.parseInt(match.group(2)),
					Integer.parseInt(match.group(3)),
			});
		}
		return res;
	}

	private static int count_triangles(String data) {
		var numbers = parse_data(data);
		var sum = 0;

		for (var x : numbers) {
			sum += can_form_a_triangle(x);
		}
		return sum;
	}

	private static int count_triangles_by_column(String data) {
		var numbers = parse_data(data);
		var sum = 0;

		for (int i = 0, n = numbers.size(); i < n - 2; i += 3) {
			for (int j = 0; j < 3; j++) {
				sum += can_form_a_triangle(new Integer[] {
						numbers.get(i)[j],
						numbers.get(i + 1)[j],
						numbers.get(i + 2)[j],
				});
			}
		}

		return sum;
	}

	public static void run(String input) {
		System.out.println(count_triangles(input));
		System.out.println(count_triangles_by_column(input));
	}
}
