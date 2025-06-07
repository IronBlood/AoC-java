package y2016.d03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SolutionTest {

	@Test
	void can_form_a_triangle_test() {
		assertEquals(0, Solution.can_form_a_triangle(new Integer[] { 5, 10, 25 }));
	}
}
