package moe.yiheng.musicservice.service.impl;

import com.ejlchina.okhttps.HTTP;
import com.ejlchina.okhttps.jackson.JacksonMsgConvertor;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.extern.slf4j.Slf4j;
import moe.yiheng.entity.music.*;
import moe.yiheng.musicservice.jsonEntities.RawMusicData;
import moe.yiheng.musicservice.repository.MusicRepository;
import moe.yiheng.musicservice.service.MusicService;
import moe.yiheng.musicservice.utils.ExpressionUtil;
import moe.yiheng.musicservice.utils.MusicConverter;
import moe.yiheng.musicservice.dto.QueryConditions;
import moe.yiheng.servicebase.exceptionhandler.MyException;
import moe.yiheng.servicebase.feign.AliasClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @Author rinne
 * @Date 2022/2/26
 */
@Service
@Slf4j
public class MusicServiceImpl implements MusicService {
    @Autowired
    MusicRepository repository;
    @Autowired
    AliasClient aliasClient;
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
        musicData.forEach(rawMusicData -> {
            Music music = MusicConverter.convert(rawMusicData);
            if (!repository.existsById(music.getId())) {
                repository.save(music);
            }
        });

        return musicData.size();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Music getById(Integer id, boolean withCharts) {
        Optional<Music> musicOptional = repository.findById(id);
        if (musicOptional.isEmpty()) {
            throw new MyException(404, "music not found");
        }
        Music music = musicOptional.get();
        if (withCharts) {
            music.getCharts().toString();
        }
        List<String> alias = aliasClient.getAliasByMusicId(id);
        music.setAlias(alias);
        return music;
    }

    @Override
    public Page<Music> query(QueryConditions conditions, Integer page, Integer size) {
        BooleanExpression expression = ExpressionUtil.generateExpression(conditions);
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Page<Music> all = repository.findAll(expression, PageRequest.of(page - 1, size, sort));
        // add alias
        all.getContent().forEach(music -> {
            music.setAlias(aliasClient.getAliasByMusicId(music.getId()));
        });
        return all;
    }
}
