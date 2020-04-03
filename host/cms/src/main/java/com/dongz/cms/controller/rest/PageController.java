package com.dongz.cms.controller.rest;

import com.dongz.api.cms.PageControllerApi;
import com.dongz.cms.service.PageService;
import com.dongz.framework.domain.cms.CmsPage;
import com.dongz.framework.domain.cms.request.QueryPageRequest;
import com.dongz.framework.domain.cms.response.CmsPageResult;
import com.dongz.framework.model.response.CommonCode;
import com.dongz.framework.model.response.QueryResponseResult;
import com.dongz.framework.model.response.ResponseResult;
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

    @Override
    @GetMapping("/get/{id}")
    public CmsPageResult findById(@PathVariable("id") String id) {
        return new CmsPageResult(CommonCode.SUCCESS, pageService.getById(id));
    }

    //这里使用put方法，http 方法中put表示更新
    @Override
    @PutMapping("/edit/{id}")
    public CmsPageResult edit(@PathVariable("id") String id, @RequestBody CmsPage cmsPage) {
        return pageService.update(id,cmsPage);
    }

    //使用http的delete方法完成岗位操作
    @DeleteMapping("/del/{id}")
    public ResponseResult delete(@PathVariable("id") String id) {
       return pageService.delete(id);
}
}
