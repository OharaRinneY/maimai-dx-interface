package moe.yiheng.musicservice.service.impl;

import com.ejlchina.okhttps.HTTP;
import com.ejlchina.okhttps.jackson.JacksonMsgConvertor;
import lombok.extern.slf4j.Slf4j;
import moe.yiheng.entity.music.*;
import moe.yiheng.musicservice.jsonEntities.RawMusicData;
import moe.yiheng.musicservice.repository.MusicRepository;
import moe.yiheng.musicservice.service.MusicService;
import moe.yiheng.musicservice.utils.MusicConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author rinne
 * @Date 2022/2/26
 */
@Service
@Slf4j
public class MusicServiceImpl implements MusicService {
    @Autowired
    MusicRepository repository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer refresh(String url) {
        HTTP http = HTTP.builder()
                .addMsgConvertor(new JacksonMsgConvertor())
                .build();
        List<RawMusicData> musicData = http.sync(url)
                .get()
                .getBody()
                .toList(RawMusicData.class);
        log.debug("get {} musics", musicData.size());
        log.debug(musicData.get(0).toString());
        musicData.forEach(rawMusicData -> {
            Music music = MusicConverter.convert(rawMusicData);
            if (!repository.existsById(music.getId())){
                repository.save(music);
            }
        });

        return musicData.size();
    }
}
