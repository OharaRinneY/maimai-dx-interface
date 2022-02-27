package moe.yiheng.aliasservice.controller;

import moe.yiheng.servicebase.Payload;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author rinne
 * @Date 2022/2/27
 */
@RestController
@RequestMapping("/alias")
@ComponentScan(basePackages = "moe.yiheng")
public class AliasController {

}
