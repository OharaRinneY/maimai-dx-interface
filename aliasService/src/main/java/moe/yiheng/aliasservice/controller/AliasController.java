package moe.yiheng.aliasservice.controller;

import moe.yiheng.aliasservice.service.AliasService;
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

    @GetMapping("/query/{alias}")
    public Payload<Set<Integer>> getMusicIdsByAlias(@PathVariable("alias") String alias) {
        return Payload.success(service.getMusicIdsByAlias(alias));
    }
}
