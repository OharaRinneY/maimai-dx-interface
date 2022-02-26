package moe.yiheng.musicservice.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import moe.yiheng.musicservice.service.MusicService;
import moe.yiheng.servicebase.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @Autowired
    MusicService musicService;

    @PostMapping("refresh")
    public Payload refresh(String url) {
        // "https://maimai.ohara-rinne.tech/api/maimaidxprober/music_data"
        Integer count = musicService.refresh(url);
        return Payload.success().data("count", count);
    }
}
