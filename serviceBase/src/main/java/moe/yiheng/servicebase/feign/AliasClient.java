package moe.yiheng.servicebase.feign;

import moe.yiheng.entity.music.Music;
import moe.yiheng.servicebase.Payload;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @Author rinne
 * @Date 2022/2/28
 */
@FeignClient(name = "alias-service", path = "/alias")
public interface AliasClient {
    @GetMapping("{music_id}")
    List<String> getAliasByMusicId(@PathVariable("music_id") Integer musicId);
}