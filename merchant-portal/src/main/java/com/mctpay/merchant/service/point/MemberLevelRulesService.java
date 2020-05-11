package com.mctpay.merchant.service.point;

import com.mctpay.common.base.model.ResponseData;
import com.mctpay.merchant.model.dto.point.MemberLevelRulesDTO;
import com.mctpay.merchant.model.param.MemberLevelRulesParam;
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
     * @Date 15:49 2020/2/24
     **/
    List<MemberLevelRulesDTO> listMemberLevelRules(String merchantId);

    /**
     * @Description 插入会员等级积分设置
     * @Date 10:36 2020/3/2
     **/
    ResponseData<Object> insertMemberLevelRules(MemberLevelRulesParam memberLevelRulesParam);

    /**
     * @Description 冻结，激活会员积分等级设置
     * @Date 11:12 2020/3/2
     **/
    void switchMemberLevelRules(Long id, Integer state);

    /**
     * @Description 更新积分等级规则
     * @Date 19:37 2020/3/23
     **/
    ResponseData updateMemberLevelRules(MemberLevelRulesParam memberLevelRulesParam);
}
