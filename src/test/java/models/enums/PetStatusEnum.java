package models.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum PetStatusEnum {
    AVAILABLE("available"),
    PENDING("pending"),
    SOLD("sold");

    private String value;
    private static Map<String, PetStatusEnum> FORMAT_MAP = Stream
            .of(PetStatusEnum.values())
            .collect(Collectors.toMap(s -> s.value, Function.identity()));

    PetStatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }


    public static PetStatusEnum fromValue(String text) {
        for (PetStatusEnum b : PetStatusEnum.values()) {
            if (String.valueOf(b.value).equals(text)) {
                return b;
            }
        }
        return null;
    }

    @JsonCreator
    public static PetStatusEnum fromString(String string) {
        return Optional
                .ofNullable(FORMAT_MAP.get(string))
                .orElseThrow(() -> new IllegalArgumentException(string));
    }
}