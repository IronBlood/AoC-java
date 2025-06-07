package y2016.d04;

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
				new TestCase("aaaaa-bbb-z-y-x-123[abxyz]", 1),
				new TestCase("a-b-c-d-e-f-g-h-987[abcde]", 1),
				new TestCase("not-a-real-room-404[oarel]", 1),
				new TestCase("totally-real-room-200[decoy]", 0));
	}

	@ParameterizedTest
	@MethodSource("part1_cases")
	void is_real_room_test(TestCase tc) {
		assertEquals(tc.expected, Solution.is_real_room(tc.input));
	}
}
