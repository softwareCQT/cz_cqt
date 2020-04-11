package com.czAcqt;


import com.czAcqt.generate.Calculate;
import com.czAcqt.generate.Expression;

import java.util.ArrayList;
import java.util.List;

/***
 * @author chenqiting
 */
public class Main {

    public static void main(String[] args) {
        //示例
        Expression expression = new Expression(10000, 40, new Calculate());

        expression.generateAllExpression();
        List<String> expressionList = expression.getExpressionList();
        List<String> answerList = expression.getAnswerList();
        for (int i = 0, length = expressionList.size(); i < length; i++) {
            System.out.println(expressionList.get(i) + " " + answerList.get(i));
        }
    }
}
