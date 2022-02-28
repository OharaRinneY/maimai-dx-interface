package moe.yiheng.aliasservice.controller;

import cn.dev33.satoken.stp.StpUtil;
import moe.yiheng.aliasservice.service.AliasService;
import moe.yiheng.entity.alias.Alias;
import moe.yiheng.entity.music.Music;
import moe.yiheng.servicebase.Payload;
import moe.yiheng.servicebase.feign.MusicClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author rinne
 * @Date 2022/2/27
 */
@RestController
@RequestMapping("/alias")
@ComponentScan(basePackages = "moe.yiheng")
public class AliasController {
    @Autowired
    private AliasService service;

    @GetMapping("{id}")
    public Payload<List<String>> getAlias(@PathVariable("id") Integer musicId) {
        return Payload.success(service.getPassedAliasStr(musicId));
    }

    @PostMapping("{id}")
    public Payload<Object> addAlias(@PathVariable("id") Integer musicId, @RequestBody String alias) {
        String message = service.addAlias(musicId, alias);
        return Payload.success(null).message(message);
    }
}
