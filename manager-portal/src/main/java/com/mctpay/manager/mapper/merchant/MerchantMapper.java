package com.mctpay.manager.mapper.merchant;

import com.mctpay.common.base.mapper.BaseMapper;
import com.mctpay.manager.model.entity.merchant.MerchantEntity;
import com.mctpay.manager.model.param.MerchantParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dongwei_guo
 * @date 2020-02-23 18:23:02
 */
@Repository
public interface MerchantMapper extends BaseMapper<MerchantEntity> {

    /**
     * @Description 插入商户
     * @Date 22:31  2020/2/28
     **/
    void insertMerchant(MerchantParam merchantParam);
    /**
     * @Description 获取商户集合
     * @Date 22:31  2020/2/28
     **/
    List<MerchantEntity> listMerchantByInput(@Param("inputContent") String inputContent);
    /**
     * @Description 冻结/取消冻结用户
     * @Date 10:47  2020/2/29
     **/
    void updateSwitchMerchant(@Param("merchantId") String merchantId , @Param("state") Integer  state);
    /**
     * @Description 修改商户
     * @Date 10:47  2020/2/29
     **/
    void updateMerchant(MerchantParam updateMerchantParam);

    /**
     * @Description 保存营业执照
     * @Date 14:16 2020/3/4
     **/
    void insertBusinessLicense(@Param("businessLicenseUrl") String businessLicenseUrl,@Param("merchantId") String merchantId);

}
