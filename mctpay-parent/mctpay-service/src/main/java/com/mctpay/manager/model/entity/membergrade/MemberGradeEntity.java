package com.mctpay.manager.model.entity.membergrade;
import com.mctpay.common.base.entity.SupperEntity;
import com.mctpay.manager.keyvalue.StatusEnum;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author liqiang
 * @date 2020/4/22 16:03
 * @Description: (what)
 * (why)
 * (how)
 */
@Data
@ApiModel(value = "会员等级")
@Table(name = "member_grade")
public class MemberGradeEntity extends SupperEntity implements Serializable {
    @Id
    private String id;
    /**
     * 等级名字
     */
    private String name;

    /**
     * 对应积分
     */
    private Double points;

    /**
     * 折扣比例
     */
    private Double discountRate;

    /**
     * 状态
     */
    private StatusEnum status;


    /**
     * 所属商户
     */
    private String merchantId;
}

