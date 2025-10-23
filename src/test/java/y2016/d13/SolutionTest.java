package y2016.d13;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SolutionTest {
	@Test
	void shortest_steps_test() {
		assertEquals(Solution.shortest_steps("10", new int[] { 7, 4 }), 11);
	}

	@Test
	void count_locations_1() {
		assertEquals(Solution.count_locations("10", 0), 1);
	}

	@Test
	void count_locations_2() {
		assertEquals(Solution.count_locations("10", 1), 3);
	}

	@Test
	void count_locations_3() {
		assertEquals(Solution.count_locations("10", 2), 5);
	}
}
