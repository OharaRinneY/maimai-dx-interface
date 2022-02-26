package moe.yiheng.musicservice.service;

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
}
