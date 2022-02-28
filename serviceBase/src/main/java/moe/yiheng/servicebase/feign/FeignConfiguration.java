package moe.yiheng.servicebase.feign;

import feign.codec.Decoder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author rinne
 * @Date 2022/2/28
 */
@Configuration
public class FeignConfiguration {
    @Bean
    public Decoder feignDecoder() {
        return new FeignPayloadDecoder();
    }
}
