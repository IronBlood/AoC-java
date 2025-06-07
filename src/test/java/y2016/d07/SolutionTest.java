package y2016.d07;

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
				new TestCase("abba[mnop]qrst", 1),
				new TestCase("abcd[bddb]xyyx", 0),
				new TestCase("aaaa[qwer]tyui", 0),
				new TestCase("ioxxoj[asdfgh]zxcvbn", 1));
	}

	@ParameterizedTest
	@MethodSource("part1_cases")
	void support_tls_test(TestCase tc) {
		assertEquals(tc.expected, Solution.support_tls(tc.input));
	}

	static Stream<TestCase> part2_cases() {
		return Stream.of(
				new TestCase("aba[bab]xyz", 1),
				new TestCase("xyx[xyx]xyx", 0),
				new TestCase("aaa[kek]eke", 1),
				new TestCase("zazbz[bzb]cdb", 1));
	}

	void support_ssl_test(TestCase tc) {
		assertEquals(tc.expected, Solution.support_ssl(tc.input));
	}
}
