package y2016.d09;

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
				new TestCase("ADVENT", 6),
				new TestCase("A(1x5)BC", 7),
				new TestCase("(3x3)XYZ", 9),
				new TestCase("A(2x2)BCD(2x2)EFG", 11),
				new TestCase("(6x1)(1x3)A", 6),
				new TestCase("X(8x2)(3x3)ABCY", 18));
	}

	@ParameterizedTest
	@MethodSource("part1_cases")
	void count_decompressed_length_test(TestCase tc) {
		assertEquals(tc.expected, Solution.count_decompressed_length(tc.input));
	}

	static Stream<TestCase> part2_cases() {
		return Stream.of(
				new TestCase("(3x3)XYZ", 9),
				new TestCase("X(8x2)(3x3)ABCY", "XABCABCABCABCABCABCY".length()),
				new TestCase("(27x12)(20x12)(13x14)(7x10)(1x12)A", 241920),
				new TestCase("(25x3)(3x3)ABC(2x3)XY(5x2)PQRSTX(18x9)(3x2)TWO(5x7)SEVEN", 445));
	}

	@ParameterizedTest
	@MethodSource("part1_cases")
	void count_decompressed_length_2_test(TestCase tc) {
		assertEquals(tc.expected, Solution.count_decompressed_length_2(tc.input));
	}
}
