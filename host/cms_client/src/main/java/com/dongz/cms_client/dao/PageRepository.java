package com.dongz.cms_client.dao;

import com.dongz.framework.domain.cms.CmsPage;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author dong
 * @date 2020/4/4 23:17
 * @desc
 */
public interface PageRepository extends MongoRepository<CmsPage, String> {

}
