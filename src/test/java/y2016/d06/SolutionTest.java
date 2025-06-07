package y2016.d06;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTest {
	void get_message_test() {
		assertEquals("easter", Solution.get_message("""
				eedadn
				drvtee
				eandsr
				raavrd
				atevrs
				tsrnev
				sdttsa
				rasrtv
				nssdts
				ntnada
				svetve
				tesnvt
				vntsnd
				vrdear
				dvrsen
				enarar\
				""", 1));
	}
}
