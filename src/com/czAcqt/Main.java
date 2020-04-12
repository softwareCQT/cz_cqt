package com.czAcqt;


import com.czAcqt.assistiveTools.CommandAnalyze;

/***
 * @author chenqiting
 */
public class Main {
    /**
     * @return void
     * @Author chenqiting
     * @Date 2020-04-11 21:00
     * @Description main方法
     * @Param String[]
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
        //TODO 无法校验错误的数据
        //获取用户指令
        CommandAnalyze commandAnalyze = new CommandAnalyze();
        commandAnalyze.command();
    }
}
