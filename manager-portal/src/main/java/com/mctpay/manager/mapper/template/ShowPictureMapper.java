package com.mctpay.manager.mapper.template;

import com.mctpay.common.base.mapper.BaseMapper;
import com.mctpay.manager.model.entity.template.ShowPictureEntity;
import com.mctpay.manager.model.param.ShowPicturerParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dongwei_guo
 * @date 2020-02-23 18:23:00
 */
@Repository
public interface ShowPictureMapper extends BaseMapper<ShowPictureEntity> {
    /**
     * @Description 插入轮播广告
     * @Date 23:31 2020/2/29
     **/
    void insertShowPicturer(ShowPicturerParam showPicturerParam);
    /**
     * @Description 插入轮播广告
     * @Date 23:49 2020/2/29
     **/
    List<ShowPictureEntity> listShowPicturer(String useTypeCode);
    /**
     * @Description  修改轮播图状态
     * @Date  00:03 2020/3/1
     **/
    void updateSwitchShowPicturer(@Param("showPicturerId") Long showPicturerId , @Param("status") Integer  status);
}
