package moe.yiheng.aliasservice.controller;

import moe.yiheng.aliasservice.service.AliasService;
import moe.yiheng.entity.alias.Alias;
import moe.yiheng.servicebase.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        List<Alias> alias = service.findByMusicId(musicId);
        List<String> aliasStrList = new ArrayList<>();
        alias.forEach(a -> aliasStrList.add(a.getAlias()));
        return Payload.success(aliasStrList);
    }
}
