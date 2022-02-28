package moe.yiheng.servicebase.exceptionhandler;

import cn.dev33.satoken.exception.NotLoginException;
import cn.hutool.extra.template.engine.freemarker.FreemarkerTemplate;
import feign.codec.DecodeException;
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
        return Payload.fail(null).message(e.getMessage());
    }

    @ExceptionHandler(MyException.class)
    @ResponseBody
    public Payload error(MyException e) {
        return Payload.fail(null).code(e.getCode()).message(e.getMsg());
    }

    @ExceptionHandler(NotLoginException.class)
    @ResponseBody
    public Payload error(NotLoginException e) {
        return Payload.fail(null).code(403).message("当前操作需要登录才能继续");
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public Payload error(MethodArgumentTypeMismatchException e) {
        return Payload.fail(null).code(401).message("参数类型有误");
    }

    @ExceptionHandler(DecodeException.class)
    @ResponseBody
    public Payload error(DecodeException e) {
        if (e.getCause() instanceof MyException) {
            MyException myException = (MyException) e.getCause();
            return Payload.fail(null).code(myException.getCode()).message(myException.getMsg());
        }
        return Payload.fail(null).code(400).message("服务调用出错");
    }

}
