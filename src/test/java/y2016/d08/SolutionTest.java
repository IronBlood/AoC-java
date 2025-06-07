package y2016.d08;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SolutionTest {
	private static class TestCase {
		final String text;
		final String cmd;
		final String expected;

		TestCase(String text, String cmd, String expected) {
			this.text = text;
			this.cmd = cmd;
			this.expected = expected;
		}

		@Override
		public String toString() {
			return text + " " + cmd + " -> " + expected;
		}
	}

	static Stream<TestCase> part1_cases() {
		return Stream.of(
				new TestCase("""
						.......
						.......
						.......\
						""", "rect 3x2", """
						###....
						###....
						.......\
						"""),
				new TestCase("""
						###....
						###....
						.......\
						""", "rotate column x=1 by 1", """
						#.#....
						###....
						.#.....\
						"""),
				new TestCase("""
						#.#....
						###....
						.#.....\
						""", "rotate row y=0 by 4", """
						....#.#
						###....
						.#.....\
						"""),
				new TestCase("""
						....#.#
						###....
						.#.....\
						""", "rotate column x=1 by 1", """
						.#..#.#
						#.#....
						.#.....\
						"""));
	}

	@ParameterizedTest
	@MethodSource("part1_cases")
	void exec_test(TestCase tc) {
		char[][] grid = Arrays.stream(tc.text.split("\\R"))
				.map(String::toCharArray)
				.toArray(char[][]::new);
		Solution.exec(grid, tc.cmd);
		assertEquals(tc.expected, Arrays.stream(grid).map(String::new).collect(Collectors.joining("\n")));
	}
}
