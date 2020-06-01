package com.mctpay.wallet.mapper.point;

import com.mctpay.common.base.mapper.BaseMapper;
import com.mctpay.manager.model.param.PointResetParam;
import com.mctpay.wallet.model.entity.point.PointResetEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author dongwei_guo
 * @date 2020-04-02 19:44:26
 */
@Repository
public interface PointResetMapper extends BaseMapper<PointResetEntity> {

    /**
     * 插入积分重置设置
     * @param pointResetParam
     */
    void insert(PointResetParam pointResetParam);

    /**
     * 分页查询积分重置设置
     */
    List<PointResetEntity> listPointReset();

    /**
     * 更新积分重置设置
     */
    void switchPointReset(@Param("operator") String operator, @Param("pointResetId") String pointResetId, @Param("state") Integer state, @Param("updateTime") Date updateTime);
}
