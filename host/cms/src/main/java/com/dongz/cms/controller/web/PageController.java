package com.dongz.cms.controller.web;

import com.dongz.api.cms.PageControllerApi;
import com.dongz.cms.service.PageService;
import com.dongz.framework.domain.cms.CmsPage;
import com.dongz.framework.domain.cms.request.QueryPageRequest;
import com.dongz.framework.domain.cms.response.CmsPageResult;
import com.dongz.framework.model.response.QueryResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author dong
 * @date 2020/3/31 22:53
 * @desc
 */
@RestController
@CrossOrigin
@RequestMapping("/cms/page")
public class PageController implements PageControllerApi{

    @Autowired
    PageService pageService;

    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryResponseResult findList(@PathVariable(name = "page") int page,@PathVariable(name = "size") int size, QueryPageRequest pageRequest) {
        return pageService.findList(page, size, pageRequest);
    }

    //添加页面
    @Override
    @PostMapping("/add")
    public CmsPageResult add(@RequestBody CmsPage cmsPage) {
        return pageService.add(cmsPage);
    }

}
