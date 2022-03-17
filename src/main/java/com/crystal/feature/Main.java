package com.crystal.feature;

import com.crystal.feature.common.constant.CommonCodeConstant;

import java.text.DecimalFormat;

/**
 * @author CHUNHAO LIU
 */
public class Main {

    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("0.00");
        Integer totalTime=3;

        String result=df.format((float) totalTime / Integer.valueOf(CommonCodeConstant.HAPPY_EIGHT_LAST_SIXTEEN) * 100) + "%";
        System.out.println(result);
    }
}
