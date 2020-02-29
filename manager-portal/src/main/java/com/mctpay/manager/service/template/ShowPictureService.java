package com.mctpay.manager.service.template;

import com.mctpay.common.base.model.ResponseData;
import com.mctpay.manager.model.dto.template.ShowPicturerDTO;
import com.mctpay.manager.model.param.ShowPicturerParam;

import java.util.List;

/**
 * @Author: chenshubiao
 * @Description: 轮播图相关接口
 * @Date: 2020/2/29 23 19
 */
public interface ShowPictureService {
    /**
     * @Description 添加轮播广告
     * @Date  23:29 2020/2/29
     **/
    ResponseData insertShowPicturer(ShowPicturerParam showPicturerParam);
    /**
     * @Description  获取所有轮播图
     * @Date  23:29 2020/2/29
     **/
    List<ShowPicturerDTO> listShowPicturer();
    /**
     * @Description  修改轮播图状态
     * @Date  00:00 2020/3/1
     **/
    public ResponseData updateSwitchShowPicturer(long showPicturerId,Integer status);
}
