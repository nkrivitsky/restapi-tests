package framework.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;

import static com.fasterxml.jackson.databind.DeserializationFeature.READ_ENUMS_USING_TO_STRING;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_ENUMS_USING_TO_STRING;


public class JSONUtils {

    private JSONUtils() {
    }

    public static <T> T mapJSONToObject(String json, Class<T> targetClass) {
        T result = null;
        try {
            result = new ObjectMapper().readValue(json, targetClass);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return result;
    }

    public static <T> String mapJSONObjectToString(T targetClass) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.enable(WRITE_ENUMS_USING_TO_STRING);
        objectMapper.enable(READ_ENUMS_USING_TO_STRING);

        return objectMapper.writeValueAsString(targetClass);
    }
}
