package com.dongz.cms.controller;

import com.dongz.cms.service.PageService;
import com.dongz.framework.web.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author dong
 * @date 2020/4/3 17:33
 * @desc
 */
@Controller
public class PagePreviewController extends BaseController {
    @Autowired
    PageService pageService;

    //接收到页面id
    @GetMapping(value="/cms/preview/{pageId}")
    public void preview(@PathVariable("pageId")String pageId){
        String pageHtml = pageService.getPageHtml(pageId);
        if(StringUtils.isNotEmpty(pageHtml)){
            try {
                ServletOutputStream outputStream = response.getOutputStream();
                outputStream.write(pageHtml.getBytes(StandardCharsets.UTF_8));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } }
}
