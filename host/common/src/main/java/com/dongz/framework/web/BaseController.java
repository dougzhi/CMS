package com.dongz.framework.web;

import com.dongz.framework.model.response.CommonCode;
import com.dongz.framework.model.response.QueryResponseResult;
import com.dongz.framework.model.response.QueryResult;
import com.dongz.framework.model.response.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by mrt on 2018/5/22.
 */
public abstract class BaseController {
    protected HttpServletRequest request;

    protected HttpServletResponse response;

    protected HttpSession session;

    @Autowired
    protected NamedParameterJdbcTemplate jdbcTemplate;

    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {

        this.request = request;

        this.response = response;

        this.session = request.getSession();
    }


    public <T> T queryForObject(String sql, Map<String, Object> params, Class<T> t) {
        List<T> list = this.jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(t));
        Assert.isTrue(list.size() == 1, "查询条数异常");
        return list.get(0);
    }

    public Map<String,Object> queryForObject(String sql, Map<String, Object> params) {
        List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql, params);
        Assert.isTrue(list.size() == 1, "查询条数异常");
        return list.get(0);
    }

    public <T> List<T> queryForList(String sql, Map<String,Object> params,Class<T> t) {
        return this.jdbcTemplate.queryForList(sql, params, t);
    }

    public List<Map<String, Object>> queryForList(String sql, Map<String, Object> params) {
        return this.jdbcTemplate.queryForList(sql, params);
    }

    public <T> QueryResponseResult<T> queryForPagination(StringBuilder sql, Map<String, Object> params, long page, long size,Class<T> t) {
        if (size <= 0) {
            size = 10;
        }

        long totalRow = this.jdbcTemplate.queryForObject("select count(*) from (" + sql + ") as T", params, Long.class);
        long pageCount = totalRow / size;
        if (totalRow % size > 0) {
            pageCount++;
        }
        if (page <= 0 || page > pageCount) {
            page = 1;
        }

        long offset = (page - 1) * size;

        List<T> data = this.jdbcTemplate
                .query("select * from (" + sql.toString() + ") as T limit " + offset + "," + size, params, new BeanPropertyRowMapper<>(t));
        QueryResult<T> queryResult = new QueryResult<>();
        queryResult.setList(data);
        queryResult.setTotal(totalRow);

        return new QueryResponseResult<T>(CommonCode.SUCCESS, queryResult);
    }

    public QueryResponseResult<Map<String, Object>> queryForPagination(StringBuilder sql, Map<String, Object> params, long page, long size) {
        if (size <= 0) {
            size = 10;
        }

        long totalRow = this.jdbcTemplate.queryForObject("select count(*) from (" + sql + ") as T", params, Long.class);
        long pageCount = totalRow / size;
        if (totalRow % size > 0) {
            pageCount++;
        }
        if (page <= 0 || page > pageCount) {
            page = 1;
        }

        long offset = (page - 1) * size;

        QueryResult queryResult = new QueryResult();

        List<Map<String, Object>> data = this.jdbcTemplate
                .queryForList("select * from (" + sql.toString() + ") as T limit " + offset + "," + size, params);
        queryResult.setList(data);
        queryResult.setTotal(totalRow);

        return new QueryResponseResult<Map<String, Object>>(CommonCode.SUCCESS, queryResult);
    }
}
