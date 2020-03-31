package com.dongz.api.cms;

import com.dongz.framework.domain.cms.request.QueryPageRequest;
import com.dongz.framework.model.response.QueryResponseResult;

/**
 * @author dong
 * @date 2020/3/31 17:31
 * @desc
 */
public interface PageControllerApi {

    /**
     * 查询列表
     * @param page
     * @param size
     * @param pageRequest
     * @return
     */
    QueryResponseResult findList(
            int page, int size, QueryPageRequest pageRequest
    );
}
