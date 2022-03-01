package moe.yiheng.aliasservice.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import moe.yiheng.aliasservice.dto.AliasDto;
import moe.yiheng.aliasservice.repository.AliasRepository;
import moe.yiheng.aliasservice.service.AliasService;
import moe.yiheng.entity.alias.Alias;
import moe.yiheng.servicebase.exceptionhandler.MyException;
import moe.yiheng.servicebase.feign.MusicClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author rinne
 * @Date 2022/2/27
 */
@Service
public class AliasServiceImpl implements AliasService {
    @Autowired
    private AliasRepository repository;
    @Autowired
    MusicClient musicClient;

    @Override
    public List<Alias> findByMusicId(Integer musicId) {
        return repository.findByMusicId(musicId);
    }

    @Override
    @Transactional
    public String addAlias(Integer musicId, String alias) {
        // if music does not exist, throw exception
        musicClient.getMusic(musicId);
        // if alias exists:
        for (Alias a : repository.findByMusicId(musicId)) {
            if (a.getAlias().equals(alias)) {
                if (a.getPassed()) {
                    // passed
                    throw new MyException(400, "该别名已存在");
                } else {
                    // not passed
                    // if logged in, pass it
                    if (StpUtil.isLogin()) {
                        a.setPassed(true);
                        return "别名已通过审核";
                    }
                    throw new MyException(400, "别名已在待审核列表中");
                }
            }
        }
        Alias.AliasBuilder aliasBuilder = Alias.builder()
                .musicId(musicId)
                .alias(alias)
                .passed(StpUtil.isLogin());
        repository.save(aliasBuilder.build());
        return StpUtil.isLogin() ? "添加成功" : "已添加到待审核列表";
    }

    @Override
    public List<String> getPassedAliasStr(Integer musicId) {
        List<Alias> alias = repository.findAliasByMusicIdAndPassed(musicId, true);
        List<String> aliasStr = new ArrayList<>();
        alias.forEach(a -> aliasStr.add(a.getAlias()));
        return aliasStr;
    }

    @Override
    public Set<AliasDto> getMusicIdsByAlias(String alias) {
        List<Alias> aliases = repository.findAllByAliasContainingIgnoreCase(alias);
        Set<AliasDto> aliasDtos = new HashSet<>();
        aliases.forEach(a -> {
            // check if exists(copilot写的)
            boolean exists = aliasDtos.stream().anyMatch(ad -> ad.getMusicId().equals(a.getMusicId()));
            if (!exists) {
                aliasDtos.add(new AliasDto(a.getMusicId(), a.getAlias()));
            }
        });
        return aliasDtos;
    }

    @Override
    @Transactional
    public void deleteAlias(Integer musicId, String alias) {
        // copilot 写的 有事问 copilot
        Alias a = repository.findByMusicIdAndAlias(musicId, alias);
        if (a == null) {
            throw new MyException(400, "别名不存在");
        }
        repository.delete(a);
    }

    @Override
    public List<Alias> getNotPassedAlias() {
        return repository.findAllByPassed(false);
    }

    @Override
    @Transactional
    public void passAlias(Integer id) {
        // copilot写的
        Alias a = repository.findById(id).orElseThrow(() -> new MyException(404, "别名不存在"));
        a.setPassed(true);
    }

    @Override
    @Transactional
    public void deleteAliasById(Integer id) {
        repository.deleteById(id);
    }
}
