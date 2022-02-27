package moe.yiheng.aliasservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * @Author rinne
 * @Date 2022/2/27
 */
@SpringBootApplication
@EntityScan(basePackages = "moe.yiheng.entity.alias")
public class AliasServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AliasServiceApplication.class, args);
    }
}
