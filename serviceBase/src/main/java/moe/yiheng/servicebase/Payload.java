package moe.yiheng.servicebase;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author rinne
 * @Date 2022/2/26
 */
@ToString
@EqualsAndHashCode
@Getter
public class Payload {
    private Boolean success;
    private Integer code;
    private String message;
    private final Map<String, Object> data = new HashMap<>();

    private Payload() {
    }

    public static Payload success() {
        Payload payload = new Payload();
        payload.code = 200;
        payload.success = true;
        payload.message = "success";
        return payload;
    }

    public static Payload fail() {
        Payload payload = new Payload();
        payload.code = 401;
        payload.success = false;
        payload.message = "fail";
        return payload;
    }

    public Payload message(String msg) {
        this.message = msg;
        return this;
    }

    public Payload data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public Payload code(Integer code) {
        this.code = code;
        return this;
    }
}
