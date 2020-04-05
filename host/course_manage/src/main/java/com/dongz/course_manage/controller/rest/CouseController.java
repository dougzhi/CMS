package com.dongz.course_manage.controller.rest;

import com.dongz.api.course.CourseControllerApi;
import com.dongz.framework.domain.course.ext.CourseInfo;
import com.dongz.framework.domain.course.request.CourseListRequest;
import com.dongz.framework.model.response.QueryResponseResult;
import com.dongz.framework.web.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dong
 * @date 2020/4/5 16:14
 * @desc
 */
@RestController
@RequestMapping("/course")
public class CouseController extends BaseController implements CourseControllerApi {

    @Override
    @GetMapping("/courseBase/list/{page}/{size}")
    public QueryResponseResult<CourseInfo> findCourseList(
            @PathVariable int page,@PathVariable int size,
          CourseListRequest courseListRequest) {
        Map<String, Object> params = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        sb.append("select t.*,p.pic from course_base t left join course_pic p on t.id = p.courseid where 1= 1 ");
        if (courseListRequest != null) {
            if (StringUtils.isNoneBlank(courseListRequest.getCompanyId())) {
                sb.append(" and t.company_id = :companyId ");
                params.put("companyId", courseListRequest.getCompanyId());
            }
        }
        return this.queryForPagination(sb, params, page, size, CourseInfo.class);
    }
}
