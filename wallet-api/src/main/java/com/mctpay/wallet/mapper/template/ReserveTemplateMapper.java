package com.mctpay.wallet.mapper.template;

import com.mctpay.common.base.mapper.BaseMapper;
import com.mctpay.wallet.model.entity.template.ReserveTemplateEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dongwei_guo
 * @date 2020-02-29 14:00:37
 */
@Repository
public interface ReserveTemplateMapper extends BaseMapper<ReserveTemplateEntity> {

    /**
     * @Description 商户预定模板列表
     * @Date 11:12 2020/04/28
     **/
    List<ReserveTemplateEntity> listMerchantReserveTemplate(String merchantId);

}
