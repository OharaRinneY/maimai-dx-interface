package moe.yiheng.servicebase;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Payload<T> {
    private Boolean success;
    private Integer code;
    private String message;
    private T data;

    private Payload() {
    }

    public static <T> Payload<T> success(T data) {
        var payload = new Payload<T>();
        payload.code = 200;
        payload.success = true;
        payload.message = "success";
        payload.data = data;
        return payload;
    }

    public static <T> Payload<T> fail(T data) {
        var payload = new Payload<T>();
        payload.code = 400;
        payload.success = false;
        payload.message = "fail";
        payload.data = data;
        return payload;
    }

    public Payload<T> message(String msg) {
        this.message = msg;
        return this;
    }

    public Payload<T> data(T data) {
        this.data = data;
        return this;
    }

    public Payload<T> code(Integer code) {
        this.code = code;
        return this;
    }
}
