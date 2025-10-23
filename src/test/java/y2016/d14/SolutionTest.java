package y2016.d14;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class SolutionTest {
	@BeforeAll
	static void skipWhenMd5Disabled() {
		String skipMd5 = System.getenv("SKIP_MD5");
		Assumptions.assumeTrue(skipMd5 == null, "SKIP_MD5 is set; skipping MD5-based tests.");
	}

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
