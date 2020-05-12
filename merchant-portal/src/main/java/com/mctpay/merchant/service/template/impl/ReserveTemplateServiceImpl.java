package com.mctpay.merchant.service.template.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.mctpay.common.base.model.ResponseData;
import com.mctpay.merchant.mapper.template.ReserveTemplateMapper;
import com.mctpay.merchant.model.dto.template.ReserveTemplateDTO;
import com.mctpay.merchant.model.entity.system.UserEntity;
import com.mctpay.merchant.model.entity.template.ReserveTemplateEntity;
import com.mctpay.merchant.model.param.MerchantTemplateParam;
import com.mctpay.merchant.service.template.ReserveTemplateService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
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
    public List<ReserveTemplateDTO> listReserveTemplate() {
        List<ReserveTemplateEntity> reserveTemplateEntityList = reserveTemplateMapper.listReserveTemplate();
        UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<ReserveTemplateEntity> merchantReserveTemplates = reserveTemplateMapper.listMerchantReserveTemplate(userEntity.getId());
        List<ReserveTemplateDTO> reserveTemplateDTOs = new ArrayList<>();
        Boolean flag = false;
        for(ReserveTemplateEntity reserveTemplateEntity : reserveTemplateEntityList){
            for (ReserveTemplateEntity merchantReserveTemplate : merchantReserveTemplates) {
                if (merchantReserveTemplate.getId().equals(reserveTemplateEntity.getId())) {
                    flag = true;
                    break;
                }
            }
            ReserveTemplateDTO reserveTemplateDTO = new ReserveTemplateDTO();
            reserveTemplateDTO.setSelected(flag);
            flag = false;
            reserveTemplateDTO.setId(reserveTemplateEntity.getId());
            reserveTemplateDTO.setTemplateName(reserveTemplateEntity.getTemplateName());
            reserveTemplateDTOs.add(reserveTemplateDTO);
        }
        return reserveTemplateDTOs;
    }

    @Override
    public List<ReserveTemplateDTO> listMerchantReserveTemplate() {
        UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<ReserveTemplateEntity> reserveTemplateEntityList = reserveTemplateMapper.listMerchantReserveTemplate(userEntity.getId());
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

    @Override
    @Transactional
    public void updateMerchantReserveTemplate(List<MerchantTemplateParam> templates) {
        UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        reserveTemplateMapper.deleteMerchantReserveTemplate(userEntity.getId());
        for (MerchantTemplateParam template : templates) {
            template.setMerchantId(Long.valueOf(userEntity.getId()));
            template.setStatus(1);
            template.setCreateTime(new Date());
            template.setUpdateTime(new Date());
        }
        reserveTemplateMapper.insertMerchantReserveTemplate(templates);
    }
}
