package y2016.d02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTest {
	@Test
	void get_code_test() {
		assertEquals("1985", Solution.get_code("""
				ULL
				RRDDD
				LURDL
				UUUUD\
				"""));
	}

	@Test
	void get_code2_test() {
		assertEquals("5DB3", Solution.get_code2("""
				ULL
				RRDDD
				LURDL
				UUUUD\
				"""));
	}
}
