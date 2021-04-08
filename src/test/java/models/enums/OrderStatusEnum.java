package models.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum OrderStatusEnum {
    PLACED("placed"),
    APPROVED("approved"),
    DELIVERED("delivered");

    private String value;

    OrderStatusEnum(String value) {
        this.value = value;
    }

    private static Map<String, OrderStatusEnum> FORMAT_MAP = Stream
            .of(OrderStatusEnum.values())
            .collect(Collectors.toMap(s -> s.value, Function.identity()));

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static OrderStatusEnum fromValue(String text) {
        for (OrderStatusEnum b : OrderStatusEnum.values()) {
            if (String.valueOf(b.value).equals(text)) {
                return b;
            }
        }
        return null;
    }


    @JsonCreator
    public static OrderStatusEnum fromString(String string) {
        return Optional
                .ofNullable(FORMAT_MAP.get(string))
                .orElseThrow(() -> new IllegalArgumentException(string));
    }
}