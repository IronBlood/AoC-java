import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	private static record ParsedDate(String year, String date) {
	}

	private static ParsedDate parse_date(String str) {
		Matcher match = Pattern.compile("^(\\d{4})-(\\d{2})$").matcher(str);
		if (!match.matches()) {
			throw new IllegalArgumentException("Wrong data format");
		}

		return new ParsedDate(match.group(1), match.group(2));
	}

	private static void exec(String date) {
		try {
			ParsedDate d = parse_date(date);

			String input_path = String.format("/y%s_d%s_input.txt", d.year, d.date);
			InputStream is = Main.class.getResourceAsStream(input_path);
			if (is == null) {
				throw new IOException(String.format("Failed to load input %s", input_path));
			}
			String input = new String(is.readAllBytes(), StandardCharsets.UTF_8);

			String clazzName = String.format("y%s.d%s.Solution", d.year, d.date);
			Class<?> clazz = Class.forName(clazzName);
			Method method = clazz.getMethod("run", String.class);

			method.invoke(null, input.trim());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void create_file(String file_path, String content) throws IOException {
		Path path = Paths.get(file_path);
		Files.createDirectories(path.getParent());
		if (Files.exists(path)) {
			System.err.println(String.format("File exists at %s, skip...", file_path));
		} else {
			Files.writeString(path, content, StandardCharsets.UTF_8);
		}
	}

	private static void scaffolding(String date) {
		try {
			ParsedDate d = parse_date(date);

			String file_path;
			String content;

			// Solution file
			file_path = String.format("src/main/java/y%s/d%s/Solution.java", d.year, d.date);
			content = """
					package y%s.d%s;

					public class Solution {
						public static void run(String input) {
						}
					}
					""".formatted(d.year, d.date);
			create_file(file_path, content);

			// Unit test file
			file_path = String.format("src/test/java/y%s/d%s/SolutionTest.java", d.year, d.date);
			content = """
					package y%s.d%s;

					import org.junit.jupiter.params.ParameterizedTest;
					import org.junit.jupiter.params.provider.MethodSource;

					import static org.junit.jupiter.api.Assertions.assertEquals;

					import java.util.stream.Stream;

					public class SolutionTest {
					}
					""".formatted(d.year, d.date);
			create_file(file_path, content);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		if (args.length == 1) {
			exec(args[0]);
		} else if (args.length == 2 && "s".equals(args[1])) {
			scaffolding(args[0]);
		} else {
			System.err.println("Wrong arguments");
		}
	}
}
