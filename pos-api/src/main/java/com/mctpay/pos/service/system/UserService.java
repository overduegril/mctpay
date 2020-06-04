package com.mctpay.pos.service.system;

import com.mctpay.common.base.model.ResponseData;
import com.mctpay.pos.model.dto.system.UserDTO;
import com.mctpay.pos.model.dto.system.AccessibleMerchantDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: guodongwei
 * @Description: 用户相关接口
 * @Date: 2020/2/24 20:42
 */
public interface UserService {

    /**
     * @Description 激活冻结用户
     * @Date 21:30 2020/2/25
     **/
    ResponseData switchUser(Long userId, Integer state);

    /**
     * @Description 分页查询会员
     * @Date 19:45 2020/2/26
     **/
    List<UserDTO> listUser();

    /**
     * @Description 根据输入内容查询会员
     * @Date 10:29 2020/2/27
     **/
    List<UserDTO> listUserByInput(String inputContent);

    /**
     * 获取可用的账号
     * @param account
     */
    List<AccessibleMerchantDTO> listAccounts(String account);

    /**
     * 选择登录账户
     * @param id
     * @param request
     */
    void selectAccount(String id, HttpServletRequest request);
}
