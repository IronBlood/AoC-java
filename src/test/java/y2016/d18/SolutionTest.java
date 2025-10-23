package y2016.d18;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTest {
	@Test
	void Test_1() {
		assertEquals(6, Solution.count_safe_tiles("..^^.", 3));
	}

	@Test
	void Test_2() {
		assertEquals(38, Solution.count_safe_tiles(".^^.^.^^^^", 10));
	}
}
