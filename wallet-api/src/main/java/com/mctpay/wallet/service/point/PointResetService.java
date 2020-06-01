package com.mctpay.wallet.service.point;



import com.mctpay.wallet.model.dto.point.PointResetDTO;
import com.mctpay.wallet.model.param.PointResetParam;

import java.util.List;

/**
 * @Author: guodongwei
 * @Description: 积分重置业务接口
 * @Date: 2020/4/2 19:58
 */
public interface PointResetService {

    /**
     * 插入积分重置设置
     * @param pointResetParam
     */
   void insertPointReset(PointResetParam pointResetParam);

    /**
     * 分页查询积分重置设置
     * @return
     */
    List<PointResetDTO> listPointReset();

    /**
     * 更新积分等级设置记录状态
     * @param pointResetId
     * @param state
     */
    void switchPointReset(String pointResetId, Integer state);
}
