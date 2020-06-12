package com.mctpay.wallet.service.merchant.impl;

import cn.hutool.core.date.DateUtil;
import com.mctpay.common.uitl.MapUtils;
import com.mctpay.wallet.mapper.merchant.MerchantMapper;
import com.mctpay.wallet.model.dto.merchant.MerchantDtO;
import com.mctpay.wallet.model.entity.merchant.MerchantEntity;
import com.mctpay.wallet.service.merchant.MerchantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: guodongwei
 * @Description: 商户serviceImpl
 * @Date: 2020/2/24 10:27
 */
@Service
@Slf4j
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private MerchantMapper merchantMapper;


    @Override
    public List<MerchantDtO> listMerchantByInput(Double lat, Double lon,  String inputContent) {

        long raidus = 10000; //半径10km
        //double lat = 23.12564488053505; //当前纬度
        //double lon = 113.34385183452603; //当前经度
        List<MerchantEntity> merchantEntities = null;
        if(lat == null || lon == null){
            merchantEntities = merchantMapper.listAllMerchant();
        }else{
            Map<String, Object> param = new HashMap<>();
            param.put("lat", lat);
            param.put("lon", lon);
            MapUtils.loadGeoSquare(param,lat,lon,raidus);
            param.put("inputContent",inputContent);

            merchantEntities = merchantMapper.listMerchantByInput(param);
        }

        // 计算商户的营业时间
        for (MerchantEntity merchantEntity : merchantEntities) {
            Integer businessStatus = 1;
            String businessTime = merchantEntity.getBusinessTime();
            if (!StringUtils.isEmpty(businessTime)) {
                String[] businessTimes = businessTime.split(";");
                String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                for (String time : businessTimes) {
                    boolean inRange = false;
                    try {
                        String startTime = time.substring(0, time.indexOf("-"));
                        String endTime = time.substring(time.indexOf("-") + 1);
                        Date startDate = DateUtil.parseDateTime(date + " " + startTime + ":00");
                        Date endDate = DateUtil.parseDateTime(date + " " + endTime + ":00");
                        inRange = DateUtil.isIn(new Date(), startDate, endDate);
                        // 防止输错影响到整个列表的查询
                    } catch (Exception e) {
                        log.debug(e.toString());
                        businessStatus = -1;
                    }
                    if (inRange && businessStatus != -1) {
                        businessStatus = 1;
                        break;
                    }else if (!inRange && businessStatus != -1){
                        businessStatus = 0;
                    }
                }
            }
                merchantEntity.setBusinessStatus(businessStatus);
        }
        List<MerchantDtO> merchantDtOs = new ArrayList<>();
        for(MerchantEntity merchantEntity : merchantEntities){
            MerchantDtO merchantDtO = new MerchantDtO();
            BeanUtils.copyProperties(merchantEntity,merchantDtO);
            merchantDtOs.add(merchantDtO);
        }
        return merchantDtOs;
    }

}
