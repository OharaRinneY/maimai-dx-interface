package moe.yiheng.test;

import com.ejlchina.okhttps.HTTP;
import com.ejlchina.okhttps.jackson.JacksonMsgConvertor;
import moe.yiheng.entity.music.Music;
import moe.yiheng.musicservice.MusicServiceApplication;
import moe.yiheng.musicservice.jsonEntities.RawMusicData;
import moe.yiheng.musicservice.repository.MusicRepository;
import moe.yiheng.musicservice.service.MusicService;
import moe.yiheng.musicservice.utils.MusicConverter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Author rinne
 * @Date 2022/2/26
 */
@SpringBootTest(classes = {MusicServiceApplication.class})
public class RefreshTest {
    @Autowired
    MusicService musicService;

    @Autowired
    MusicRepository repository;
    @Test
    public void testConverter() {
        HTTP http = HTTP.builder()
                .addMsgConvertor(new JacksonMsgConvertor())
                .build();
        List<RawMusicData> musicData = http.sync("https://maimai.ohara-rinne.tech/api/maimaidxprober/music_data")
                .get()
                .getBody()
                .toList(RawMusicData.class);
        Music music = MusicConverter.convert(musicData.get(0));
        System.out.println(music);
        repository.save(music);
    }

    @Test
    public void testService() {
//        musicService.refresh("https://maimai.ohara-rinne.tech/api/maimaidxprober/music_data");
    }
}
