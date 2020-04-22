package com.mctpay.manager.model.dto.merchantuser;

import com.mctpay.manager.keyvalue.MerchantUserTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author liqiang
 * @date 2020/4/21 19:44
 * @Description: (what)
 * (why)
 * (how)
 */
@Data
public class FindByEmailDtO  implements Serializable, UserDetails {

    @Id
    private String id;
    @ApiModelProperty(value = "商户密码")
    private String password;
    @ApiModelProperty(value = "是否默认密码登录")
    private Boolean defaultPassword;

    @ApiModelProperty(value = "登录名")
    private String loginName;
    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String name;
    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickname;
    /**
     * 性别
     */
    @ApiModelProperty(value = "性别")
    private Integer gender;
    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String phoneNumber;
    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "折扣比例")
    private Double minDiscountRate;
    @ApiModelProperty(value = "所属商户")
    private String merchantId;
    @ApiModelProperty(value = "状态")
    private Integer status;
    /**
     * 账号类型
     */
    private MerchantUserTypeEnum merchantUserType;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        if (getStatus() < 0) {
            return false;
        } else
            return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        if (status != 0) {
            return true;
        } else
            return false;
    }


}
