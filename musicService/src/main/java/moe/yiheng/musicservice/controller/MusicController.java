package moe.yiheng.musicservice.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import moe.yiheng.entity.music.Charts;
import moe.yiheng.entity.music.Music;
import moe.yiheng.musicservice.service.MusicService;
import moe.yiheng.servicebase.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("{id}")
    @ApiOperation(value = "获取乐曲信息")
    public Payload getMusic(
            @PathVariable("id") @ApiParam(value = "乐曲id",required = true) Integer id,
            @ApiParam(value = "是否包含谱面信息",required = false) boolean withCharts
    ) {
        Music music = musicService.getById(id,withCharts);
        return Payload.success().data("music", music);
    }
}
