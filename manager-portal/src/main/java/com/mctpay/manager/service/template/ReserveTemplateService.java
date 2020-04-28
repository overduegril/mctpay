package com.mctpay.manager.service.template;

import com.mctpay.common.base.model.ResponseData;
import com.mctpay.manager.model.dto.template.ReserveTemplateDTO;
import com.mctpay.manager.model.param.ReserveTemplateParam;

import java.util.List;

/**
 * @Author: guodongwei
 * @Description: 预定模板
 * @Date: 2020/3/21 15:23
 */
public interface ReserveTemplateService {

    /**
     * @Description
     * @Date 15:39 2020/3/21
     **/
    void insertReserveTemplate(ReserveTemplateParam reserveTemplateParam);
    /**
     * @Description
     * @Date 15:39 2020/04/26
     **/
    ResponseData updateReserveTemplate(ReserveTemplateParam reserveTemplateParam);
    /**
     * @Description
     * @Date 10:24 2020/04/28
     **/
    ResponseData deleteReserveTemplate(Long reserveTemplateId);
    /**
     * @Description
     * @Date 10:56 2020/04/28
     **/
    List<ReserveTemplateDTO> listReserveTemplate();
}
