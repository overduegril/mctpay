package com.mctpay.manager.service.template.impl;

import com.mctpay.common.base.model.ResponseData;
import com.mctpay.manager.mapper.template.ShowPictureMapper;
import com.mctpay.manager.model.dto.template.ShowPicturerDTO;
import com.mctpay.manager.model.entity.template.ShowPictureEntity;
import com.mctpay.manager.model.param.ShowPicturerParam;
import com.mctpay.manager.service.template.ShowPictureService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: chenshubiao
 * @Description:  轮播图
 * @Date: 2020/2/29 23:29
 */
@Service
public class ShowPictureServiceImpl implements ShowPictureService {
    @Autowired
    private ShowPictureMapper showPictureMapper;
    @Override
    public ResponseData insertShowPicturer(ShowPicturerParam showPicturerParam) {
        showPictureMapper.insertShowPicturer(showPicturerParam);
        return new ResponseData().success(null);
    }

    @Override
    public List<ShowPicturerDTO> listShowPicturer() {
        List<ShowPictureEntity> showPictureEntities = showPictureMapper.listShowPicturer();
        List<ShowPicturerDTO> showPicturerDTOS = new ArrayList<>();
        for(ShowPictureEntity showPictureEntitie : showPictureEntities){
            ShowPicturerDTO showPicturerDTO = new ShowPicturerDTO();
            BeanUtils.copyProperties(showPictureEntitie,showPicturerDTO);
            showPicturerDTOS.add(showPicturerDTO);
        }
        return showPicturerDTOS;
    }

    @Override
    public ResponseData updateSwitchShowPicturer(long showPicturerId, Integer status) {
        showPictureMapper.updateSwitchShowPicturer(showPicturerId,status);
        return new ResponseData().success(null);
    }
}
