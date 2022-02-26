package moe.yiheng.test;

import moe.yiheng.entity.music.Music;
import moe.yiheng.musicservice.MusicServiceApplication;
import moe.yiheng.musicservice.repository.MusicRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

/**
 * @Author rinne
 * @Date 2022/2/26
 */
@SpringBootTest(classes = {MusicServiceApplication.class})
public class MusicRepoTest {
    @Autowired
    MusicRepository repository;

    @Test
    public void test() {
        Optional<Music> music = repository.findById(8);
        System.out.println(music.get());
    }
}
