package com.mctpay.pos.mapper.system;

import com.mctpay.common.base.mapper.BaseMapper;
import com.mctpay.pos.model.entity.system.SmsCodeEntity;
import com.mctpay.pos.model.param.SmsCodeParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author dongwei_guo
 * @date 2020-06-16 21:58:28
 */
@Repository
public interface SmsCodeMapper extends BaseMapper<SmsCodeEntity> {

    /**
     * 插入短信验证码
     * @param smsCodeParam
     */
    void insert(SmsCodeParam smsCodeParam);

    /**
     * 根据业务类型和手机号获取已发送短信验证码信息
     *
     * @return
     */
    SmsCodeEntity getByPhoneAndBussinessType(@Param("phoneNumber") String phoneNumber, @Param("businessType") String businessType);
}
