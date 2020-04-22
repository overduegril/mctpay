package com.mctpay.manager.keyvalue;

/**
 * @author liqiang
 * @date 2020/4/22 11:48
 * @Description: (what)
 * (why)
 * (how)
 */
public class CodeEnumUtil {
    public static <E extends Enum<?> & BaseEnum> E codeOf(Class<E> enumClass, int code) {
        E[] enumConstants = enumClass.getEnumConstants();
        for (E e : enumConstants) {
            if (e.getValue() == code)
                return e;
        }
        return null;
    }
}
