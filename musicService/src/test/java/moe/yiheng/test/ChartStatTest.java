package moe.yiheng.test;

import moe.yiheng.musicservice.MusicServiceApplication;
import moe.yiheng.musicservice.service.MusicService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author rinne
 * @Date 2022/3/1
 */
@SpringBootTest(classes = MusicServiceApplication.class)
public class ChartStatTest {
    @Autowired
    MusicService service;

    @Test
    public void test() {
        service.refreshChartStat();
    }
}
