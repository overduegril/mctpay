package com.mctpay.wallet.mapper.template;

import com.mctpay.common.base.mapper.BaseMapper;
import com.mctpay.wallet.model.entity.template.ReserveEntity;
import com.mctpay.wallet.model.param.ReserveParam;
import org.springframework.stereotype.Repository;

/**
 * @author dongwei_guo
 * @date 2020-05-30 11:06:07
 */
@Repository
public interface ReserveMapper extends BaseMapper<ReserveEntity> {

    /**
     * 插入预约信息
     *
     * @param reserveParam
     */
    void insert(ReserveParam reserveParam);
	
}
