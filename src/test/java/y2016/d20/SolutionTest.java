package y2016.d20;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTest {
	@Test
	void Test_1_1() {
		assertEquals(3, Solution.lowest_ip("5-8\n0-2\n4-7", 1));
	}

	@Test
	void Test_2_1() {
		assertEquals(2, Solution.lowest_ip("5-8\n0-2\n4-7", 2, 10));
	}

	@Test
	void Test_2_2() {
		assertEquals(3, Solution.lowest_ip("5-8\n1-2\n4-7", 2, 10));
	}
}
