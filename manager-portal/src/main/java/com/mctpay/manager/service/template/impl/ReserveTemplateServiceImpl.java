package com.mctpay.manager.service.template.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.mctpay.common.base.model.ResponseData;
import com.mctpay.manager.mapper.template.ReserveTemplateMapper;
import com.mctpay.manager.model.dto.template.ReserveTemplateDTO;
import com.mctpay.manager.model.entity.template.ReserveTemplateEntity;
import com.mctpay.manager.model.param.ReserveTemplateParam;
import com.mctpay.manager.service.template.ReserveTemplateService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    /**
     * @Description
     * @Date 10:25 2020/04/28
     **/
    @Override
    public ResponseData deleteReserveTemplate(Long reserveTemplateId) {
        Date updateTime = new Date();
        reserveTemplateMapper.deleteReserveTemplate(reserveTemplateId, 0, updateTime);
        return new ResponseData().success(null);
    }
    /**
     * @Description
     * @Date 11:06 2020/04/28
     **/
    @Override
    public List<ReserveTemplateDTO> listReserveTemplate() {
        List<ReserveTemplateEntity> reserveTemplateEntityList = reserveTemplateMapper.listReserveTemplate();
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
