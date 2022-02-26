package moe.yiheng.musicservice.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import moe.yiheng.entity.music.Charts;
import moe.yiheng.entity.music.Music;
import moe.yiheng.musicservice.service.MusicService;
import moe.yiheng.servicebase.Payload;
import moe.yiheng.servicebase.exceptionhandler.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author rinne
 * @Date 2022/2/26
 */
@RestController
@RequestMapping("chart")
@Api(tags =  "谱面查询接口")
public class ChartController {

    @Autowired
    MusicService service;

    @GetMapping("{id}/{difficulty}")
    @ApiOperation("获取指定乐曲的指定谱面")
    public Payload getChart(
            @ApiParam("歌曲id") @PathVariable("id") Integer id,
            @ApiParam("难度，从1-5") @PathVariable("difficulty") Integer difficulty) {
        Music music = service.getById(id, true);
        // 不优雅，想办法重构
        Charts charts = music.getCharts();
        switch (difficulty) {
            case 1:
                return Payload.success().data("chart", charts.getBasic());
            case 2:
                return Payload.success().data("chart", charts.getAdvanced());
            case 3:
                return Payload.success().data("chart", charts.getExpert());
            case 4:
                return Payload.success().data("chart", charts.getMaster());
            case 5:
                if (charts.getRemaster() == null) {
                    throw new MyException(400, "难度不存在");
                }
                return Payload.success().data("chart", charts.getRemaster());
            default:
                throw new MyException(400, "难度不存在");
        }
    }
}
