package moe.yiheng.aliasservice.service.impl;

import moe.yiheng.aliasservice.repository.AliasRepository;
import moe.yiheng.aliasservice.service.AliasService;
import moe.yiheng.entity.alias.Alias;
import moe.yiheng.servicebase.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author rinne
 * @Date 2022/2/27
 */
@Service
public class AliasServiceImpl implements AliasService {
    @Autowired
    private AliasRepository repository;

    @Override
    public List<Alias> findByMusicId(Integer musicId) {
        return repository.findByMusicId(musicId);
    }
}
