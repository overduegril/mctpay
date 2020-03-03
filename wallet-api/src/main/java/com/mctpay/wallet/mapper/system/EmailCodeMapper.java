package com.mctpay.wallet.mapper.system;

import com.mctpay.common.base.mapper.BaseMapper;
import com.mctpay.wallet.model.entity.system.EmailCodeEntity;
import com.mctpay.wallet.model.param.EmailCodeParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author dongwei_guo
 * @date 2020-03-02 16:26:16
 */
@Repository
public interface EmailCodeMapper extends BaseMapper<EmailCodeEntity> {

    /**
     * @Description 插入邮箱验证码
     * @Date 16:42 2020/3/2
     **/
    void insertEmailCode(EmailCodeParam emailCodeParam);

    /**
     * @Description 获取验证码
     * @Date 19:57 2020/3/2
     **/
    EmailCodeEntity getEmailCodeByEmail(@Param("email") String email, @Param("businessType") String businessType);
}
