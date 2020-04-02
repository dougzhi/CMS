package com.dongz.cms.dao;

import com.dongz.framework.domain.cms.CmsPage;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author dong
 * @date 2020/4/1 00:12
 * @desc
 */
public interface PageRepository extends MongoRepository<CmsPage,String> {

    //根据页面名称、站点id、页面访问路径查询
    CmsPage findByPageNameAndSiteIdAndPageWebPath(String pageName,String siteId,String
            pageWebPath);
}
