package moe.yiheng.aliasservice.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import moe.yiheng.aliasservice.service.AliasService;
import moe.yiheng.entity.alias.Alias;
import moe.yiheng.servicebase.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * @Author rinne
 * @Date 2022/2/27
 */
@RestController
@RequestMapping("/alias")
@Api(tags = "乐曲别名服务")
@ComponentScan(basePackages = "moe.yiheng")
public class AliasController {
    @Autowired
    private AliasService service;

    @ApiOperation("根据乐曲id获取别名")
    @GetMapping("{music_id}")
    public Payload<List<String>> getAliasByMusicId(@PathVariable("music_id") Integer musicId) {
        return Payload.success(service.getPassedAliasStr(musicId));
    }

    @ApiOperation("添加别名，无token则将别名加入待审核列表")
    @PostMapping("{music_id}")
    public Payload<Object> addAlias(@PathVariable("music_id") Integer musicId, String alias) {
        String message = service.addAlias(musicId, alias);
        return Payload.success(null).message(message);
    }

    @ApiOperation("根据别名获取乐曲")
    @GetMapping("/query/{alias}")
    public Payload<Set<Integer>> getMusicIdsByAlias(@PathVariable("alias") String alias) {
        return Payload.success(service.getMusicIdsByAlias(alias));
    }

    @SaCheckLogin
    @DeleteMapping("/{musicId}/{alias}")
    @ApiOperation("删除别名")
    public Payload<Object> deleteAlias(@PathVariable("musicId") Integer musicId, @PathVariable("alias") String alias) {
        service.deleteAlias(musicId, alias);
        return Payload.success(null);
    }

    @SaCheckLogin
    @DeleteMapping("/{id}")
    @ApiOperation("根据id删除别名")
    public Payload<Object> deleteAlias(@ApiParam("别名的id，并不是乐曲id") @PathVariable("id") Integer id) {
        service.deleteAliasById(id);
        return Payload.success(null);
    }

    @SaCheckLogin
    @GetMapping("not_passed")
    @ApiOperation("获取尚未通过审核的别名")
    public Payload<List<Alias>> getNotPassedAlias() {
        return Payload.success(service.getNotPassedAlias());
    }

    @SaCheckLogin
    @GetMapping("pass/{id}")
    @ApiOperation("通过别名审核")
    public Payload<Object> passAlias(@PathVariable("id") Integer id) {
        service.passAlias(id);
        return Payload.success(null);
    }
}
