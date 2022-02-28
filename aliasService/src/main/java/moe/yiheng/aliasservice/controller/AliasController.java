package moe.yiheng.aliasservice.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
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
@ComponentScan(basePackages = "moe.yiheng")
public class AliasController {
    @Autowired
    private AliasService service;

    @GetMapping("{music_id}")
    public Payload<List<String>> getAlias(@PathVariable("music_id") Integer musicId) {
        return Payload.success(service.getPassedAliasStr(musicId));
    }

    @PostMapping("{music_id}")
    public Payload<Object> addAlias(@PathVariable("music_id") Integer musicId, @RequestBody String alias) {
        String message = service.addAlias(musicId, alias);
        return Payload.success(null).message(message);
    }

    @GetMapping("/query/{alias}")
    public Payload<Set<Integer>> getMusicIdsByAlias(@PathVariable("alias") String alias) {
        return Payload.success(service.getMusicIdsByAlias(alias));
    }

    @SaCheckLogin
    @DeleteMapping("/{musicId}/{alias}")
    public Payload<Object> deleteAlias(@PathVariable("musicId") Integer musicId, @PathVariable("alias") String alias) {
        service.deleteAlias(musicId, alias);
        return Payload.success(null);
    }

    @SaCheckLogin
    @DeleteMapping("/{id}")
    public Payload<Object> deleteAlias(@PathVariable("id") Integer id) {
        service.deleteAliasById(id);
        return Payload.success(null);
    }

    @SaCheckLogin
    @GetMapping("not_passed")
    public Payload<List<Alias>> getNotPassedAlias() {
        return Payload.success(service.getNotPassedAlias());
    }

    @SaCheckLogin
    @GetMapping("pass/{id}")
    public Payload<Object> passAlias(@PathVariable("id") Integer id) {
        service.passAlias(id);
        return Payload.success(null);
    }
}
