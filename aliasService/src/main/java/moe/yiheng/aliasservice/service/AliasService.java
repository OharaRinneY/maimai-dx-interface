package moe.yiheng.aliasservice.service;

import moe.yiheng.aliasservice.dto.AliasDto;
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

    Set<AliasDto> getMusicIdsByAlias(String alias);

    void deleteAlias(Integer musicId, String alias);

    List<Alias> getNotPassedAlias();

    void passAlias(Integer id);

    void deleteAliasById(Integer id);
}
