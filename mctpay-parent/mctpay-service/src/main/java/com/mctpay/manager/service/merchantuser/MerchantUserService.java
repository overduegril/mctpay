package com.mctpay.manager.service.merchantuser;

import com.mctpay.common.exception.BusinessException;
import com.mctpay.manager.model.dto.merchantuser.EditReqDtO;
import com.mctpay.manager.model.dto.merchantuser.FindByEmailDtO;
import com.mctpay.manager.model.dto.merchantuser.FindByLoginNameResDtO;
import com.mctpay.manager.model.dto.merchantuser.ListMerchantUserByInputReqDtO;
import com.mctpay.manager.model.vo.merchantuser.ListMerchantUserByInputResVo;

import java.util.List;

/**
 * @Author: guodongwei
 * @Description: 用户相关接口
 * @Date: 2020/2/24 20:42
 */
public interface MerchantUserService {

    /**
     * 根据用户名获取用户信息
     *
     * @param loginName
     * @return
     */
    public FindByLoginNameResDtO findByLoginName(String loginName);
    /**
     * @Description 激活冻结用户
     * @Date 21:30 2020/2/25
     **/
    boolean switchUser(Long userId, Integer state) throws BusinessException;
//
//    /**
//     * @Description 分页查询会员
//     * @Date 19:45 2020/2/26
//     **/
//    List<UserDTO> listUser();
//
//    /**
//     * @Description 根据输入内容查询会员
//     * @Date 10:29 2020/2/27
//     **/
//    List<UserDTO> listUserByInput(String inputContent);

    /**
     * 修改指定用户密码
     * @param newPassword
     * @return
     */
    public boolean updatePassword(String id,String newPassword) throws BusinessException;
    /**
     * 根据email获取商户用户信息
     * @param email
     * @return
     */
    public FindByEmailDtO findByEmail(String email);
    /**
     * 分页查询商户管理员
     * @param listMerchantUserByInputReqDtO
     * @return
     */
    public List<ListMerchantUserByInputResVo> listMerchantUserByInput(ListMerchantUserByInputReqDtO listMerchantUserByInputReqDtO);

    /**
     *新增管理员
     * @param editReqDtO
     * @return
     */
    public boolean insert(EditReqDtO editReqDtO) throws BusinessException;

    /**
     * 修改管理员
     * @param editReqDtO
     * @return
     */
    public boolean update(EditReqDtO editReqDtO) throws BusinessException;
}
