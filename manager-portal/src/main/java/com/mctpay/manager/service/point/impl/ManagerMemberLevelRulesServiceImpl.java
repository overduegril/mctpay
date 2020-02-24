package com.mctpay.manager.service.point.impl;

import com.mctpay.manager.mapper.point.ManagerMemberLevelRulesMapper;
import com.mctpay.manager.model.dto.point.ManagerMemberLevelRulesDTO;
import com.mctpay.manager.model.entity.point.ManagerMemberLevelRulesEntity;
import com.mctpay.manager.service.point.ManagerMemberLevelRulesService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: guodongwei
 * @Description: 积分等级设置业务
 * @Date: 2020/2/24 15:55
 */
@Service
public class ManagerMemberLevelRulesServiceImpl implements ManagerMemberLevelRulesService {

    @Autowired
    private ManagerMemberLevelRulesMapper managerMemberLevelRulesMapper;

    /**
     * @Description 积分等级设置
     * @Date 15:49 2020/2/24
     **/
    @Override
    public ManagerMemberLevelRulesDTO listMemberLevelRules() {
        ManagerMemberLevelRulesEntity managerMemberLevelRulesEntity = managerMemberLevelRulesMapper.listMemberLevelRules();
        ManagerMemberLevelRulesDTO managerMemberLevelRulesDTO = new ManagerMemberLevelRulesDTO();
        if (managerMemberLevelRulesEntity != null) {
            BeanUtils.copyProperties(managerMemberLevelRulesEntity, managerMemberLevelRulesDTO);
        }
        return managerMemberLevelRulesDTO;
    }
}
