package y2016.d19;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

public class SolutionTest {
	private static class TestCase {
		final int input;
		final int expected;

		TestCase(int input, int expected) {
			this.input = input;
			this.expected = expected;
		}

		@Override
		public String toString() {
			return input + " -> " + expected;
		}
	}

	static Stream<TestCase> part1_cases() {
		return Stream.of(
				new TestCase(4, 1),
				new TestCase(5, 3),
				new TestCase(6, 5),
				new TestCase(10, 5));
	}

	@ParameterizedTest
	@MethodSource("part1_cases")
	void Test_1(TestCase tc) {
		assertEquals(tc.expected, Solution.get_all(tc.input));
	}

	static Stream<TestCase> part2_cases() {
		return Stream.of(
				new TestCase(5, 2),
				new TestCase(12, 3));
	}

	@ParameterizedTest
	@MethodSource("part2_cases")
	void Test_2(TestCase tc) {
		assertEquals(tc.expected, Solution.get_all_2(tc.input));
	}
}
