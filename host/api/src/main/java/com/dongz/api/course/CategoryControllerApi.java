package com.dongz.api.course;

import com.dongz.framework.domain.course.ext.CategoryNode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author dong
 * @date 2020/4/5 18:44
 * @desc
 */
@Api(tags = "课程分类管理")
public interface CategoryControllerApi {

    @ApiOperation("查询分类")
    CategoryNode findList();
}
