package com.czAcqt.assistiveTools;

import com.czAcqt.checkingTools.AnswerChecking;
import com.czAcqt.generate.Calculate;
import com.czAcqt.generate.Expression;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * @class: CommandAnalyze
 * @description: TODO
 * @author: Naren
 * @date: 2020-04-11 10:00
 * @version 2.0
 */
public class CommandAnalyze {


    private Scanner sc;
    private Scanner sc1;
    private Scanner sc2;

    public CommandAnalyze(){
        sc = new Scanner(System.in);
        sc1 = new Scanner(System.in);
        sc2 = new Scanner(System.in);

    }
    public void command(){
        System.out.println("请输入待进行的操作【生成/校验】：[1/2]");
        String choose = sc.next();
        switch(choose) {
            case "1":
                int[] arrays = getRange();

                Expression expression = new Expression(arrays[0], arrays[1], new Calculate());
                expression.generateAllExpression();
                //单线程生成，会阻塞
                //TODO 调用你的文件写
                DataStorage dataStorage = new DataStorage();
                dataStorage.storeExp(expression.getExpressionList());
                dataStorage.storeAns(expression.getAnswerList());
                break;
            case "2":
                checkCommand();
                break;
            default:
                //TODO 记得改
                System.out.println("您的输入有误，请重新输入。");
                new CommandAnalyze();
        }
    }

    private void checkCommand() {
        System.out.println("输入校验命令【格式:Myapp.exe -e <exercisefile>.txt -a <answerfile>.txt】");
        String checkCommand = sc1.nextLine();
        String []file = checkCommand.split(" ");

        //作为命令格式合法与否的标记
        boolean flag = false;
        if(file[0].equals("Myapp.exe") && file[1].equals("-e") && file[3].equals("-a"))
            flag = true;

        //格式正确，将题目数和数值范围传给表达式生成类
        if(flag){
            //进行校验
            AnswerChecking ac = new AnswerChecking();
            ac.checkFile(file[2],file[4]);
            //格式错误，重新输入命令
        }else{
            System.out.println("无效命令！请检查您的命令格式。");
            checkCommand();
        }
    }

    /**
     * 获取题目数和数值范围
     * @throws
     */
    private int[] getRange() {//zai jian ,gou nanren

        System.out.println("请输入命令以生成对应数目题目：");
        String numCommand = sc1.nextLine();
        System.out.println("请输入命令以限制题目数值范围[2位数]：");
        String rangeCommand = sc2.nextLine();

        String []num = numCommand.split(" ");
        String []range = rangeCommand.split(" ");

        //作为命令格式合法与否的标记
        boolean flag = false;
        if(num[0].equals("Myapp.exe") && num[1].equals("-n") && range[0].equals("Myapp.exe") && range[1].equals("-r"))
            //如果题目数是>0的数字且数值范围是合理的
            if(num[2].matches("[^0][0-9]+") && range[2].matches("[^0][0-9]"))
                flag = true;

        //格式正确，将题目数和数值范围传给表达式生成类
        if(!flag){
            System.out.println("无效命令！请检查您的命令格式或数值范围。");
            return getRange();
            //TODO 传参检验
        //格式错误，重新输入命令
        } else {
            //返回结果集
            return new int[]{Integer.parseInt(num[2]), Integer.parseInt(range[2])};
        }
    }
}
