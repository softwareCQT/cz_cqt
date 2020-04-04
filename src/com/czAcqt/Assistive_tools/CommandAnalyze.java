package com.czAcqt.Assistive_tools;

import com.czAcqt.generate.Expression;

import java.io.IOException;
import java.util.Scanner;

/**
 * 命令解析类V1.0：解析用户输入的命令
 */
public class CommandAnalyze {

    Scanner sc1 = new Scanner(System.in);
    Scanner sc2 = new Scanner(System.in);

    /**
     * 获取题目数和数值范围
     * @throws IOException
     */
    public void getRange() throws IOException {

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
            //TODO 当前为通过构造方法传参（可能待修改）
            new Expression(Integer.parseInt(num[2]), Integer.parseInt(range[2]));
        //格式错误，重新输入命令
        }else{
            System.out.println("无效命令！请检查您的命令格式或数值范围。");
            getRange();
        }


    }
}
