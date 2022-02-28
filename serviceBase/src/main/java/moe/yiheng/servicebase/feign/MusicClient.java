package moe.yiheng.servicebase.feign;

import moe.yiheng.entity.music.Music;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author rinne
 * @Date 2022/2/28
 */
@FeignClient(name = "music-service" , path = "/music")
public interface MusicClient {
    @GetMapping("{id}")
    Music getMusic(@PathVariable("id") Integer id);
}
