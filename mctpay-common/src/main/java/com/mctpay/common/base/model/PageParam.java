package com.mctpay.common.base.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: guodongwei
 * @Description: 分页参数
 * @Date: 2020/2/26 20:01
 */
@ApiModel(value = "分页查询类")
public class PageParam implements Serializable{
    private static final long serialVersionUID = 1L;
    /**
     * 每页记录数
     */
    @ApiModelProperty(value = "每页记录数")
    private Integer pageSize;

    /**
     * 起始页
     */
    @ApiModelProperty(value = "起始页")
    private Integer pageNum;

    /**
     * 排序规则
     */
    @ApiModelProperty(value = "排序规则")
    private String order;

    public Integer getPageSize() {
        if (pageSize == null || pageSize == 0) {
            this.pageSize = 5;
        }
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        if (pageNum == null || pageNum == 0) {
            this.pageNum = 1;
        }

        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
