package com.mctpay.wallet.controller.system;

import com.mctpay.common.base.model.ResponseData;
import com.mctpay.wallet.mapper.system.ShippingAddressMapper;
import com.mctpay.wallet.model.dto.system.ShippingAddressDTO;
import com.mctpay.wallet.model.entity.system.ShippingAddressEntity;
import com.mctpay.wallet.model.entity.system.UserEntity;
import com.mctpay.wallet.model.param.ShippingAddressParam;
import com.mctpay.wallet.service.system.ShippingAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: guodongwei
 * @Description: 收货地址控制层
 * @Date: 2020/6/12 17:29
 */
@Api(value = "收货地址", tags = "收货地址")
@RestController
@RequestMapping("shopping-address")
public class ShippingAddressController {

    @Autowired
    private ShippingAddressService shippingAddressService;


    @ApiOperation(value = "统计用户收货地址数量", notes = "统计用户收货地址数量", httpMethod = "POST")
    @PostMapping("/countAddress")
    public ResponseData<Integer> countAddress(@RequestBody ShippingAddressParam shippingAddressParam) {
        UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        shippingAddressParam.setUserId(userEntity.getId());
        Integer rows = shippingAddressService.countAddress(shippingAddressParam);
        return new ResponseData<Integer>().success(rows);
    }


    @ApiOperation(value = "修改收货地址", notes = "修改收货地址", httpMethod = "POST")
    @PostMapping("/updateAddress")
    public ResponseData updateAddress(@RequestBody ShippingAddressParam shippingAddressParam) {
        UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        shippingAddressParam.setUserId(userEntity.getId());
        shippingAddressParam.setUpdateTime(new Date());
        shippingAddressService.updateAddress(shippingAddressParam);
        return new ResponseData<>().success(null);
    }


    @ApiOperation(value = "删除收货地址", notes = "删除收货地址", httpMethod = "POST")
    @PostMapping("/deleteAddress")
    public ResponseData deleteAddress(Long id) {
        shippingAddressService.deleteAddress(id);
        return new ResponseData<>().success(null);
    }


    @ApiOperation(value = "批量删除收货地址", notes = "批量删除收货地址", httpMethod = "POST")
    @PostMapping("/deleteBatchAddress")
    public ResponseData deleteBatchAddress(@RequestParam List<Long> ids) {
        shippingAddressService.deleteBatchAddress(ids);
        return new ResponseData<>().success(null);
    }

    @ApiOperation(value = "查询收货地址", notes = "查询收货地址", httpMethod = "POST")
    @PostMapping("/listAddress")
    public ResponseData<List<ShippingAddressDTO>> listAddress(@RequestBody ShippingAddressParam shippingAddressParam) {
        UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        shippingAddressParam.setUserId(userEntity.getId());
        List<ShippingAddressDTO> shippingAddressDTOs = shippingAddressService.listAddress(shippingAddressParam);
        return new ResponseData<List<ShippingAddressDTO>>().success(shippingAddressDTOs);
    }


    @ApiOperation(value = "新建收货地址", notes = "新建收货地址", httpMethod = "POST")
    @PostMapping("/createAddress")
    public ResponseData createAddress(@RequestBody ShippingAddressParam shippingAddressParam) {
        UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        shippingAddressParam.setUserId(userEntity.getId());
        shippingAddressParam.setCreateTime(new Date());
        shippingAddressParam.setUpdateTime(new Date());
        shippingAddressParam.setStatus(1);
        shippingAddressService.createAddress(shippingAddressParam);
        return new ResponseData<>().success(null);
    }
}
