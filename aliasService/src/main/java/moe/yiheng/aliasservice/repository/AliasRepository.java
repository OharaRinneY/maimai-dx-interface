package moe.yiheng.aliasservice.repository;

import moe.yiheng.entity.alias.Alias;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author rinne
 * @Date 2022/2/27
 */
public interface AliasRepository extends JpaRepository<Alias, Integer> {
    List<Alias> findByMusicId(Integer musicId);

    List<Alias> findAliasByMusicIdAndPassed(Integer musicId, Boolean passed);

}
