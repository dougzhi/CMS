package com.dongz.cms.web.controller;

import com.dongz.api.cms.PageControllerApi;
import com.dongz.cms.service.PageService;
import com.dongz.framework.domain.cms.request.QueryPageRequest;
import com.dongz.framework.model.response.QueryResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dong
 * @date 2020/3/31 22:53
 * @desc
 */
@RestController
@RequestMapping("/cms/page")
public class PageController implements PageControllerApi{

    @Autowired
    PageService pageService;

    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryResponseResult findList(@PathVariable(name = "page") int page,@PathVariable(name = "size") int size, QueryPageRequest pageRequest) {
        return pageService.findList(page, size, pageRequest);
    }

}
