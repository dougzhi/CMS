package com.dongz.cms.service;

import com.dongz.cms.dao.ConfigRepository;
import com.dongz.framework.domain.cms.CmsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author dong
 * @date 2020/4/3 16:32
 * @desc
 */
@Service
public class ConfigService {

    @Autowired
    ConfigRepository configRepository; //根据id查询配置管理信息

    public CmsConfig getConfigById(String id){
        Optional<CmsConfig> optional = configRepository.findById(id);
        if(optional.isPresent()){
            CmsConfig cmsConfig = optional.get();
            return cmsConfig;
        }
        return null;
    }
}
