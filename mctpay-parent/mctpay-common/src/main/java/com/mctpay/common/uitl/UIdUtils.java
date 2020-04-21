package com.mctpay.common.uitl;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.mctpay.common.config.UidGeneratorConfig;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: guodongwei
 * @Description: uid生成工具类
 * @Date: 2020/2/22 22:01
 */
public class UIdUtils {

    public  static  Long getUid() {
        long centerId = UidGeneratorConfig.centerId;
        long workId = UidGeneratorConfig.workId;
        Snowflake snowflake = IdUtil.createSnowflake(workId, centerId);
        return snowflake.nextId();
    }

}
