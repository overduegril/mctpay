package com.mctpay.wallet.service.template.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.mctpay.wallet.mapper.template.ReserveTemplateMapper;
import com.mctpay.wallet.model.dto.template.ReserveTemplateDTO;
import com.mctpay.wallet.model.entity.template.ReserveTemplateEntity;
import com.mctpay.wallet.service.template.ReserveTemplateService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: guodongwei
 * @Description: 预定模板业务
 * @Date: 2020/3/21 15:23
 */
@Service
public class ReserveTemplateServiceImpl implements ReserveTemplateService {

    @Autowired
    private ReserveTemplateMapper reserveTemplateMapper;

    @Override
    public List<ReserveTemplateDTO> listMerchantReserveTemplate(String merchantId) {
        List<ReserveTemplateEntity> reserveTemplateEntityList = reserveTemplateMapper.listMerchantReserveTemplate(merchantId);
        List<ReserveTemplateDTO> reserveTemplateDTOs = new ArrayList<>();
        for(ReserveTemplateEntity reserveTemplateEntity : reserveTemplateEntityList){
            ReserveTemplateDTO reserveTemplateDTO = new ReserveTemplateDTO();
            BeanUtils.copyProperties(reserveTemplateEntity,reserveTemplateDTO);
            JSONObject dynamicField = JSONUtil.parseObj(reserveTemplateEntity.getDynamicField());
            reserveTemplateDTO.setDynamicField(dynamicField);
            reserveTemplateDTOs.add(reserveTemplateDTO);
        }
        return reserveTemplateDTOs;
    }
}
