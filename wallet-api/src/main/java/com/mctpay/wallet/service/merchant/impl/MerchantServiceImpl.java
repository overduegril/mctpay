package com.mctpay.wallet.service.merchant.impl;

import cn.hutool.core.date.DateUtil;
import com.mctpay.common.uitl.MapUtils;
import com.mctpay.wallet.mapper.merchant.MerchantMapper;
import com.mctpay.wallet.mapper.merchant.TradeRecordMapper;
import com.mctpay.wallet.model.dto.card.CardDTO;
import com.mctpay.wallet.model.dto.merchant.MerchantDtO;
import com.mctpay.wallet.model.dto.merchant.TradeRecordDTO;
import com.mctpay.wallet.model.entity.merchant.MctTradeRecordEntity;
import com.mctpay.wallet.model.entity.merchant.MerchantEntity;
import com.mctpay.wallet.model.param.TradeRecordParam;
import com.mctpay.wallet.service.card.MerchantCardService;
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
    @Autowired
    private MerchantCardService merchantCardService;

    @Autowired
    private TradeRecordMapper tradeRecordMapper;

    @Override
    public List<MerchantDtO> listMerchantByInput(Double lat, Double lon,  String inputContent) {
        //double lat = 23.12564488053505; //当前纬度
        //double lon = 113.34385183452603; //当前经度

        long raidus = 1000000000; //半径10km
        List<MerchantEntity> merchantEntities = null;
        if(lat == null || lon == null) {
            lat = 1.3122618 ; //当前纬度
            lon =  103.863018; //当前经度
        }

            Map<String, Object> param = new HashMap<>();
            param.put("lat", lat);
            param.put("lon", lon);
            MapUtils.loadGeoSquare(param,lat,lon,raidus);
            param.put("inputContent",inputContent);

            merchantEntities = merchantMapper.listMerchantByInput(param);


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
            if(lon != null && lat != null){
                if(StringUtils.hasText(merchantEntity.getLatitude()) && StringUtils.hasText(merchantEntity.getLongitude())){
                    double distance = MapUtils.getDistance(new Double(merchantEntity.getLatitude()),new Double(merchantEntity.getLongitude()),lat,lat);
                    merchantDtO.setDistance(distance);
                    List<CardDTO> cardDTOList = merchantCardService.listCard(merchantEntity.getId());
                    if(cardDTOList != null && cardDTOList.size() > 0){
                        merchantDtO.setCardNum(cardDTOList.size());
                    }else{
                        merchantDtO.setCardNum(0);
                    }
                }

            }
            merchantDtOs.add(merchantDtO);
        }
        return merchantDtOs;
    }

    @Override
    public void insertTradeRecord(TradeRecordParam tradeRecordParam) {
        tradeRecordMapper.insert(tradeRecordParam);
    }

    @Override
    public List<TradeRecordDTO> listTradeRecord(String userId, String inputContent) {
        List<MctTradeRecordEntity> tradeRecordEntities = tradeRecordMapper.listTradeRecordByMerchantId(userId, inputContent);
        List<TradeRecordDTO> tradeRecordDTOs = new ArrayList<>();
        for (MctTradeRecordEntity tradeRecordEntity : tradeRecordEntities) {
            TradeRecordDTO tradeRecordDTO = new TradeRecordDTO();
            BeanUtils.copyProperties(tradeRecordEntity, tradeRecordDTO);
            tradeRecordDTOs.add(tradeRecordDTO);
        }
        return tradeRecordDTOs;
    }


}
