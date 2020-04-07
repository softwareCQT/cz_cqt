package com.czAcqt.generate;

import com.czAcqt.util.ResolveUtil;

import java.util.Map;

/**
 * @author chenqiting
 * 生成表达式的具体类
 */
public class Expression {
    /***
     * 生成题目的数量
     */
    private int questionCount;
    /***
     * 生成的数值范围大小
     */
    private int numberRange;

    public Expression(int questionCount, int numberRange) {
        this.numberRange = numberRange;
        this.questionCount = questionCount;
    }

    public Map<String, String> generateExpression(){
        //TODO 需要看清楚需求文档

        return null;
    }

    /***
     * 生成整数值
     * @return  返回整数值的字符串
     */
    public String generateInt(){
            //生成整数值
        return String.valueOf((int) (Math.random() * numberRange));
    }

    /***
     * 生成分数
     * @return 分数的字符串
     */
    public String generateFraction(){
        //需要定义好分子和分母
        int denominator = (int) (Math.random() * numberRange);
        int molecule = (int) (Math.random() * denominator * denominator);
        return ResolveUtil.createFraction(molecule, denominator);
    }


}
