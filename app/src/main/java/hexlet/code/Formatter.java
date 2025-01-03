package hexlet.code;

import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;
import hexlet.code.formatters.Json;

public class Formatter {
    public static String formatter(String format, String changes, String key, Object value1, Object value2) {
        if ("stylish".equals(format)) {
            return Stylish.format(changes, key, value1, value2);
        } else if ("plain".equals(format)) {
            return Plain.format(changes, key, value1, value2);
        } else if ("json".equals(format)) {
            return Json.format(changes, key, value1, value2);
        } else {
            return Plain.format(changes, key, value1, value2);
        }
    }
}

