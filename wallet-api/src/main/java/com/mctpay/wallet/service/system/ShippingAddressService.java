package com.mctpay.wallet.service.system;

import com.mctpay.common.base.mapper.BaseMapper;
import com.mctpay.wallet.model.dto.system.ShippingAddressDTO;
import com.mctpay.wallet.model.entity.system.ShippingAddressEntity;
import com.mctpay.wallet.model.param.ShippingAddressParam;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: guodongwei
 * @Description: 收货地址业务接口
 * @Date: 2020/6/12 17:29
 */
public interface ShippingAddressService {

    /**
     * 统计地址数量
     * @param shippingAddressParam
     * @return
     */
    Integer countAddress(ShippingAddressParam shippingAddressParam);

    /**
     * 更新地址
     * @param shippingAddressParam
     */
    void updateAddress(ShippingAddressParam shippingAddressParam);

    /**
     * 刪除地址
     * @param id
     */
    void deleteAddress(Long id);

    /**
     * 批量刪除地址
     * @param ids
     */
    void deleteBatchAddress(List<Long> ids);

    /**
     * 获取收货地址集合
     * @return shippingAddressParam
     */
    List<ShippingAddressDTO> listAddress(ShippingAddressParam shippingAddressParam);

    /**
     * 新增地址
     * @param shippingAddressParam
     */
    void createAddress(ShippingAddressParam shippingAddressParam);
}
