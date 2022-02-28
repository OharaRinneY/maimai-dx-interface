package moe.yiheng.aliasservice.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import moe.yiheng.aliasservice.repository.AliasRepository;
import moe.yiheng.aliasservice.service.AliasService;
import moe.yiheng.entity.alias.Alias;
import moe.yiheng.servicebase.exceptionhandler.MyException;
import moe.yiheng.servicebase.feign.MusicClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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


}
