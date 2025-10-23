package y2016.d11;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTest {
	static final String data = """
			The first floor contains a hydrogen-compatible microchip and a lithium-compatible microchip.
			The second floor contains a hydrogen generator.
			The third floor contains a lithium generator.
			The fourth floor contains nothing relevant.\
			""";

	@Test
	void minimum_moves_test_1() {
		assertEquals(11, Solution.minimum_moves(data, 1));
	}
}
