package y2016.d24;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTest {
	@Test
	void Test_1() {
		assertEquals(14, Solution.fewest_steps("""
				###########
				#0.1.....2#
				#.#######.#
				#4.......3#
				###########\
				""", 1));
	}
}
