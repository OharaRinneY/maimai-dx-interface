package moe.yiheng.musicservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author rinne
 * @Date 2022/2/26
 */
@SpringBootApplication
@ComponentScan(basePackages = {"moe.yiheng"})
@EntityScan(basePackages = {"moe.yiheng.entity.music"})
public class MusicServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MusicServiceApplication.class, args);
    }
}
