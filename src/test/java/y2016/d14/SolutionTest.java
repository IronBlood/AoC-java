package y2016.d14;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class SolutionTest {
	@Test
	void Test_1() {
		try {
			assertEquals(Solution.get_index("abc", 1), 22728);
		} catch (Exception ex) {
			fail();
		}
	}

	@Test
	void Test_2() {
		try {
			assertEquals(Solution.get_index("abc", 2), 22551);
		} catch (Exception ex) {
			fail();
		}
	}
}
