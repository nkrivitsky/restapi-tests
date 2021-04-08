package framework.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;


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

        return objectMapper.writeValueAsString(targetClass);
    }
}
