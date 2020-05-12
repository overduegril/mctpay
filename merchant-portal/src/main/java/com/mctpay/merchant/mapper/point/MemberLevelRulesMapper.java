package com.mctpay.merchant.mapper.point;

import com.mctpay.common.base.mapper.BaseMapper;
import com.mctpay.merchant.model.entity.point.MemberLevelRulesEntity;
import com.mctpay.merchant.model.param.MemberLevelRulesParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dongwei_guo
 * @date 2020-02-23 18:23:01
 */
@Repository
public interface MemberLevelRulesMapper extends BaseMapper<MemberLevelRulesEntity> {

    /**
     * @Description 查看积分等级设置
     * @Date 15:49 2020/2/24
     **/
    List<MemberLevelRulesEntity> listMemberLevelRules(String merchantId);

    /**
     * @Description 插入会员等级积分设置
     * @Date 10:38 2020/3/2
     **/
    void insertMemberLevelRules(MemberLevelRulesParam memberLevelRulesParam);

    /**
     * @Description 冻结，激活会员积分等级设置
     * @Date 11:12 2020/3/2
     **/
    void switchMemberLevelRules(@Param("id") Long id, @Param("state") Integer state, @Param("merchantId") String merchantId);

    /**
     * @Description 判断会员等级名称是否已经存在
     * @Date 11:45 2020/3/2
     **/
    Integer countMemberLevelName(@Param("memberLevelName") String memberLevelName, @Param("merchantId") String merchantId);

    /**
     * @Description 判断会员等级名称是否已经存在
     * @Date 11:45 2020/3/2
     **/
    Integer countPoint(@Param("point") Integer point, @Param("merchantId") String merchantId);

    /**
     * @Description 根据积分判断会员等级
     * @Date 13:52 2020/3/3
     **/
    String getLevelByPoint(@Param("point") Integer point, @Param("merchantId") String merchantId );

    /**
     * @Description 更新积分等级规则
     * @Date 19:37 2020/3/23
     **/
    void updateMemberLevelRules(MemberLevelRulesParam memberLevelRulesParam);
}
