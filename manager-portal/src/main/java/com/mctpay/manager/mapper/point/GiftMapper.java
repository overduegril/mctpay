package com.mctpay.manager.mapper.point;

import com.mctpay.common.base.mapper.BaseMapper;
import com.mctpay.manager.model.entity.point.GiftEntity;
import com.mctpay.manager.model.param.GiftParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dongwei_guo
 * @date 2020-02-23 18:23:01
 */
@Repository
public interface GiftMapper extends BaseMapper<GiftEntity> {
    /**
     * @Description 插入积分商品
     * @Date 16:45 2020/2/26
     **/
    void insertGift(GiftParam giftParam);
    /**
     * @Description  列表积分商品
     * @Date 16:02  2020/2/27
     **/
    List<GiftEntity> listGiftByInput(@Param("inputContent") String inputContent);
    /**
     * @Description  冻结激活积分商品
     * @Date 16:40  2020/2/27
     **/
    void updateSwitchGift(@Param("giftId") String giftId , @Param("state") Integer  state);
}
