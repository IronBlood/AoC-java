package y2016.d12;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTest {
	@Test
	void exec_test() {
		assertEquals(42, Solution.exec("""
				cpy 41 a
				inc a
				inc a
				dec a
				jnz a 2
				dec a\
				""", 1));
	}
}
