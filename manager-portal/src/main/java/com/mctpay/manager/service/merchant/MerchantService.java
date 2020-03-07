package com.mctpay.manager.service.merchant;

import com.mctpay.common.base.model.ResponseData;
import com.mctpay.manager.model.dto.merchant.MerchantDtO;
import com.mctpay.manager.model.param.MerchantParam;

import java.util.List;

/**
 * @Author: guodongwei
 * @Description: 商户service
 * @Date: 2020/2/24 10:28
 */
public interface MerchantService {

    /**
     * @Description 插入积分商品
     * @Date 22:27  2020/2/28
     **/
    ResponseData insertMerchant(MerchantParam merchantParam);
    /**
     * @Description 商户集合
     * @Date 23:39 2020/2/28
     **/
    List<MerchantDtO> listMerchantByInput(String inputContent);
    /**
     * @Description 冻结/解冻商户
     * @Date 10:36 2020/2/29
     **/
    ResponseData switchMerchant(String merchantId, Integer state);
    /**
     * @Description 修改商户
     * @Date 10:36 2020/2/29
     **/
    ResponseData updateMerchant(MerchantParam merchantParam);

    /**
     * @Description 重置密码
     * @Date 23:23 2020/3/3
     **/
    void resetPassword(String merchantId);

    /**
     * @Description 保存营业执照
     * @Date 14:16 2020/3/4
     **/
    void insertBusinessLicense(String businessLicenseUrl,String merchantId);
}
