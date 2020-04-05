package com.dongz.api.course;

import com.dongz.framework.domain.course.ext.CourseInfo;
import com.dongz.framework.domain.course.request.CourseListRequest;
import com.dongz.framework.model.response.QueryResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author dong
 * @date 2020/4/5 15:42
 * @desc
 */
@Api(tags = "课程管理")
public interface CourseControllerApi {
    @ApiOperation("查询我的课程列表")
    QueryResponseResult<CourseInfo> findCourseList(
            int page,
            int size,
            CourseListRequest courseListRequest
    );
}
