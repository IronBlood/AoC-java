package y2016.d21;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTest {
	@Test
	void Test_1() {
		assertEquals("decab", Solution.scramble("""
				swap position 4 with position 0
				swap letter d with letter b
				reverse positions 0 through 4
				rotate left 1 step
				move position 1 to position 4
				move position 3 to position 0
				rotate based on position of letter b
				rotate based on position of letter d\
				""", "abcde"));
	}
}
