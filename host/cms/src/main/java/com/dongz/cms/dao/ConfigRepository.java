package com.dongz.cms.dao;

import com.dongz.framework.domain.cms.CmsConfig;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author dong
 * @date 2020/4/3 16:27
 * @desc
 */
public interface ConfigRepository extends MongoRepository<CmsConfig, String> {

}
