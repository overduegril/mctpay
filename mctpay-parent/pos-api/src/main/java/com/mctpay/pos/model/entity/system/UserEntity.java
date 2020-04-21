package com.mctpay.pos.model.entity.system;

import com.mctpay.common.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author dongwei_guo
 * @date 2020-02-23 18:23:01
 */
@Data
@ApiModel(value = "钱包用户")
public class UserEntity extends BaseEntity implements Serializable, UserDetails {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "")
    private String id;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String username;
    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickname;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;
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

    /**
     * 角色
     */
    @ApiModelProperty(value = "角色")
    private List<RoleEntity> roles;

    /**
     * 该记录状态
     */
    @ApiModelProperty(value = "记录状态", hidden = true)
    private Integer status;

    /**
     * @Description 设置权限
     * @Date 10:34 2020/2/26
     **/
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>(roles.size());
        for (RoleEntity role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * @Description 账号是否被冻结
     * @Date 16:15 2020/2/26
     **/
    @Override
    public boolean isAccountNonLocked() {
        if (status < 0) {
            return false;
        } else
            return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * @Description 账号是否可用
     * @Date 16:15 2020/2/26
     **/
    @Override
    public boolean isEnabled() {
        if (status != 0) {
            return true;
        } else
            return false;
    }
}
