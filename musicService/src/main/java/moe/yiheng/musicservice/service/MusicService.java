package moe.yiheng.musicservice.service;

import moe.yiheng.entity.music.Music;
import moe.yiheng.musicservice.vo.QueryConditions;
import org.springframework.data.domain.Page;

/**
 * @Author rinne
 * @Date 2022/2/26
 */
public interface MusicService {

    /**
     * 刷新数据库
     * @param url 接口url
     * @return 获取的音乐数量
     */
    Integer refresh(String url);

    Music getById(Integer id, boolean withCharts);

    Page<Music> query(QueryConditions conditions, Integer page, Integer size);
}
