package com.czAcqt.assistiveTools;

import com.czAcqt.checkingTools.AnswerChecking;
import com.czAcqt.generate.Calculate;
import com.czAcqt.generate.Expression;

import java.util.Scanner;


/**
 * @Class CommandAnalyze
 * @Destination 解析用户的指令
 * @Author Naren
 * @Date 2020/4/11 10:00
 * @Version 1.0
 */
public class CommandAnalyze {

    private Scanner sc;
    private Scanner sc1;
    private Scanner sc2;

    /**
     * 空参构造方法 用于初始化
     * @author Naren
     */
    public CommandAnalyze(){
        sc = new Scanner(System.in);
        sc1 = new Scanner(System.in);
        sc2 = new Scanner(System.in);

    }

    /**
     * 获取用户指令并选择进行相应操作
     * @author Naren
     */
    public void command(){
        String con = "";
        do{
            System.out.println("请输入待进行的操作【生成/校验】：[1/2]");
            String choose = sc.next();
            switch(choose) {
                case "1":
                    //获取表达式参数
                    int[] arrays = getRange();
                    Expression expression = new Expression(arrays[0], arrays[1], new Calculate());
                    //生成表达式，单线程生成，会阻塞
                    expression.generateAllExpression();
                    //将生成的表达式和答案写入对应文件
                    DataStorage dataStorage = new DataStorage();
                    dataStorage.storeExp(expression.getExpressionList());
                    dataStorage.storeAns(expression.getAnswerList());
                    break;
                case "2":
                    //进行校验命令格式判断
                    checkCommand();
                    break;
            }
            System.out.println("是否退出？【是/否】[y/else]");
            con = sc.next();
        }while(!con.equalsIgnoreCase("y"));
    }

    /**
     * 检查用户校验命令的格式
     * @author Naren
     */
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
     * 获取用户指定的题目数和数值范围
     * @return int[]
     * @author Naren
     */
    private int[] getRange(){

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
        //格式错误，重新输入命令
        } else {
            //返回结果集
            return new int[]{Integer.parseInt(num[2]), Integer.parseInt(range[2])};
        }
    }
}
