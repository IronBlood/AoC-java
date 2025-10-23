package y2016.d05;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
	void find_pw_test() {
		assertEquals("18f47a30", Solution.find_pw("abc", 1, 5));
	}
}
