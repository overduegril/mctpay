package com.mctpay.wallet.mapper.point;

import com.mctpay.common.base.mapper.BaseMapper;
import com.mctpay.wallet.model.entity.point.UseabelPointEntity;
import com.mctpay.wallet.model.param.UseabelPointParam;
import org.springframework.stereotype.Repository;

/**
 * @author dongwei_guo
 * @date 2020-02-23 18:23:00
 */
@Repository
public interface UseabelPointMapper extends BaseMapper<UseabelPointEntity> {

    void initUseabelPoint(UseabelPointParam useabelPointParam);

}
