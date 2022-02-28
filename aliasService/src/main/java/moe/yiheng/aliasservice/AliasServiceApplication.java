package moe.yiheng.aliasservice;

import moe.yiheng.servicebase.feign.MusicClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author rinne
 * @Date 2022/2/27
 */
@SpringBootApplication
@ComponentScan(basePackages = {"moe.yiheng"})
@EntityScan(basePackages = "moe.yiheng.entity.alias")
@EnableFeignClients(clients = {MusicClient.class})
public class AliasServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AliasServiceApplication.class, args);
    }
}
