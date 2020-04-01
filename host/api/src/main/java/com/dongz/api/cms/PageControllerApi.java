package com.dongz.api.cms;

import com.dongz.framework.domain.cms.request.QueryPageRequest;
import com.dongz.framework.model.response.QueryResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @author dong
 * @date 2020/3/31 17:31
 * @desc
 */
@Api(value="cms页面管理接口")
public interface PageControllerApi {

    /**
     * 查询列表
     * @param page
     * @param size
     * @param pageRequest
     * @return
     */
    @ApiOperation("分页查询页面列表") @ApiImplicitParams({
            @ApiImplicitParam(name="page",value = "页 码",required=true,paramType="path",dataType="int"),
            @ApiImplicitParam(name="size",value = "每页记录 数",required=true,paramType="path",dataType="int")
    })
    QueryResponseResult findList(
            int page, int size, QueryPageRequest pageRequest
    );
}
