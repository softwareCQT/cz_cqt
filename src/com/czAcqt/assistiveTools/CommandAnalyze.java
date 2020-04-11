package com.czAcqt.assistiveTools;

import com.czAcqt.checkingTools.AnswerChecking;
import com.czAcqt.generate.Expression;

import java.io.IOException;
import java.util.Scanner;

/**
 * @class: CommandAnalyze
 * @description: TODO
 * @author: Naren
 * @date: 2020-04-11 10:00
 * @version 2.0
 */
public class CommandAnalyze {

    AnswerChecking ac = new AnswerChecking();
    Scanner sc = new Scanner(System.in);
    Scanner sc1 = new Scanner(System.in);
    Scanner sc2 = new Scanner(System.in);

    public CommandAnalyze(String choose){
        System.out.println("请输入待进行的操作【生成/校验】：[1/2]");
        choose = sc.next();
        switch(choose){
            case "1":
                getRange();
                break;
            case"2":
                checkCommand();
                break;
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
    private void getRange() {//zai jian ,gou nanren

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
        if(flag){
            //TODO 待添加calculate对象参数（在main中传参？）
//            new Expression(Integer.parseInt(num[2]), Integer.parseInt(range[2]),);
        //格式错误，重新输入命令
        }else{
            System.out.println("无效命令！请检查您的命令格式或数值范围。");
            getRange();
        }
    }
}
