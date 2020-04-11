package com.czAcqt.assistiveTools;

import java.io.*;
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
    public void setFileId(int flag) throws IOException {

        if(flag == 1) {//表达式
            System.out.println("请设置待生成文件的3位数字版号：");
            Scanner sc = new Scanner(System.in);
            id = sc.next();
            expFile = new File("Exercises" + id + ".txt");//存储表达式的文件
            if(!expFile.exists()) {
                expFile.createNewFile();
            }else{
                System.out.println("该文件已存在，请重新设置。");
                setFileId(flag);
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

        BufferedWriter expWriter = null;
        try {
            setFileId(1);
            expWriter = new BufferedWriter(new FileWriter(expFile,true));

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

        BufferedWriter ansWriter = null;
        try {
            setFileId(2);
            ansWriter = new BufferedWriter(new FileWriter(ansFile,true));
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

    /**
     * @function 将校验信息写入本地文件
     * @param correctList
     * @param wrongList
     */
    public void storeCheckInfo(List<Integer> correctList, List<Integer> wrongList) {
        BufferedWriter  checkWriter = null;
        try{
            //Correct信息
            checkWriter = new BufferedWriter( new FileWriter(new File("Grade.txt")));
            checkWriter.write("Correct:" + correctList.size());
            if(checkWriter != null) checkWriter.flush();
            String content = null;
            for (Integer questionId: correctList) {
                if(questionId == correctList.get(0))
                    content += "(" +questionId + ",";
                else if(questionId != correctList.get(correctList.size() - 1))
                    content += questionId + ")";
                else{
                    content += questionId+",";
                }
            }
            checkWriter.write(content + "\n");
            if(checkWriter != null) checkWriter.flush();

            //Wrong信息
            content = null;
            checkWriter.write("Correct:" + wrongList.size());
            if(checkWriter != null) checkWriter.flush();
            for (Integer questionId: wrongList) {
                if(questionId == wrongList.get(0))
                    content += "(" +questionId + ",";
                else if(questionId != correctList.get(correctList.size() - 1))
                    content += questionId + ")";
                else{
                    content += questionId+",";
                }
            }
            checkWriter.write(content + "\n");
            if(checkWriter != null) checkWriter.flush();
            if(checkWriter != null) checkWriter.close();
        }catch(Exception e){
            System.out.println("【校验结果写入异常】请检查文件路径。");
        }
    }
}
