package moe.yiheng.aliasservice.service;

import moe.yiheng.entity.alias.Alias;

import java.util.List;
import java.util.Set;

/**
 * @Author rinne
 * @Date 2022/2/27
 */
public interface AliasService {
    List<Alias> findByMusicId(Integer musicId);

    /**
     * @param musicId
     * @param alias
     * @return message
     */
    String addAlias(Integer musicId, String alias);

    List<String> getPassedAliasStr(Integer musicId);

    Set<Integer> getMusicIdsByAlias(String alias);
}
