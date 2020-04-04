package com.dongz.cms_client.dao;

import com.dongz.framework.domain.cms.CmsSite;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author dong
 * @date 2020/4/4 23:19
 * @desc
 */
public interface SiteRepository extends MongoRepository<CmsSite, String> {

}
