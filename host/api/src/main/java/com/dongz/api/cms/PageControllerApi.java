package com.dongz.api.cms;

import com.dongz.framework.domain.cms.CmsPage;
import com.dongz.framework.domain.cms.request.QueryPageRequest;
import com.dongz.framework.domain.cms.response.CmsPageResult;
import com.dongz.framework.model.response.QueryResponseResult;
import com.dongz.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @author dong
 * @date 2020/3/31 17:31
 * @desc
 */
@Api(tags={"cms页面管理接口"})
public interface PageControllerApi {

    /**
     * 查询列表
     *
     * @param page
     * @param size
     * @param pageRequest
     * @return
     */
    @ApiOperation(value = "分页查询页面列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页 码", required = true, defaultValue = "1", paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页记录 数", required = true, paramType = "path", defaultValue = "10", dataType = "int")
    })
    QueryResponseResult findList(
            int page, int size, QueryPageRequest pageRequest
    );

    @ApiOperation("添加页面")
    CmsPageResult add(CmsPage cmsPage);

    @ApiOperation("通过ID查询页面")
    CmsPageResult findById(String id);

    @ApiOperation("修改页面")
    CmsPageResult edit(String id,CmsPage cmsPage);

    @ApiOperation("通过ID删除页面")
    ResponseResult delete(String id);

    @ApiOperation("发布页面")
    ResponseResult post(String pageId);

}
