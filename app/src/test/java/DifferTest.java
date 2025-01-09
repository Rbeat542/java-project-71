import hexlet.code.Differ;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.nio.file.NoSuchFileException;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DifferTest {

    @Test
    public void testDifferNoSuchFileException() throws Exception {
        String testfile1 = "src/test/resources/fi4.yml";
        String testfile2 = "src/test/resources/file1.yml";

        assertThrows(NoSuchFileException.class, () -> {
            Differ.generate(testfile1, testfile2, "plain");
            throw new Exception();
        });
    }

    @Test
    public void testGenerateUknownFileTypeException() throws Exception {
        String testfile1 = "src/test/resources/file1.ml";
        String testfile2 = "src/test/resources/file2.yml";

        assertThrows(Exception.class, () -> {
            Differ.generate(testfile1, testfile2, "plain");
            throw new Exception();
        });
    }

    @ParameterizedTest
    @CsvSource({
        "src/test/resources/file1.json, src/test/resources/file2.json, stylish",
        "src/test/resources/file1.yml, src/test/resources/file2.yml, stylish",
    })
    static void testCompareStylish(String path1, String path2, String format) throws Exception {
        String expected = "    chars1: [a, b, c]\n  - chars2: [d, e, f]";

        String results = Differ.generate(path1, path2, format);

        assertTrue(results.contains(expected));
    }

    @ParameterizedTest
    @CsvSource({
        "src/test/resources/file1.json, src/test/resources/file2.json, plain",
        "src/test/resources/file1.yml, src/test/resources/file2.yml, plain",
    })
    static void testComparePlain(String path1, String path2, String format) throws Exception {
        String expected = "Property 'chars2' was updated. From [complex value] to false";

        String results = Differ.generate(path1, path2, format);

        assertTrue(results.contains(expected));
        assertNotNull(path1);
        assertNotNull(path2);
    }

    @Test
    public void testCompareJsonJson() throws Exception {
        String testfile1 = "src/test/resources/file1.json";
        String testfile2 = "src/test/resources/file2.json";
        String expected = "\"chars2\": \"was updated. From [d, e, f] to false\"";

        String results2 = Differ.generate(testfile1, testfile2, "json");

        assertTrue(results2.contains(expected));
    }
}
