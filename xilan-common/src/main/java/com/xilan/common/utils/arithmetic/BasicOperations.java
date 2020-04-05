package com.xilan.common.utils.arithmetic;

import java.text.DecimalFormat;

/**
 * @author like
 * @version 1.0
 * @date 2020/4/5 20:55
 */
public class BasicOperations {

    /**
     * 算术除法
     * @param a
     * @param b
     * @return
     */
    public static String txfloat(long a,long b) {
        // TODO 自动生成的方法存根
        DecimalFormat df = new DecimalFormat("0.0");//设置保留位数
        return df.format((float) a / b);
    }
}
