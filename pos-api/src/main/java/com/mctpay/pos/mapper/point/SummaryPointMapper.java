package com.mctpay.pos.mapper.point;

import com.mctpay.common.base.mapper.BaseMapper;
import com.mctpay.pos.model.entity.point.SummaryPointEntity;
import com.mctpay.pos.model.param.SummaryPointParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author dongwei_guo
 * @date 2020-02-23 18:23:01
 */
@Repository
public interface SummaryPointMapper extends BaseMapper<SummaryPointEntity> {

    /**
     * 积分增加
     * @param userId
     * @param incCount
     */
    void incPoint(@Param("userId") String userId, @Param("incCount") Integer incCount);

}
