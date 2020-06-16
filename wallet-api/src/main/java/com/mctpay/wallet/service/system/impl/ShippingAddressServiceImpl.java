package com.mctpay.wallet.service.system.impl;

import com.mctpay.wallet.mapper.system.ShippingAddressMapper;
import com.mctpay.wallet.model.dto.system.ShippingAddressDTO;
import com.mctpay.wallet.model.entity.system.ShippingAddressEntity;
import com.mctpay.wallet.model.param.ShippingAddressParam;
import com.mctpay.wallet.service.system.ShippingAddressService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: guodongwei
 * @Description: 收货地址业务
 * @Date: 2020/6/12 17:29
 */
@Service
public class ShippingAddressServiceImpl implements ShippingAddressService {

    @Autowired
    private ShippingAddressMapper shippingAddressMapper;

    @Override
    public Integer countAddress(ShippingAddressParam shippingAddressParam) {
        Integer rows = shippingAddressMapper.count(shippingAddressParam);
        return rows;
    }

    @Override
    public void updateAddress(ShippingAddressParam shippingAddressParam) {
        shippingAddressMapper.update(shippingAddressParam);
    }

    @Override
    public void deleteAddress(Long id) {
        shippingAddressMapper.delete(id);
    }

    @Override
    public void deleteBatchAddress(List<Long> ids) {
        shippingAddressMapper.deleteBatch(ids);
    }

    @Override
    public List<ShippingAddressDTO> listAddress(ShippingAddressParam shippingAddressParam) {
        shippingAddressParam.setStatus(1);
        List<ShippingAddressEntity> shippingAddresses = shippingAddressMapper.list(shippingAddressParam);
        List<ShippingAddressDTO> shippingAddressDTOs = new ArrayList<>();
        for (ShippingAddressEntity shippingAddress : shippingAddresses) {
            ShippingAddressDTO shippingAddressDTO = new ShippingAddressDTO();
            BeanUtils.copyProperties(shippingAddress, shippingAddressDTO);
            shippingAddressDTOs.add(shippingAddressDTO);
        }
        return shippingAddressDTOs;
    }

    @Override
    public void createAddress(ShippingAddressParam shippingAddressParam) {
        shippingAddressMapper.insert(shippingAddressParam);
    }
}
