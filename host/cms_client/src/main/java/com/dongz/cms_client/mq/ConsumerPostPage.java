package com.dongz.cms_client.mq;

import com.alibaba.fastjson.JSON;
import com.dongz.cms_client.dao.PageRepository;
import com.dongz.cms_client.service.PageService;
import com.dongz.framework.domain.cms.CmsPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

/**
 * @author dong
 * @date 2020/4/4 23:30
 * @desc
 */
@Component
public class ConsumerPostPage {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerPostPage.class);
    @Autowired
    PageRepository pageRepository;
    @Autowired
    PageService pageService;

    @RabbitListener(queues={"${mq.queue}"})
    public void postPage(String msg){
        //解析消息
        Map map = JSON.parseObject(msg, Map.class);
        //取出页面id
        LOGGER.info("receive cms post page:{}", msg);
        String pageId = (String) map.get("pageId");
        //查询页面信息
        Optional<CmsPage> optional = pageRepository.findById(pageId);
        if(!optional.isPresent()){
            LOGGER.error("receive cms post page,cmsPage is null:{}", msg);
            return ;
        }
        //将页面保存到服务器物理路径
        pageService.savePageToServerPath(pageId);
    }
}
