package moe.yiheng.servicebase.exceptionhandler;

import cn.dev33.satoken.exception.NotLoginException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import moe.yiheng.servicebase.Payload;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

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

    @ExceptionHandler(NotLoginException.class)
    @ResponseBody
    public Payload error(NotLoginException e) {
        e.printStackTrace();
        return Payload.fail().code(403).message("当前操作需要登录才能继续");
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public Payload error(MethodArgumentTypeMismatchException e) {
        e.printStackTrace();
        return Payload.fail().code(401).message("参数类型有误");
    }
}
