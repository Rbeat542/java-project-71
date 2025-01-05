import hexlet.code.Differ;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.nio.file.NoSuchFileException;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DifferTest {

    private static String testfile1 = "src/test/resources/file1.json";
    private static String testfile2 = "src/test/resources/file2.json";
    private static String testfile3 = "src/test/resources/file1.yml";
    private static String testfile4 = "src/test/resources/file2.yml";
    private static String testfile5 = "src/test/resources/file1.ml";
    private static String testfile6 = "src/test/resources/fi4.yml";

    @Test
    public void testDifferNoSuchFileException() throws Exception {
        assertThrows(NoSuchFileException.class, () -> {
            Differ.generate(testfile6, testfile3, "plain");
            throw new Exception();
        });
    }

    @Test
    public void testGenerateUknownFileTypeException() throws Exception {
        assertThrows(Exception.class, () -> {
            Differ.generate(testfile5, testfile4, "plain");
            throw new Exception();
        });
    }

    @ParameterizedTest
    @CsvSource({
        "src/test/resources/file1.json, src/test/resources/file2.json, stylish",
        "src/test/resources/file1.yml, src/test/resources/file2.yml, stylish",
    })
    public void testCompareStylish(String path1, String path2, String format) throws Exception {
        String expected = "    chars1: [a, b, c]\n  - chars2: [d, e, f]";
        String results = Differ.generate(path1, path2, format);
        assertTrue(results.contains(expected));
    }

    @ParameterizedTest
    @CsvSource({
        "src/test/resources/file1.json, src/test/resources/file2.json, plain",
        "src/test/resources/file1.yml, src/test/resources/file2.yml, plain",
    })
    void testComparePlain(String path1, String path2, String format) throws Exception {
        String expected = "Property 'chars2' was updated. From [complex value] to false";
        String results = Differ.generate(path1, path2, format);
        assertTrue(results.contains(expected));
        assertNotNull(path1);
        assertNotNull(path2);
    }

    @Test
    void testCompareJsonJson() throws Exception {
        String expected = "\"chars2\": \"was updated. From [d, e, f] to false\"";
        String results2 = Differ.generate(testfile1, testfile2, "json");
        assertTrue(results2.contains(expected));
    }
}
