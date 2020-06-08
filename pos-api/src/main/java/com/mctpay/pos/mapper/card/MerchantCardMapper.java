package com.mctpay.pos.mapper.card;

import com.mctpay.common.base.mapper.BaseMapper;
import com.mctpay.pos.model.entity.card.MerchantCardEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dongwei_guo
 * @date 2020-02-23 18:23:01
 */
@Repository
public interface MerchantCardMapper extends BaseMapper<MerchantCardEntity> {

    /**
     * @Description 卡券集合
     * @Date 23:50 2020/5/11
     **/
    List<MerchantCardEntity> listCardByMerchanId(@Param("merchanId") String merchanId);

}
