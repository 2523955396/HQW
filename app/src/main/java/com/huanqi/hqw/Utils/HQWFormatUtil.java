package com.huanqi.hqw.Utils;

import android.text.TextUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;

/**
 * 文本格式库
 * 待测试 by焕奇灵动
 */
public class HQWFormatUtil {

    /**
     * 格式化金额 -- 去掉小数位最后面的0
     *
     * @param strAmount
     * @param decimal   保留小数位数
     * @param isFormat  是否需要格式成###,###,###,###,##0.000
     * @return 格式化后的金额
     */
    public static String formatAmountRemoveDecimalZero(String strAmount, int decimal, boolean isFormat) {
        if (TextUtils.isEmpty(strAmount) || "null".equals(strAmount)) {
            return "";
        }

        BigDecimal douAmount = new BigDecimal(strAmount).setScale(decimal, BigDecimal.ROUND_HALF_UP).stripTrailingZeros();//四舍五入 并去小数位多余的0
        String str = douAmount.toPlainString();
        if (isFormat) {
            //获取小数位数
            String[] num = str.split("\\.");
            int length = 0;
            if (num.length == 2) {
                length = num[1].length();
            }
            return formatAmount(str, length);
        } else {
            return str;
        }
    }


    /**
     * 格式化金额
     *
     * @param strAmount 字符串金额
     * @param decimal   几位小数
     * @return 格式后的金额
     */
    public static String formatAmount(String strAmount, int decimal) {
        if (TextUtils.isEmpty(strAmount) || "null".equals(strAmount)) {
            return "";
        }

        BigDecimal numDouble = new BigDecimal(strAmount).setScale(decimal, BigDecimal.ROUND_HALF_UP);
//        Double numDouble = Double.parseDouble(strAmount);
        String patten = null;
        switch (decimal) {
            case 3:
                patten = "###,###,###,###,##0.000";
                break;
            case 2:
                patten = "###,###,###,###,##0.00";
                break;
            case 1:
                patten = "###,###,###,###,##0.0";
                break;
            default:
                patten = "###,###,###,###,##0";
                break;
        }
        DecimalFormat df = new DecimalFormat(patten, DecimalFormatSymbols.getInstance(Locale.CHINA));
        return df.format(numDouble);
    }

    /**
     * 格式化金额
     *
     * @param amount  金额
     * @param decimal 几位小数
     * @return 格式后的金额
     */
    public static String formatAmount(double amount, int decimal) {
        BigDecimal numDouble = new BigDecimal(amount).setScale(decimal, BigDecimal.ROUND_HALF_UP);
        String patten = null;
        switch (decimal) {
            case 3:
                patten = "###,###,###,###,##0.000";
                break;
            case 2:
                patten = "###,###,###,###,##0.00";
                break;
            case 1:
                patten = "###,###,###,###,##0.0";
                break;
            default:
                patten = "###,###,###,###,##0";
                break;
        }
        DecimalFormat df = new DecimalFormat(patten, DecimalFormatSymbols.getInstance(Locale.CHINA));
        return df.format(numDouble);
    }


    /**
     * 格式化金额 -- 拼接货币
     */
    public static String formatAmount(double amount, String currency) {
        return formatAmount(amount, 2) + " " + currency;
    }

    /**
     * 当金额大于0小于0.01时金额保留三位小数否则保留两位
     */
    public static String formatAmountKeepThreeDecimal(double numDouble) {
        DecimalFormat df;
        if (numDouble > 0 && numDouble < 0.01) {
            df = new DecimalFormat("###,###,###,###,##0.000", DecimalFormatSymbols.getInstance(Locale.CHINA));
        } else {
            df = new DecimalFormat("###,###,###,###,##0.00", DecimalFormatSymbols.getInstance(Locale.CHINA));
        }
        return df.format(numDouble);
    }

    /**
     * 反格式化金额
     *
     * @param numString
     * @return
     */
    public static String parseAmountString(String numString) {
        String amount = "";
        if (TextUtils.isEmpty(numString)) {
            return "";
        }
        try {
            DecimalFormat df = new DecimalFormat("###,###,###,###,##0.00", DecimalFormatSymbols.getInstance(Locale.CHINA));
            amount = String.valueOf(df.parse(numString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return amount;
    }


}

