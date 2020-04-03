package com.dongz.cms.dao;

import com.dongz.framework.domain.cms.CmsTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author dong
 * @date 2020/4/3 17:23
 * @desc
 */
public interface TemplateRepository extends MongoRepository<CmsTemplate, String> {

}
