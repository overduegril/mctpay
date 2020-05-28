package com.mctpay.wallet.service.point;

import com.mctpay.common.base.model.ResponseData;
import com.mctpay.wallet.model.dto.point.MemberLevelRulesDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: guodongwei
 * @Description: 积分等级设置
 * @Date: 2020/2/24 15:54
 */
@Service
public interface MemberLevelRulesService {

    /**
     * @Description 积分等级设置
     * @Date 22:56 2020/05/28
     **/
    List<MemberLevelRulesDTO> listMemberLevelRules();

}
