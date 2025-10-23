package y2016.d16;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SolutionTest {
	@Test
	void Test_1() {
		assertEquals(Solution.get_checksum("110010110100"), "100");
	}

	@Test
	void Test_2() {
		assertEquals(Solution.disk_checksum("10000", 20), "01100");
	}
}
