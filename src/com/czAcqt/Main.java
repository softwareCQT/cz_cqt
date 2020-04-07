package com.czAcqt;


import com.czAcqt.generate.Calculate;

import java.util.ArrayList;

/***
 * @author chenqiting
 */
public class Main {

    public static void main(String[] args) {
        //TODO  检查参数，并检索

        //TODO 根据参数开始生成表达式

        //TODO 表达式生成结果返回，以Map<String, String>的question，answer形式

        //TODO 写入文件
        Calculate calculate = new Calculate();
        System.out.println(calculate.calculate("100 - 200"));
    }
}
