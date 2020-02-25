package com.mctpay.manager.service.point;

import com.mctpay.manager.model.dto.point.MemberLevelRulesDTO;
import org.springframework.stereotype.Service;

/**
 * @Author: guodongwei
 * @Description: 积分等级设置
 * @Date: 2020/2/24 15:54
 */
@Service
public interface MemberLevelRulesService {

    /**
     * @Description 积分等级设置
     * @Date 15:49 2020/2/24
     **/
    MemberLevelRulesDTO listMemberLevelRules();

}
