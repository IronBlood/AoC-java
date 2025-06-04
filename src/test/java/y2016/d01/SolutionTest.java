package y2016.d01;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

public class SolutionTest {
	private static class TestCase {
		final String input;
		final int expected;

		TestCase(String input, int expected) {
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
				new TestCase("R2, L3", 5),
				new TestCase("R2, R2, R2", 2),
				new TestCase("R5, L5, R5, R3", 12));
	}

	@ParameterizedTest
	@MethodSource("part1_cases")
	void count_blocks_test(TestCase tc) {
		assertEquals(tc.expected, Solution.count_blocks(tc.input));
	}

	static Stream<TestCase> part2_cases() {
		return Stream.of(
				new TestCase("R8, R4, R4, R8", 4),
				new TestCase("R2, R3, R2, R3", 0));
	}

	@ParameterizedTest
	@MethodSource("part2_cases")
	void count_blocks_HQ_test(TestCase tc) {
		assertEquals(tc.expected, Solution.count_blocks_HQ(tc.input));
	}
}
