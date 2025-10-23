package y2016.d15;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SolutionTest {
	@Test
	void Test_1() {
		assertEquals(Solution.first_time("""
				Disc #1 has 5 positions; at time=0, it is at position 4.
				Disc #2 has 2 positions; at time=0, it is at position 1.\
				""", 1), 5);
	}
}
