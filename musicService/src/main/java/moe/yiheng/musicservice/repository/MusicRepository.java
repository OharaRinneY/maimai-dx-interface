package moe.yiheng.musicservice.repository;

import moe.yiheng.entity.music.Music;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author rinne
 * @Date 2022/2/26
 */
@Repository
public interface MusicRepository extends PagingAndSortingRepository<Music,Integer> {
}
