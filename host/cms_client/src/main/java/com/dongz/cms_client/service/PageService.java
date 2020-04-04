package com.dongz.cms_client.service;

import com.dongz.cms_client.dao.PageRepository;
import com.dongz.cms_client.dao.SiteRepository;
import com.dongz.framework.domain.cms.CmsPage;
import com.dongz.framework.domain.cms.CmsSite;
import com.dongz.framework.domain.cms.response.CmsCode;
import com.dongz.framework.exception.ExceptionCast;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

/**
 * @author dong
 * @date 2020/4/4 23:19
 * @desc
 */
@Service
public class PageService {

    @Autowired
    PageRepository pageRepository;
    @Autowired
    SiteRepository siteRepository;
    @Autowired
    GridFsTemplate gridFsTemplate;
    @Autowired
    GridFSBucket gridFSBucket;
    /**
     /将页面html保存到页面物理路径
     */
    public void savePageToServerPath(String pageId){
        Optional<CmsPage> optional = pageRepository.findById(pageId);
        if(!optional.isPresent()){
            ExceptionCast.cast(CmsCode.CMS_PAGE_NOTEXISTS);
        }
        //取出页面物理路径
        CmsPage cmsPage = optional.get();
        //页面所属站点
        CmsSite cmsSite = this.getCmsSiteById(cmsPage.getSiteId());
        //页面物理路径
//        String pagePath = cmsSite.getSitePhysicalPath() + cmsPage.getPagePhysicalPath() +
        String pagePath = cmsPage.getPagePhysicalPath() +
                cmsPage.getPageName(); //查询页面静态文件
        String htmlFileId = cmsPage.getHtmlFileId();

        try (InputStream inputStream = this.getFileById(htmlFileId)){
            if(inputStream == null){
                ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_HTMLISNULL);
            }
            try (FileOutputStream fileOutputStream = new FileOutputStream(new File(pagePath))){
                //将文件内容保存到服务物理路径
                IOUtils.copy(inputStream,fileOutputStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     /根据文件id获取文件内容
     */
    public InputStream getFileById(String fileId){
        try {
            GridFSFile gridFSFile =
                    gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(fileId)));
            GridFSDownloadStream gridFSDownloadStream =
                    gridFSBucket.openDownloadStream(gridFSFile.getObjectId());
            GridFsResource gridFsResource = new GridFsResource(gridFSFile,gridFSDownloadStream);
            return gridFsResource.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     /根据站点id得到站点
     */
    public CmsSite getCmsSiteById(String siteId){
        Optional<CmsSite> optional = siteRepository.findById(siteId);
        if(optional.isPresent()){
            CmsSite cmsSite = optional.get();
            return cmsSite;
        }
        return null;
    }
}
