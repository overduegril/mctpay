package com.mctpay.common.base.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author liqiang
 * @date 2020/4/22 16:35
 * @Description: (what)
 * (why)
 * (how)
 */
@Data
public class SupperEntity {
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间", hidden = true)
    private Date createTime;

    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间", hidden = true)
    private Date updateTime;

    /*
      *创建人
     */
    private String createUserId;

    /**
     *最后修改人
     */
    private String lastUpdateUserId;
}
