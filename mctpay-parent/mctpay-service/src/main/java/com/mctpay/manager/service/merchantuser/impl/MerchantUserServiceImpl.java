package com.mctpay.manager.service.merchantuser.impl;

import com.mctpay.common.config.GlobalConstant;
import com.mctpay.common.config.MyBCryptPasswordEncoder;
import com.mctpay.common.exception.BusinessException;
import com.mctpay.common.uitl.SecureUtils;
import com.mctpay.common.uitl.UIdUtils;
import com.mctpay.manager.convert.merchantuser.MerchantUserConvert;
import com.mctpay.manager.keyvalue.MerchantUserTypeEnum;
import com.mctpay.manager.mapper.merchant.MerchantMapper;
import com.mctpay.manager.mapper.merchantuser.MerchantUserMapper;
import com.mctpay.manager.model.dto.merchantuser.EditReqDtO;
import com.mctpay.manager.model.dto.merchantuser.FindByEmailDtO;
import com.mctpay.manager.model.dto.merchantuser.FindByLoginNameResDtO;
import com.mctpay.manager.model.dto.merchantuser.ListMerchantUserByInputReqDtO;
import com.mctpay.manager.model.entity.merchant.MerchantEntity;
import com.mctpay.manager.model.entity.merchantuser.MerchantUserEntity;
import com.mctpay.manager.model.vo.merchantuser.ListMerchantUserByInputResVo;
import com.mctpay.manager.service.merchantuser.MerchantUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.regex.Pattern;

import static com.mctpay.common.constants.ErrorCode.*;

/**
 * @Author: guodongwei
 * @Description: 用户相关
 * @Date: 2020/2/24 20:18
 */
@Service
public class MerchantUserServiceImpl implements MerchantUserService, UserDetailsService {

    @Autowired
    private MerchantUserMapper merchantUserMapper;
    @Autowired
    MerchantMapper merchantMapper;
    @Autowired
    MerchantUserConvert merchantUserConvert;
    @Autowired
    @Qualifier("myBCryptPasswordEncoder")
    private MyBCryptPasswordEncoder myBCryptPasswordEncoder;



    /**
     * 分页查询指定商户管理员信息
     *
     * @param listMerchantUserByInputReqDtO
     * @return
     */
    @Override
    public List<ListMerchantUserByInputResVo> listMerchantUserByInput(ListMerchantUserByInputReqDtO listMerchantUserByInputReqDtO) {
        List<MerchantUserEntity> list = merchantUserMapper.findPageByMerchantId(listMerchantUserByInputReqDtO);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return merchantUserConvert.merchantUserEntityToListMerchantUserByInputResDtO(list);
    }

    /**
     * 新增管理员
     *
     * @param editReqDtO
     * @return
     */
    @Override
    public boolean insert(EditReqDtO editReqDtO) throws BusinessException {
        MerchantUserEntity merchantUserEntity = merchantUserConvert.editReqDtOToPo(editReqDtO);
        merchantUserEntity.setCreateTime(new java.util.Date());
        merchantUserEntity.setId(UIdUtils.getUid().toString());
        //默认激活状态 并设置默认你密码
        merchantUserEntity.setStatus(2);
        merchantUserEntity.setPassword(GlobalConstant.defalutMd5Password);
        try {
            merchantUserMapper.insert(merchantUserEntity);
        }catch (DuplicateKeyException  e){
            String message=e.getMessage();
            if(message.indexOf("for key 'merchant_id_login_name'")>=0){
               throw new BusinessException(500,"登录名或者邮箱重复");
            }else if(message.indexOf("for key 'merchant_id_email'")>=0){
                throw new BusinessException(500,"邮箱重复");
            }else if(message.indexOf("for key 'merchant_id_phone_number'")>=0){
                throw new BusinessException(500,"手机号重复");
            }
            throw  e;
        }
        return true;
    }


    /**
     * 修改管理员
     *
     * @param editReqDtO
     * @return
     */
    @Override
    public boolean update(EditReqDtO editReqDtO) throws BusinessException {
        MerchantUserEntity merchantUserEntity = merchantUserMapper.selectByPrimaryKey(editReqDtO.getId());
        if (merchantUserEntity == null || merchantUserEntity.getId() == null) {
            throw new BusinessException(500, "数据不存在");
        }
        merchantUserConvert.updateMerchantUserEntityFromEditReqDtO(editReqDtO, merchantUserEntity);
        merchantUserEntity.setUpdateTime(new java.util.Date());
        return merchantUserMapper.updateByPrimaryKey(merchantUserEntity) > 0;
    }

