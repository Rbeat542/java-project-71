package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class Parser {
    public static HashMap<String, Object> parseFile(String pathToFile) throws Exception {
        Path path = Paths.get(pathToFile.toString()).toAbsolutePath().normalize();
        byte[] file1Contents = Files.readAllBytes(path);
        var mapper = chooseMapper(path);
        HashMap<String, Object> result = mapper.readValue(file1Contents,
            new TypeReference<HashMap<String, Object>>() {
            });
        return result;
    }

    public static ObjectMapper chooseMapper(Path path) throws Exception {
        String extensionOfFile = getExtension(path);
        if (extensionOfFile.equals("json")) {
            return new ObjectMapper();
        } else if ((extensionOfFile.equals("yml") || extensionOfFile.equals("yaml"))) {
            return YAMLMapper.builder().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER).build();
        } else {
            //System.out.println("Format of file(s) is wrong or not specified. Applying JSON format by default");
            throw new Exception("Format of file(s) is wrong or not specified");
        }
    }

    public static String getExtension(Path pathToFile) {
        int dotIndex = pathToFile.toString().lastIndexOf('.');
        String extension = (dotIndex > 0) ? pathToFile.toString().substring(dotIndex + 1) : "";
        return extension;
    }
}
