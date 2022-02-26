package moe.yiheng.musicservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import moe.yiheng.servicebase.Payload;

/**
 * @Author rinne
 * @Date 2022/2/26
 */
@RestController
public class TestController {
    @RequestMapping("/test")
    public Payload test () {
        return Payload.fail();
    }
}
