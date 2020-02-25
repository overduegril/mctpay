package com.mctpay.common.uitl;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.mctpay.common.config.UidGeneratorConfig;

/**
 * @Author: guodongwei
 * @Description: uid生成工具类
 * @Date: 2020/2/22 22:01
 */
public class UIdUtils {

    public  static  Long getUid() {
        UidGeneratorConfig uidGeneratorConfig = new UidGeneratorConfig();
        long centerId = uidGeneratorConfig.getCenterId();
        long workId = uidGeneratorConfig.getWorkId();
        Snowflake snowflake = IdUtil.createSnowflake(workId, centerId);
        return snowflake.nextId();
    }

}
