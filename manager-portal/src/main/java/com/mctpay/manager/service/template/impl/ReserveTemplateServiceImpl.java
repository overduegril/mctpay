package com.mctpay.manager.service.template.impl;

import com.mctpay.common.base.model.ResponseData;
import com.mctpay.manager.mapper.template.ReserveTemplateMapper;
import com.mctpay.manager.model.param.ReserveTemplateParam;
import com.mctpay.manager.service.template.ReserveTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: guodongwei
 * @Description: 预定模板业务
 * @Date: 2020/3/21 15:23
 */
@Service
public class ReserveTemplateServiceImpl implements ReserveTemplateService {

    @Autowired
    private ReserveTemplateMapper reserveTemplateMapper;

    /**
     * @Description
     * @Date 15:39 2020/3/21
     **/
    @Override
    public void insertReserveTemplate(ReserveTemplateParam reserveTemplateParam) {
        reserveTemplateMapper.insert(reserveTemplateParam);
    }
    /**
     * @Description
     * @Date 15:39 2020/04/26
     **/
    @Override
    public ResponseData updateReserveTemplate(ReserveTemplateParam reserveTemplateParam) {
        reserveTemplateMapper.updateReserveTemplate(reserveTemplateParam);
        return new ResponseData().success(null);
    }
}
