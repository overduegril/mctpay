package com.mctpay.wallet.mapper.system;

import com.mctpay.common.base.mapper.BaseMapper;
import com.mctpay.wallet.model.entity.system.ShippingAddressEntity;
import com.mctpay.wallet.model.param.ShippingAddressParam;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dongwei_guo
 * @date 2020-06-12 17:03:13
 */
@Repository
public interface ShippingAddressMapper extends BaseMapper<ShippingAddressEntity> {

    /**
     * 统计地址数量
     * @param shippingAddressParam
     * @return
     */
    Integer count(ShippingAddressParam shippingAddressParam);

    /**
     * 更新地址
     * @param shippingAddressParam
     */
    void update(ShippingAddressParam shippingAddressParam);

    /**
     * 刪除地址
     * @param id
     */
    void delete(Long id);

    /**
     * 批量刪除地址
     * @param ids
     */
    void deleteBatch(List<Long> ids);

    /**
     * 新增地址
     * @param shippingAddressParam
     */
    void insert(ShippingAddressParam shippingAddressParam);
}
