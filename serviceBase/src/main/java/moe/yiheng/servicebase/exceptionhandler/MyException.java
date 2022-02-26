package moe.yiheng.servicebase.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author rinne
 * @Date 2022/2/24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyException extends RuntimeException {
    private Integer code;
    private String msg;
}
