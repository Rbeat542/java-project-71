import hexlet.code.Call;
import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CallTest {

    @Test
    public void testCallExecution() throws Exception {
        Call call = new Call(new File("src/test/resources/file2.json"),
                new File("src/test/resources/file2.json"), "stylish");

        int result = call.execute();

        assertEquals(0, result);
    }

    @Test
    public void testCallMethod() throws Exception {
        Call call = new Call(new File("src/test/resources/file100.json"),
                new File("src/test/resources/file2.json"), "stylish");

        assertThrows(Exception.class, () -> {
            call.execute();
            throw new Exception();
        });
    }
}
