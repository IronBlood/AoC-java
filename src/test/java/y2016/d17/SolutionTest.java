package y2016.d17;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

public class SolutionTest {
	private static class TestCase<T> {
		final String input;
		final T expected;

		TestCase(String input, T expected) {
			this.input = input;
			this.expected = expected;
		}

		@Override
		public String toString() {
			return input + " -> " + expected;
		}
	}

	static Stream<TestCase<String>> part1_cases() {
		return Stream.of(
				new TestCase<String>("ihgpwlah", "DDRRRD"),
				new TestCase<String>("kglvqrro", "DDUDRLRRUDRD"),
				new TestCase<String>("ulqzkmiv", "DRURDRUDDLLDLUURRDULRLDUUDDDRR"));
	}

	@ParameterizedTest
	@MethodSource("part1_cases")
	void Test_1(TestCase<String> tc) {
		assertEquals(tc.expected, Solution.find_path1(tc.input));
	}

	static Stream<TestCase<Integer>> part2_cases() {
		return Stream.of(
				new TestCase<Integer>("ihgpwlah", 370),
				new TestCase<Integer>("kglvqrro", 492),
				new TestCase<Integer>("ulqzkmiv", 830));
	}

	@ParameterizedTest
	@MethodSource("part2_cases")
	void Test_2(TestCase<Integer> tc) {
		assertEquals(tc.expected, Solution.find_path2(tc.input));
	}
}
