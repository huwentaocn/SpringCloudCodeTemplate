package com.hwt.mybatisPlus.page;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author : wmm
 * date : 2023-04-10 16:09
 * description : 公共分页请求参数
 */
@Data
public class PageParam {


    /**
     * 页码
     */
    @Max(Integer.MAX_VALUE)
    @Min(Integer.MIN_VALUE)
    private Integer pageNum = 1;

    /**
     * 每页条数
     */
    @Max(Integer.MAX_VALUE)
    @Min(Integer.MIN_VALUE)
    private Integer pageSize = 10;


    /**
     * 模糊查询字段
     */

    private String queryName;

}
