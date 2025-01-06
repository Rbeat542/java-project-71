import hexlet.code.Parser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParserTest {
    private static String testfile = "src/test/resources/fi4.yml";
    @ParameterizedTest(name = "{index} Parser of {0} test")
    @ValueSource(strings = {"src/test/resources/file1.json", "src/test/resources/file2.json",
        "src/test/resources/file1.yml", "src/test/resources/file2.yml" })
    private void testParseFileExistingFile(String path) throws Exception {
        HashMap<String, Object> map = Parser.parseFile(path);
        assertNotNull(map);
    }

    @Test
    void testParseFileNonExisitngFile() throws Exception {
        assertThrows(Exception.class, () -> {
            Parser.parseFile(testfile);
            throw new Exception();
        });
    }
}
