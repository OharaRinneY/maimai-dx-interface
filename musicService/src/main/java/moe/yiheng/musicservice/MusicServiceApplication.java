package moe.yiheng.musicservice;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
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

    /**
     * 解决Hibernate懒加载问题时Jackson报错
     */
    @Bean
    protected com.fasterxml.jackson.databind.Module module() {
        Hibernate5Module hibernate5Module = new Hibernate5Module();
        hibernate5Module.configure(Hibernate5Module.Feature.USE_TRANSIENT_ANNOTATION, false);
        return hibernate5Module;
    }
}
