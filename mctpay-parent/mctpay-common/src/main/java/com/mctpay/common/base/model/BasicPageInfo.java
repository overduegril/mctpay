package com.mctpay.common.base.model;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: guodongwei
 * @Description: 分页信息中的关键信息
 * @Date: 2020/2/26 21:04
 */
@Data
@Api("分页基础信息")
public class BasicPageInfo {

    @ApiModelProperty(value = "当前页")
    private Integer pageNum;
    @ApiModelProperty(value = "每页数据量")
    private Integer pageSize;
    @ApiModelProperty(value = "总数据量")
    private Long totalRow;
    @ApiModelProperty(value = "总页数")
    private Integer totalPage;

}
