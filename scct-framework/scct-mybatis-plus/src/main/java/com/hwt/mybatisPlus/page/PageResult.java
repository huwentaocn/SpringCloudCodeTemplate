package com.hwt.mybatisPlus.page;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * description : mybatis-plus 分页封装
 * @author Hu Wentao
 */
@Data
@NoArgsConstructor
public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 总条数
     */
    private long total;

    /**
     * 每页条数
     */
    private long pageNum;

    /**
     * 当前页
     */
    private long pageSize;

    /**
     * 总页数
     */
    private long pages;


    /**
     * 查询数据列表
     */
    private List<T> list;

    /**
     * 是否存在上一页
     */
    private boolean hasPreviousPage;

    /**
     * 是否存在下一页
     */
    private boolean hasNextPage;

    /**
     * 结果赋值
     */
    public PageResult(Page<T> page) {
        this.pageNum = page.getCurrent();
        this.pageSize = page.getSize();
        this.total = page.getTotal();
        this.pages = page.getPages();
        this.list = page.getRecords();
        this.hasPreviousPage = page.hasPrevious();
        this.hasNextPage = page.hasNext();
    }


    /**
     * 结果赋值
     */
    public PageResult(Page page, List<T> list) {
        this.pageNum = page.getCurrent();
        this.pageSize = page.getSize();
        this.total = page.getTotal();
        this.pages = page.getPages();
        this.list = list;
        this.hasPreviousPage = page.hasPrevious();
        this.hasNextPage = page.hasNext();
    }

    /**
     * 结果赋值转换
     */
    public PageResult(Page page, Class<T> clazz) {
        this.pageNum = page.getCurrent();
        this.pageSize = page.getSize();
        this.total = page.getTotal();
        this.pages = page.getPages();
        List records = page.getRecords();
        List<T> list = (List<T>) records.stream().map(item -> {
            try {
                T t = clazz.getDeclaredConstructor().newInstance();
                BeanUtils.copyProperties(item, t);
                return t;
            } catch (Exception e) {
                // 处理异常情况
                e.printStackTrace();
                return null;
            }
        }).collect(Collectors.toList());
        this.list = list;
        this.hasPreviousPage = page.hasPrevious();
        this.hasNextPage = page.hasNext();
    }
}