    /**
     * 修改指定用户密码
     *
     * @param id
     * @param newPassword
     * @return
     */
    @Override
    public boolean updatePassword(String id, String newPassword) throws BusinessException {
        validatePassword(newPassword);
        MerchantUserEntity merchantUserEntity = merchantUserMapper.selectByPrimaryKey(id);
        if(merchantUserEntity==null){
            throw new BusinessException(500, "用户不存在");
        }
        merchantUserEntity.setPassword(SecureUtils.MD5Encrypt(newPassword));
        merchantUserEntity.setUpdateTime(new java.util.Date());
        int updateCount = merchantUserMapper.updateByPrimaryKeySelective(merchantUserEntity);
        return updateCount > 0;
    }

    /**
     * 验证密码有效性
     *
     * @param password
     * @throws BusinessException
     */
    public void validatePassword(String password) throws BusinessException {
        if (StringUtils.isEmpty(password)) {
            throw new BusinessException(PASSWORD_IS_NOT_NULL.getCode(), PASSWORD_IS_NOT_NULL.getMessage());
        }
        if (password.length() < 6) {
            throw new BusinessException(3015, "密码长度小于6位");
        }
        if (password.length() > 30) {
            throw new BusinessException(3015, "密码长度大于30");
        }
        String pattern = "([A-Z]|[a-z]|[0-9])+";
        boolean isMatch = Pattern.matches(pattern, password);
        if (!isMatch) {
            throw new BusinessException(3015, "密码不能包含特殊字符,只能是A-Z,a-z0-9");
        }
    }

    /**
     * 根据email获取用户信息
     *
     * @param email
     * @return
     */
    @Override
    public FindByEmailDtO findByEmail(String email) {
        Example example = new Example(MerchantUserEntity.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("email", StringUtils.isEmpty(email) ? " " : email);
        MerchantUserEntity merchantEntity = merchantUserMapper.selectOneByExample(example);
        if (merchantEntity == null) {
            return null;
        }
        return merchantUserConvert.entityToFindByEmailDtO(merchantEntity);
    }

    /**
     * 根据用户名获取用户信息
     *
     * @param loginName
     * @return
     */
    @Override
    public FindByLoginNameResDtO findByLoginName(String loginName) {
        Example example = new Example(MerchantUserEntity.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("loginName", StringUtils.isEmpty(loginName) ? " " : loginName);
        MerchantUserEntity merchantEntity = merchantUserMapper.selectOneByExample(example);
        if (merchantEntity == null) {
            return null;
        }
        return merchantUserConvert.entityToFindByLoginNameResDtO(merchantEntity);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        FindByLoginNameResDtO findByLoginNameResDtO = this.findByLoginName(s);
        // 验证账户为username的用户是否存在
        if (null == findByLoginNameResDtO) {
            throw new UsernameNotFoundException("username:  " + s + "is not exist!");
        }
        if (findByLoginNameResDtO.getMerchantUserType() == null) {
            throw new UsernameNotFoundException(USER_TYPE_IS_NOT_NULL.getMessage());
        }
        if (!findByLoginNameResDtO.getMerchantUserType().equals(MerchantUserTypeEnum.system)) {
            throw new UsernameNotFoundException(ONLY_SUPPER_ADMIN.getMessage());
        }
        //根据商户端状态为准
        MerchantEntity merchantEntity= merchantMapper.selectByPrimaryKey(findByLoginNameResDtO.getMerchantId());
        findByLoginNameResDtO.setStatus(merchantEntity.getStatus());
        findByLoginNameResDtO.setDefaultPassword(findByLoginNameResDtO.getPassword().equals(GlobalConstant.defalutMd5Password));
        findByLoginNameResDtO.setPassword(myBCryptPasswordEncoder.encode(findByLoginNameResDtO.getPassword()));
        // 返回认证用户
        return findByLoginNameResDtO;
    }

    /**
     * @Description 激活冻结用户
     * @Date 21:31 2020/2/25
     **/
    @Override
    public boolean switchUser(Long userId, Integer state) throws BusinessException {
        MerchantUserEntity merchantUserEntity = merchantUserMapper.selectByPrimaryKey(userId);
        if(merchantUserEntity==null){
            throw new BusinessException(500, "用户不存在");
        }
        merchantUserEntity.setStatus(state);
        merchantUserEntity.setUpdateTime(new java.util.Date());
        return merchantUserMapper.updateByPrimaryKey(merchantUserEntity)>0;
    }

}
