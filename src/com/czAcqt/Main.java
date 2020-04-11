package com.czAcqt;


import com.czAcqt.assistiveTools.CommandAnalyze;
import com.czAcqt.generate.Calculate;
import com.czAcqt.generate.Expression;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/***
 * @author chenqiting
 */
public class Main {
    /**
     * @Author chenqiting
     * @Date  2020-04-11 21:00
     * @Description main方法
     * @Param String[]
     * @return void
     */
    public static void main(String[] args) {
        /*//起廷的示例
        Expression expression = new Expression(10000, 40, new Calculate());

        expression.generateAllExpression();
        List<String> expressionList = expression.getExpressionList();
        List<String> answerList = expression.getAnswerList();
        for (int i = 0, length = expressionList.size(); i < length; i++) {
            System.out.println(expressionList.get(i) + " " + answerList.get(i));
        }*/

        //获取用户指令
        CommandAnalyze commandAnalyze = new CommandAnalyze();
        commandAnalyze.command();

    }
}
