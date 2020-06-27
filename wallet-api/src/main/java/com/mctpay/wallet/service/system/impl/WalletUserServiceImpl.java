package com.mctpay.wallet.service.system.impl;

import cn.hutool.extra.qrcode.QrCodeUtil;
import com.mctpay.common.base.model.ResponseData;
import com.mctpay.common.config.MyBCryptPasswordEncoder;
import com.mctpay.common.exception.MCTException;
import com.mctpay.common.uitl.OSSUtils;
import com.mctpay.common.uitl.SecureUtils;
import com.mctpay.wallet.config.OSSProperties;
import com.mctpay.wallet.mapper.point.SummaryPointMapper;
import com.mctpay.wallet.mapper.point.UseabelPointMapper;
import com.mctpay.wallet.mapper.system.EmailCodeMapper;
import com.mctpay.wallet.mapper.system.SmsCodeMapper;
import com.mctpay.wallet.mapper.system.UserMapper;
import com.mctpay.wallet.model.dto.point.PointInfoDTO;
import com.mctpay.wallet.model.dto.system.UserDTO;
import com.mctpay.wallet.model.entity.point.SummaryPointEntity;
import com.mctpay.wallet.model.entity.system.EmailCodeEntity;
import com.mctpay.wallet.model.entity.system.SmsCodeEntity;
import com.mctpay.wallet.model.entity.system.UserEntity;
import com.mctpay.wallet.model.param.SummaryPointParam;
import com.mctpay.wallet.model.param.UseabelPointParam;
import com.mctpay.wallet.model.param.UserParam;
import com.mctpay.wallet.service.system.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.mctpay.common.constants.ErrorCode.*;
import static com.mctpay.wallet.model.enums.EmailCodeEnum.UPDATE_PASSWORD;
import static com.mctpay.wallet.model.enums.PhoneCodeEnum.USERS_BINDING;

/**
 * @Author: guodongwei
 * @Description: 用户相关
 * @Date: 2020/2/24 20:18
 */
@Service
public class WalletUserServiceImpl implements UserService {

    @Autowired
    @Qualifier("myBCryptPasswordEncoder")
    private MyBCryptPasswordEncoder myBCryptPasswordEncoder;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SummaryPointMapper summaryPointMapper;

    @Autowired
    private UseabelPointMapper useabelPointMapper;

    @Autowired
    private EmailCodeMapper emailCodeMapper;

    @Autowired
    private OSSProperties ossProperties;

    @Autowired
    private SmsCodeMapper smsCodeMapper;

    /**
     * @Description 注册管理员
     * @Date 20:45 2020/2/24
     **/
    @Override
    @Transactional
    public ResponseData insertUser(UserParam userParam) throws IOException {
        // 验证邮箱是否重复
        Integer emailCount = countEmail(userParam.getEmail());
        if (emailCount != 0) {
            return new ResponseData<>().fail(EMAIL_HAS_BEEN_USED.getCode(), EMAIL_HAS_BEEN_USED.getMessage());
        }
        // 添加用户二维码
        File tempFile = File.createTempFile(userParam.getId().toString(), ".jpg");
        QrCodeUtil.generate(userParam.getId().toString(), 300, 300, tempFile);
        InputStream inputStream = new FileInputStream(tempFile);
        String userQurcodeUrl = OSSUtils.uploadFileInputStream(ossProperties.getBucketName(), ossProperties.getQrcodePath() + tempFile.getName(), inputStream);
        userParam.setUserQurcodeUrl(userQurcodeUrl);
        userParam.setPassword(SecureUtils.MD5Encrypt(userParam.getPassword()));
        userMapper.insertUser(userParam);
        // 初始化积分设置
        UseabelPointParam useabelPointParam = new UseabelPointParam();
        useabelPointParam.setPoint(0);
        useabelPointParam.setUserId(userParam.getId());
        useabelPointParam.setStatus(1);
        useabelPointParam.setCreateTime(new Date());
        useabelPointParam.setUpdateTime(new Date());
        useabelPointMapper.initUseabelPoint(useabelPointParam);

        SummaryPointParam summaryPointParam = new SummaryPointParam();
        summaryPointParam.setPoint(0);
        summaryPointParam.setUserId(userParam.getId());
        summaryPointParam.setStatus(1);
        summaryPointParam.setCreateTime(new Date());
        summaryPointParam.setUpdateTime(new Date());
        summaryPointMapper.initUserSummaryPoint(summaryPointParam);
        return new ResponseData().success(null);
    }

