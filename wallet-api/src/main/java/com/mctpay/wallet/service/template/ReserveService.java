package com.mctpay.wallet.service.template;

import com.mctpay.wallet.model.param.ReserveParam;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author: guodongwei
 * @Description: 预约业务接口
 * @Date: 2020/5/31 14:40
 */
public interface ReserveService {

    void reserve(ReserveParam reserveParam);

}
