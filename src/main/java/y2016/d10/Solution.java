package y2016.d10;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Solution {
	private static class Robot {
		String id;
		List<Integer> values;

		Robot(String id, List<Integer> values) {
			this.id = id;
			this.values = values;
		}
	}

	static String part_1_id;

	static void check_robot(Robot bot) {
		if (part_1_id == null
				&& "17,61".equals(bot.values.stream().map(String::valueOf).collect(Collectors.joining(",")))) {
			part_1_id = bot.id;
		}
	}

	static Map<String, Integer> outputs = new HashMap<>();

	static void handle_outputs(String id, int value) {
		outputs.put(id, value);
	}

	static Robot helper_assign_value_to_robot(Map<String, Robot> map, int value, String id) {
		Robot bot;
		if (map.containsKey(id)) {
			bot = map.get(id);
			bot.values.add(value);
			Collections.sort(bot.values);
		} else {
			// List.of JDK 9+
			bot = new Robot(id, new ArrayList<>(List.of(value)));
			// Arrays.asList JDK 8
			// bot = new Robot(id, new ArrayList<>(Arrays.asList(value));
			map.put(id, bot);
		}
		return bot;
	}

	static void simulate(String data) {
		Map<String, Robot> map_robots = new HashMap<>();

		Deque<String> queue = new ArrayDeque<>();
		for (var line : data.split("\\R")) {
			if (line.startsWith("value ")) {
				var arr = line.split(" ");
				var bot = helper_assign_value_to_robot(map_robots, Integer.parseInt(arr[1]), arr[5]);
				check_robot(bot);
			} else {
				queue.addLast(line);
			}
		}

		while (queue.size() > 0) {
			var cmd = queue.removeFirst();
			var arr = cmd.split(" ");
			var bot_id = arr[1];
			var lower_bot_check = "bot".equals(arr[5]);
			var lower_bot_id = arr[6];
			var higher_bot_check = "bot".equals(arr[10]);
			var higher_bot_id = arr[11];

			Robot source_bot;
			if (!map_robots.containsKey(bot_id)) {
				source_bot = new Robot(bot_id, new ArrayList<>());
				map_robots.put(bot_id, source_bot);
			} else {
				source_bot = map_robots.get(bot_id);
			}
			if (source_bot.values.size() < 2) {
				queue.addLast(cmd);
				continue;
			}

			check_robot(source_bot);

			if (lower_bot_check) {
				helper_assign_value_to_robot(map_robots, source_bot.values.get(0), lower_bot_id);
			} else {
				handle_outputs(lower_bot_id, source_bot.values.get(0));
			}
			if (higher_bot_check) {
				helper_assign_value_to_robot(map_robots, source_bot.values.get(1), higher_bot_id);
			} else {
				handle_outputs(higher_bot_id, source_bot.values.get(1));
			}
			source_bot.values.clear();
		}
	}

	public static void run(String input) {
		simulate(input);
		System.out.println(part_1_id);
		int value = 1;
		for (Integer i = 0; i < 3; i++) {
			value *= outputs.get(i.toString());
		}
		System.out.println(value);
	}
}
