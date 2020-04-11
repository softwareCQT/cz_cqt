package com.czAcqt.assistiveTools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * 数据存储类V1.0
 */
public class DataStorage {

    File expFile = null;
    File ansFile = null;
    String id = null;

    /**
     * @function 设置文件版号以方便匹配题目与答案
     * @param flag
     * @throws IOException
     */
    public void setfileId(int flag) throws IOException {

        if(flag == 1) {//表达式
            System.out.println("请设置待生成文件的3位数字版号：");
            Scanner sc = new Scanner(System.in);
            id = sc.next();
            expFile = new File("Exercises" + id + ".txt");//存储表达式的文件
            if(!expFile.exists()) {
                expFile.createNewFile();
            }else{
                System.out.println("该文件已存在，请重新设置。");
                setfileId(flag);
            }
        }
        if(flag == 2) {//答案
            ansFile = new File("Answers" + id + ".txt"); //存储答案的文件
            if(!ansFile.exists()) {
                ansFile.createNewFile();
            }
        }

    }
    /**
     * 将表达式写入本地文件
     * @param questionList
     * @throws IOException
     */
    public void storeExp(List<String> questionList) {

        try {
            setfileId(1);
            BufferedWriter expWriter = new BufferedWriter(new FileWriter(expFile,true));

            //循环将表达式写入文件
            int num = 1;
            for(String exp: questionList){
                expWriter.write(num++ + "." + exp + "\n");
                expWriter.flush();
            }
            if(expWriter != null){
                expWriter.close();
            }
            System.out.println("题目文件生成成功。");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 将答案写入本地文件
     * @param answerList
     * @throws IOException
     */
    public void storeAns(List<String> answerList) {

        try {
            setfileId(2);
            BufferedWriter ansWriter = new BufferedWriter(new FileWriter(ansFile,true));
            for(int i = 1;i <= answerList.size();i++){
                ansWriter.write(i + "." + answerList.get(i) + "\n");
                ansWriter.flush();
            }
            if(ansWriter != null){
                ansWriter.close();
            }
            System.out.println("答案文件生成成功。");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