    /**
     * @Description 激活冻结用户
     * @Date 21:31 2020/2/25
     **/
    @Override
    public ResponseData switchUser(Long userId, Integer state) {
        userMapper.updateSwitchUser(userId, state);
        return new ResponseData().success(null);
    }

    /**
     * @Description 分页查询会员列表
     * @Date 19:58 2020/2/26
     **/
    @Override
    public List<UserDTO> listUser() {
        List<UserEntity> userEntities = userMapper.listUser();
        List<UserDTO> userDTOs = new ArrayList<>();
        for (UserEntity userEntity : userEntities) {
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(userEntity, userDTO);
            userDTOs.add(userDTO);
        }
        return userDTOs;
    }

    /**
     * @Description 根据输入内容查询会员
     * @Date 10:29 2020/2/27
     **/
    @Override
    public List<UserDTO> listUserByInput(String inputContent) {
        List<UserEntity> userEntities = userMapper.listUserByInput(inputContent);
        List<UserDTO> userDTOs = new ArrayList<>();
        for (UserEntity userEntity : userEntities) {
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(userEntity, userDTO);
            userDTOs.add(userDTO);
        }
        return userDTOs;
    }

    /**
     * @Description 验证邮箱是否重复
     * @Date 19:30 2020/3/2
     **/
    public Integer countEmail(String email) {
        // 验证邮箱是否重复
        Integer emailCount = userMapper.countEmail(email);
        return emailCount;
    }

    /**
     * @Description修改昵称
     * @Date 13:58 2020/5/25
     **/
    @Override
    public ResponseData updateNickname(String userId, String newNickname) {
        userMapper.updateNickname(userId, newNickname);
        return new ResponseData().success(null);
    }

    /**
     * @Description修改头像
     * @Date 14:19 2020/5/25
     **/
    @Override
    public void updateHeadpicture(String businessLicenseUrl, String userId) {
        userMapper.updateHeadpicture(businessLicenseUrl, userId);
    }

    @Override
    public ResponseData updatePassword(String newPassword, String oldPassword, String userId) {
        String newPwd = SecureUtils.MD5Encrypt(newPassword);
        userMapper.updatePassword(newPwd, userId);
        return new ResponseData().success(null);
    }

    @Override
    public PointInfoDTO getPointInfo(String id) {
        SummaryPointEntity summaryPoint = summaryPointMapper.getByUserId(id);
        Integer nextNeedPoint = summaryPointMapper.getNextNeedPoint(summaryPoint.getPoint());
        PointInfoDTO pointInfoDTO = new PointInfoDTO();
        pointInfoDTO.setNextNeedPoint(nextNeedPoint);
        pointInfoDTO.setCurrentLevel(summaryPoint.getMemberLevelName());
        pointInfoDTO.setCurrentPoint(summaryPoint.getPoint());
        return pointInfoDTO;
    }

    @Override
    public ResponseData forgetPassword(String email, String emailCode, String newPassword) {
        EmailCodeEntity emailCodeByEmail = emailCodeMapper.getEmailCodeByEmail(email, UPDATE_PASSWORD.getEmailCodeType());
        if (!emailCode.equals(emailCodeByEmail.getCode())) {
            return new ResponseData().fail(EMAILCODE_NOT_CORRECT.getCode(), EMAILCODE_NOT_CORRECT.getMessage());
        }
        if (emailCodeByEmail.getExpirationTime().compareTo(new Date()) < 0) {
            return new ResponseData().fail(EMAILCODE_HAS_EXPIRED.getCode(), EMAILCODE_HAS_EXPIRED.getMessage());
        }
        userMapper.updatePasswordByEmail(SecureUtils.MD5Encrypt(newPassword), emailCodeByEmail.getToEmail());
        return new ResponseData().success(null);
    }

    @Override
    public ResponseData bindingPhoneNumber(String userId, String phoneNumber, String smsCode) throws MCTException {
        SmsCodeEntity phoneAndBussinessType = smsCodeMapper.getByPhoneAndBussinessType(phoneNumber, USERS_BINDING.getPhoneCodeType());
        if (!smsCode.equals(phoneAndBussinessType.getCode())) {
            return new ResponseData().fail(SMSCODE_NOT_CORRECT.getCode(), SMSCODE_NOT_CORRECT.getMessage());
        }
        if (phoneAndBussinessType.getExpirationTime().compareTo(new Date()) < 0) {
            return new ResponseData().fail(SMSCODE_HAS_EXPIRED.getCode(), SMSCODE_HAS_EXPIRED.getMessage());
        }
        userMapper.updatePhoneNumber(phoneNumber, userId);
        return new ResponseData().success(null);
    }
}
