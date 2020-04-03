package com.czAcqt.generate;

import java.util.Map;

/**
 * @author chenqiting
 * 生成表达式的具体类
 */
public class Expression {
    /***
     * 生成题目的数量
     */
    int questionCount;
    /***
     * 生成的数值范围大小
     */
    int numberRange;

    public Expression(int questionCount, int numberRange) {
        this.numberRange = numberRange;
        this.questionCount = questionCount;
    }

    public Map<String, String> generateExpression(){
        //TODO 需要看清楚需求文档
        return null;
    }
}
