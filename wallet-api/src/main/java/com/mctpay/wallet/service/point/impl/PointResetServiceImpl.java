package com.mctpay.wallet.service.point.impl;

import com.mctpay.wallet.mapper.point.PointResetMapper;
import com.mctpay.wallet.model.dto.point.PointResetDTO;
import com.mctpay.wallet.model.entity.point.PointResetEntity;
import com.mctpay.wallet.model.entity.system.UserEntity;
import com.mctpay.wallet.model.param.PointResetParam;
import com.mctpay.wallet.service.point.PointResetService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: guodongwei
 * @Description: 积分重置业务
 * @Date: 2020/4/2 19:55
 */
@Service
public class PointResetServiceImpl implements PointResetService {

    @Autowired
    private PointResetMapper pointResetMapper;

    @Override
    public void insertPointReset(PointResetParam pointResetParam) {
        pointResetMapper.insert(pointResetParam);
    }

    @Override
    public List<PointResetDTO> listPointReset() {
        List<PointResetEntity> pointResetEntities = pointResetMapper.listPointReset();
        List<PointResetDTO> pointResetDTOs = new ArrayList<>();
        for (PointResetEntity pointResetEntity : pointResetEntities) {
            PointResetDTO pointResetDTO = new PointResetDTO();
            BeanUtils.copyProperties(pointResetEntity, pointResetDTO);
            pointResetDTOs.add(pointResetDTO);
        }
        return pointResetDTOs;
    }

    @Override
    public void switchPointReset(String pointResetId, Integer state) {
        UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        pointResetMapper.switchPointReset(userEntity.getUsername(), pointResetId, state, new Date());
    }
}
