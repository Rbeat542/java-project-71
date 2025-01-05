
import hexlet.code.Formatter;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class FormatterTest {

    @Test
    void testFormatterStylish() throws Exception {
        var params = List.of("stylish", "changed", "checked");
        var parameters = new ArrayList<>(params);
        String line = Formatter.formatter(parameters, false, true);
        assertTrue("  - checked: false\n  + checked: true".equals(line));
    }

    @Test
    void testFormatterPlain() throws Exception {
        var params = List.of("plain", "changed", "checked");
        var parameters = new ArrayList<>(params);
        String line = Formatter.formatter(parameters, false, true);
        assertTrue("Property 'checked' was updated. From false to true".equals(line));
    }
}
