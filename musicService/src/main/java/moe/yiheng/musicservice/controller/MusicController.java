package moe.yiheng.musicservice.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import moe.yiheng.entity.music.Music;
import moe.yiheng.musicservice.service.MusicService;
import moe.yiheng.musicservice.dto.QueryConditions;
import moe.yiheng.servicebase.Payload;
import moe.yiheng.servicebase.dto.PageDTO;
import moe.yiheng.servicebase.exceptionhandler.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @SaCheckLogin
    @ApiOperation("从指定url获取乐曲列表，需有效jwt")
    @PostMapping("refresh")
    public Payload<Integer> refresh(String url) {
        // "https://maimai.ohara-rinne.tech/api/maimaidxprober/music_data"
        Integer count = musicService.refresh(url);
        return Payload.success(count);
    }

    @GetMapping("{id}")
    @ApiOperation(value = "获取乐曲信息")
    public Payload<Music> getMusic(
            @PathVariable("id") @ApiParam(value = "乐曲id", required = true) Integer id,
            @ApiParam(value = "是否包含谱面信息", required = false) boolean withCharts
    ) {
        Music music = musicService.getById(id, withCharts);
        return Payload.success(music);
    }

    @PostMapping("query/{page}/{size}")
    @ApiOperation(value = "条件查询")
    public Payload<PageDTO<Music>> queryMusic(
            @ApiParam(value = "查询条件", required = true) @RequestBody QueryConditions conditions,
            @ApiParam(value = "当前页面", required = true) @PathVariable("page") Integer page,
            @ApiParam(value = "每页数量(<=20)", required = true) @PathVariable("size") Integer size
    ) {
        if (size > 20) {
            throw new MyException(400, "最大单页数量为20");
        }
        var resultPage = musicService.query(conditions, page, size);
        return Payload.success(new PageDTO<>(resultPage.getTotalElements(), resultPage.getContent()));
    }
}
