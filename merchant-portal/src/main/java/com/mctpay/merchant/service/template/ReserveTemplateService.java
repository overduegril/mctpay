package com.mctpay.merchant.service.template;

import com.mctpay.common.base.model.ResponseData;
import com.mctpay.merchant.model.dto.template.ReserveTemplateDTO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Author: guodongwei
 * @Description: 预定模板
 * @Date: 2020/3/21 15:23
 */
public interface ReserveTemplateService {

    /**
     * @Description 获取预约模板
     * @Date 10:56 2020/04/28
     **/
    List<ReserveTemplateDTO> listReserveTemplate();

    /**
     * @Description 获取商户用有分页模板
     * @Date 19:49 2020/5/11
     **/
    List<ReserveTemplateDTO> listMerchantReserveTemplate();

    /**
     * 更新商户模板
     * @param templates
     * @return
     */
    void updateMerchantReserveTemplate(@RequestBody List<Long> templates);
}
