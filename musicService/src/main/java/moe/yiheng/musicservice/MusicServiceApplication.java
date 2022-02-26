package moe.yiheng.musicservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author rinne
 * @Date 2022/2/26
 */
@SpringBootApplication
@ComponentScan(basePackages = {"moe.yiheng"})
public class MusicServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MusicServiceApplication.class, args);
    }
}
