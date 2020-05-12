package com.mctpay.merchant.mapper.template;

import com.mctpay.common.base.mapper.BaseMapper;
import com.mctpay.merchant.model.entity.template.ReserveTemplateEntity;
import com.mctpay.merchant.model.param.MerchantTemplateParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dongwei_guo
 * @date 2020-02-29 14:00:37
 */
@Repository
public interface ReserveTemplateMapper extends BaseMapper<ReserveTemplateEntity> {

    /**
     * @Description 预定模板列表
     * @Date 11:12 2020/04/28
     **/
    List<ReserveTemplateEntity> listReserveTemplate();


    /**
     * @Description 商户预定模板列表
     * @Date 11:12 2020/04/28
     **/
    List<ReserveTemplateEntity> listMerchantReserveTemplate(String merchantId);

    /**
     * @Description 更新商户模板
     * @Date 21:07 2020/5/11
     **/
    void insertMerchantReserveTemplate(@Param("templates") List<MerchantTemplateParam> templates);

   /**
    * @Description 删除短信模板
    * @Date 21:09 2020/5/11
    **/
    void deleteMerchantReserveTemplate(String merchantId);
}
