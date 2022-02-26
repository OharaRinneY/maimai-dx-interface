package moe.yiheng.servicebase.exceptionhandler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import moe.yiheng.servicebase.Payload;

/**
 * @Author rinne
 * @Date 2022/2/24
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Payload error(Exception e) {
        e.printStackTrace();
        return Payload.fail().message(e.getMessage());
    }

    @ExceptionHandler(MyException.class)
    @ResponseBody
    public Payload error(MyException e) {
        return Payload.fail().code(e.getCode()).message(e.getMsg());
    }
}
