package moe.yiheng.musicservice.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import moe.yiheng.servicebase.Payload;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author rinne
 * @Date 2022/2/26
 */
@RestController
@RequestMapping("/music")
@Api(tags = "查歌接口")
public class MusicController {

    @GetMapping("refresh")
    public Payload refresh() {
        return Payload.success();
    }
}
