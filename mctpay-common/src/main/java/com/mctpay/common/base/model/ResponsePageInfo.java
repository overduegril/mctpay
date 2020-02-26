package com.mctpay.common.base.model;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: guodongwei
 * @Description: 分页返回数据时使用
 * @Date: 2020/2/26 20:53
 */
@Data
@ApiModel(value = "分页格式返回")
public class ResponsePageInfo<T> extends ResponseData<T> {

    @ApiModelProperty(value = "分页基础信息")
    private BasicPageInfo basicPageInfo;

    /**
     * @Description 重新成功方法。设置封装分页信息
     * @Date 20:48 2020/2/26
     **/
    public ResponsePageInfo success(T data, PageInfo pageInfo) {
        basicPageInfo = new BasicPageInfo();
        super.result = 0;
        super.data = data;
        basicPageInfo.setPageNum(pageInfo.getPageNum());
        basicPageInfo.setPageSize(pageInfo.getPageSize());
        basicPageInfo.setTotalRow(pageInfo.getTotal());
        basicPageInfo.setTotalPage(pageInfo.getPages());
        return this;
    }
}
