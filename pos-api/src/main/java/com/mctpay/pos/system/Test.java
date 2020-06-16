package com.mctpay.pos.system;

import java.math.BigDecimal;

/**
 * @Author: guodongwei
 * @Description: ${description}
 * @Date: 2020/6/15 9:47
 */
public class Test {

    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal(10.55);
        String amount = bigDecimal.setScale(0, BigDecimal.ROUND_FLOOR).toString();
        System.out.println(amount);
    }

}
