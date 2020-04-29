package com.mctpay.manager.service.merchant.impl;

import com.mctpay.manager.mapper.merchant.IndustryDictionaryMapper;
import com.mctpay.manager.model.dto.merchant.IndustryDictionaryDTO;
import com.mctpay.manager.model.entity.merchant.IndustryDictionaryEntity;
import com.mctpay.manager.service.merchant.IndustryDictionaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 陈树彪
 * @Description: 商业serviceImpl
 * @Date: 2020/04/29 10:17
 */
@Service
@Slf4j
public class IndustryDictionaryServiceImpl implements IndustryDictionaryService {

    @Autowired
    private IndustryDictionaryMapper industryDictionaryMapper;

    /**
     * @Description 行业列表
     * @Date 10:24 2019/04/29
     **/

    @Override
    public List<IndustryDictionaryDTO> listIndustryDictionary() {

        List<IndustryDictionaryEntity> industryDictionaryEntities =  industryDictionaryMapper.listIndustryDictionary();
        List<IndustryDictionaryDTO> industryDictionaryDTOs = new ArrayList<>();
        for(IndustryDictionaryEntity industryDictionaryEntitie : industryDictionaryEntities){
            IndustryDictionaryDTO industryDictionaryDTO = new IndustryDictionaryDTO();
            BeanUtils.copyProperties(industryDictionaryEntitie,industryDictionaryDTO);
            industryDictionaryDTOs.add(industryDictionaryDTO);
        }
        return industryDictionaryDTOs;
    }
}
