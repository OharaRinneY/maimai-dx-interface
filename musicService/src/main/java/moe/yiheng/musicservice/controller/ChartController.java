package moe.yiheng.musicservice.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.alibaba.druid.sql.visitor.functions.Char;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import moe.yiheng.entity.music.Chart;
import moe.yiheng.entity.music.Charts;
import moe.yiheng.entity.music.Music;
import moe.yiheng.enums.entity.Difficulty;
import moe.yiheng.musicservice.service.MusicService;
import moe.yiheng.servicebase.Payload;
import moe.yiheng.servicebase.exceptionhandler.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @Author rinne
 * @Date 2022/2/26
 */
@RestController
@RequestMapping("/chart")
@Api(tags =  "谱面查询接口")
public class ChartController {

    @Autowired
    MusicService service;

    @GetMapping("{id}/{difficulty}")
    @ApiOperation("获取指定乐曲的指定谱面")
    public Payload<Chart> getChart(
            @ApiParam("歌曲id") @PathVariable("id") Integer id,
            @ApiParam("难度，从0-4") @PathVariable("difficulty") Integer difficulty) {
        Music music = service.getById(id, true);
        var chart = music.getCharts().getByDifficulty(Difficulty.getById(difficulty));
        if (chart.isEmpty()) {
            throw new MyException(400, "难度不存在");
        }
        return Payload.success(chart.get());
    }

    @SaCheckLogin
    @ApiOperation("刷新谱面统计信息，需有效jwt")
    @PostMapping("refresh")
    public Payload<Integer> refreshChartStat() {
        // "https://maimai.ohara-rinne.tech/api/maimaidxprober/chart_stats"
        Integer count = service.refreshChartStat();
        return Payload.success(count);
    }
}
