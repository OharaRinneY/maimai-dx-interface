package moe.yiheng.loginservice.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import io.swagger.annotations.ApiOperation;
import moe.yiheng.servicebase.Payload;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author rinne
 * @Date 2022/2/27
 */
@RestController
@RequestMapping("login")
public class LoginController {

    /**
     * 临时解决方案
     */
    @ApiOperation(value = "获取token(临时解决方案),需有效jwt")
    @SaCheckLogin
    @GetMapping("token")
    public Payload<SaTokenInfo> token(String username) {
        StpUtil.login(username);
        SaTokenInfo token = StpUtil.getTokenInfo();
        return Payload.success(token);
    }

    @GetMapping("checkToken")
    @ApiOperation(value = "检查token是否有效")
    public Payload<Object> checkToken(String token) {
        Object id = StpUtil.getLoginIdByToken(token);
        if (id == null){
            return Payload.fail(null).message("token无效");
        }
        return Payload.success(id);
    }
}
