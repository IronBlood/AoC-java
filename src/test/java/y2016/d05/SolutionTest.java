package y2016.d05;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SolutionTest {
	@Test
	void find_pw_test() {
		assertEquals("18f47a30", Solution.find_pw("abc", 1, 5));
	}
}
