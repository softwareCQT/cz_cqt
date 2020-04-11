package com.czAcqt.generate;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author chenqiting
 * 计算的类，主要通过后缀表达式来计算结果
 */
public class Calculate{
    /***
     * 计算表达式
     * @param expression 表达式
     * @param permit 允许存在负数的运算过程
     * @return 结果
     */
     public String calculate(String expression, boolean permit) {
        if (expression == null) {
            return null;
        }
        //先生成后缀表达式数组，然后手动控制数组进行操作
        String[] afterExp = middleToAfter(expression);
        Stack<String> stack = new Stack<>();
        try {
            for (int index = 0; index < afterExp.length; index++) {
                if (afterExp[index].matches("[0-9/']+")) {
                    stack.push(afterExp[index]);
                } else {
                    String b = stack.pop();
                    String a = stack.pop();

                    String result = Symbol.value(afterExp[index]).calculate(a, b);
                    //计算过程中存在负数，重新生成表达式
                    if (result.startsWith("-") && !permit){
                        return null;
                    }
                    stack.push(result);
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e){
            System.out.println("存在表达式不合法");
        }
        return stack.pop();
    }

    /***
     * 中缀表达式转换成后缀表达式
     * @param expression 表达式
     * @return 数组
     */
    private String[] middleToAfter(String expression) {
        //用来转换的栈
        Stack<String> stack = new Stack<>();
        //表达式每个字符前后都会生成一个空格
        String[] strings = expression.split("\\s");
        //返回的list
        List<String> stringList = new ArrayList<>(strings.length);
        for (int index = 0; index < strings.length; index++) {
            if ('0'<= strings[index].charAt(0) && strings[index].charAt(0) <= '9'){
                //数字直接输出
                stringList.add(strings[index]);
            } else if (strings[index].equals(Symbol.BEGIN.getSymbol())) {
                //开始括号压进栈
                stack.push(strings[index]);
            }else if (strings[index].equals(Symbol.END.getSymbol())){
                //把所有运算符都出栈
                while (!stack.peek().equals(Symbol.BEGIN.getSymbol())){
                    stringList.add(stack.pop());
                }
                //出栈开始括号
                stack.pop();
            }else if (strings[index].equals(Symbol.MULTIPLY.getSymbol())
                    || strings[index].equals(Symbol.DIVIDE.getSymbol())){
                //判断上一级符号是什么
                boolean flag = !stack.isEmpty() && (stack.peek().equals(Symbol.MULTIPLY.getSymbol())
                        || stack.peek().equals(Symbol.DIVIDE.getSymbol()));
                if (flag){
                    stringList.add(stack.pop());
                }
                stack.push(strings[index]);
            } else if (strings[index].equals(Symbol.SUB.getSymbol())
                    || strings[index].equals(Symbol.ADD.getSymbol())){
                //此处应该为+，-号
                boolean flag = !stack.isEmpty() && (stack.peek().equals(Symbol.ADD.getSymbol())
                        || stack.peek().equals(Symbol.SUB.getSymbol()));
                if (flag) {
                    stringList.add(stack.pop());
                }
                stack.push(strings[index]);
            } else{
                //有其他符号，直接跳出，可能是=号
                break;
            }
        }
        while (!stack.isEmpty()){
            stringList.add(stack.pop());
        }
        //返回数组
        return stringList.toArray(new String[0]);
    }
}
