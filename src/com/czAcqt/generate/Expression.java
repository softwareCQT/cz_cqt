package com.czAcqt.generate;

import com.czAcqt.util.ResolveUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * @author chenqiting
 * 生成表达式的具体类
 */
public class Expression {
    /***
     * 计算
     */
    private final Calculate calculate;
    /***
     * 生成题目的数量
     */
    private int questionCount;
    /***
     * 生成的数值范围大小
     */
    private int numberRange;
    /***
     * 表达式List
     */
    private List<String> expressionList;

    public List<String> getExpressionList() {
        return expressionList;
    }

    public List<String> getAnswerList() {
        return answerList;
    }

    /***
     * 答案List
     */
    private List<String> answerList;
    /***
     * 生成表达式的size，可用来计算进度
     */
    private int nowExpressionSize;
    /***
     * 操作符个数
     */
    private final static int OPERATOR_SIZE = 4;
    /***
     * 最多的操作符数
     */
    private final static int MAX_OPERATOR_SIZE = 3;

    /***
     * 生成文件的构造函数
     * @param questionCount 表达式的数量
     * @param numberRange   表达式的
     * @param calculate
     */
    public Expression(int questionCount, int numberRange, Calculate calculate) {
        this.numberRange = numberRange;
        this.questionCount = questionCount;
        this.calculate = calculate;
        expressionList = new ArrayList<>(questionCount);
        answerList = new ArrayList<>(questionCount);
        nowExpressionSize = 0;
    }

    /***
     * 比对答案的构造函数
     * @param calculate 传进计算类
     */
    public Expression(Calculate calculate) {
        this.calculate = calculate;
    }

    /***
     * 生成表达式成功
     * @return 是否生成过程中用户有其他操作，返回错误
     */
    public boolean generateAllExpression() {
        for (int i = 0; i < questionCount; ) {
            if (i % 100 == 0) {
                //打印进度
            }
            //生成表达式
            String expression = generateExpression();
            //计算出来的结果
            String result = calculate.calculate(expression, false);
            //判断结果
            boolean flag = checkExpressionExistAndResultIllegal(expression, result);
            //表达式重复，不进行操作，不重复加入列表
            if (!flag) {
                expressionList.add(expression);
                answerList.add(result);
                i++;
                nowExpressionSize++;
            }
        }
        return false;
    }

    /***
     * 检查表达式是否已经存在或者重复
     * @param expression 表达式
     * @param result  结果
     * @return 是否重复
     */
    private boolean checkExpressionExistAndResultIllegal(String expression, String result) {
        if (Objects.isNull(result)) {
            return true;
        }
        //当前没有表达式
        if (nowExpressionSize == 0) {
            return false;
        }
        //API的一些操作也是循环，效率低下，手动循环
        for (int i = 0, j = nowExpressionSize - 1; i <= j; i++, j--) {
            if (expressionList.get(i).equals(expression) || expressionList.get(j).equals(expression)) {
                return true;
            }
            //查看是否答案有相同的
            if (answerList.get(i).equals(result)) {
                return checkCharEquals(expressionList.get(i), expression);
            } else if (answerList.get(j).equals(result)) {
                return checkCharEquals(expressionList.get(j), expression);
            }
        }
        return false;
    }

    /***
     *
     * @param oldExpression 存在的表达式
     * @param newExpression 还没存进去的表达式
     * @return 相同，不相同
     */
    private boolean checkCharEquals(String oldExpression, String newExpression) {
        String[] oldExpressionArrays = oldExpression.split("\\s+");
        String[] newExpressionArrays = newExpression.split("\\s+");
        int oldExpressionNumber = 0;
        int equalsNumber = 0;
        //是否为数字
        boolean flag;
        //开始遍历
        for (String oldString : oldExpressionArrays) {
            flag = oldExpression.matches("[0-9'/]+");
            if (flag) {
                oldExpressionNumber++;
            }
            //比对
            for (String newString : newExpressionArrays) {
                if (oldString.equals(newString)) {
                    equalsNumber++;
                }
            }
        }
        //返回校验结果
        return oldExpressionNumber == equalsNumber;
    }


    /***
     * 生成表达式
     * @return 生成表达式
     */
    private String generateExpression() {
        //随机运算符大小
        int operatorSize = (int) (Math.random() * MAX_OPERATOR_SIZE) + 1;
        int numberSize = operatorSize + 1;
        //判断是否需要生成括号,1/4的概率
        boolean flag = (int) (Math.random() * MAX_OPERATOR_SIZE) == 0;
        //标记（产生的位置）
        int mark = -1;
        if (flag) {
            //随机插入括号的位置
            mark = (int) (Math.random() * operatorSize);
        }

        StringBuilder expression = new StringBuilder();
        //遍历产生数字和符号，你一下我一下
        for (int i = 0; i < numberSize; i++) {
            if (mark == i) {
                myAppend(expression, "(");
            }
            //生成数字
            myAppend(expression, (int) (Math.random() * 2) == 0 ? generateFraction() : generateInt());

            //判断是否加入结束符号，判断是否结尾
            if (mark >= 0 && mark < i) {
                //已经到了表达式结尾, 此时必须结束
                if (i == operatorSize) {
                    myAppend(expression, ")");
                    break;
                }
                //判断是否需要结束
                flag = (int) (Math.random() * 2) == 0;
                if (flag) {
                    myAppend(expression, ")");
                    mark = -1;
                }
            }
            if (i < operatorSize) {
                //然后生成一个操作符
                myAppend(expression, generateOperator());
            }
        }
        //最后补充等号
        expression.append("=");
        return expression.toString();
    }

    /***
     * 生成整数值
     * @return 返回整数值的字符串
     */
    private String generateInt() {
        //生成整数值
        return String.valueOf((int) (Math.random() * numberRange));
    }

    /***
     * 生成分数
     * @return 分数的字符串
     */
    private String generateFraction() {
        //需要定义好分子和分母
        int denominator = (int) (Math.random() * numberRange);
        if (denominator == 0) {
            denominator = numberRange;
        }
        int molecule = (int) (Math.random() * denominator * denominator);
        return ResolveUtil.createFraction(molecule, denominator);
    }

    /***
     * 生成操作符，不包括括号
     * @return 操作符
     */
    private String generateOperator() {
        int random = (int) (Math.random() * OPERATOR_SIZE);

        if (random == Symbol.ADD.ordinal()) {
            return Symbol.ADD.getSymbol();
        } else if (random == Symbol.DIVIDE.ordinal()) {
            return Symbol.DIVIDE.getSymbol();
        } else if (random == Symbol.SUB.ordinal()) {
            return Symbol.SUB.getSymbol();
        } else {
            return Symbol.MULTIPLY.getSymbol();
        }
    }

    /***
     * 加入了空格分隔符
     */
    private void myAppend(final StringBuilder expression, String string) {
        expression.append(string);
        expression.append(" ");
    }

    /***
     * @param questionList 把用户的表达式传进来
     * @return list 正确的答案
     */
    public List<String> getCorrectAnswerList(List<String> questionList) {
        //结果集
        List<String> answerList = new ArrayList<>();
        //遍历
        for (String question : questionList) {
            answerList.add(calculate.calculate(question, true));
        }
        return answerList;
    }
}
