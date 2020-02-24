package com.mctpay.manager.service.point;

import com.mctpay.manager.mapper.point.ManagerGiftMapper;
import com.mctpay.manager.model.dto.point.ManagerGiftDTO;
import com.mctpay.manager.model.entity.point.ManagerGiftEntity;
import com.mctpay.manager.model.entity.point.ManagerMemberLevelRulesEntity;
import com.mctpay.manager.service.point.impl.ManagerGiftService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: guodongwei
 * @Description: 积分
 * @Date: 2020/2/24 10:27
 */
@Service
public class ManagerGiftServiceImpl implements ManagerGiftService{

    @Autowired
    private ManagerGiftMapper managerGiftMapper;

}
