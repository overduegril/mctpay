package com.mctpay.wallet.mapper.point;

import com.mctpay.common.base.mapper.BaseMapper;
import com.mctpay.wallet.model.entity.point.SummaryPointEntity;
import com.mctpay.wallet.model.param.SummaryPointParam;
import org.springframework.stereotype.Repository;

/**
 * @author dongwei_guo
 * @date 2020-02-23 18:23:01
 */
@Repository
public interface SummaryPointMapper extends BaseMapper<SummaryPointEntity> {

    /**
     * @Description 初始化汇总积分
     * @Date 22:00 2020/3/2
     **/
    void initUserSummaryPoint(SummaryPointParam summaryPointParam);

    /**
     * 根据用户ID获取汇总积分信息
     * @param userId
     * @return
     */
    SummaryPointEntity getByUserId(String userId);

    /**
     * 获取下一级所需积分
     * @param point
     */
    Integer getNextNeedPoint(Integer point);
}
