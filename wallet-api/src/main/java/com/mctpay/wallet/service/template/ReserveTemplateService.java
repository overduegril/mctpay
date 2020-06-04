package com.mctpay.wallet.service.template;

import com.mctpay.wallet.model.dto.template.ReserveTemplateDTO;

import java.util.List;

/**
 * @Author: guodongwei
 * @Description: 预定模板
 * @Date: 2020/3/21 15:23
 */
public interface ReserveTemplateService {


    /**
     * @Description 获取商户用有分页模板
     * @Date 19:49 2020/5/11
     **/
    List<ReserveTemplateDTO> listMerchantReserveTemplate(String merchantId);

}
