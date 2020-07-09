package com.mctpay.wallet.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: guodongwei
 * @Description: 扫码收款回调参数
 * @Date: 2020/5/27 11:02
 */
@ApiModel("扫码收款回调参数")
@Data
public class SweepCollectNotifyParam {

    private String is_success;

    @ApiModelProperty("用户ID")
    private String user_id;

    @ApiModelProperty("交易号")
    private String trade_no;

    @ApiModelProperty("商户交易号 Partner ID")
    private String partner_trans_id;

    @ApiModelProperty("交易时间 Transaction Time")
    private String pay_time;

    @ApiModelProperty("收付款商家 Merchat Name")
    private String merchant;

    @ApiModelProperty("收付款人 Payer")
    private String buyer_email;

    @ApiModelProperty("货币 Currency")
    private String currency;

    @ApiModelProperty("收付款总额")
    private String trans_amount;

    @ApiModelProperty("人民币总额")
    private String total_fee;

    @ApiModelProperty("汇率 Exchage Rate")
    private String forex_rate;

    @ApiModelProperty("付款方式 Payment Mode")
    private String pay_type;

    @ApiModelProperty("签名")
    private String sign_string;

    @ApiModelProperty("错误信息")
    private String error_msg;

    @ApiModelProperty("交易状态")
    private String trade_status;

}
