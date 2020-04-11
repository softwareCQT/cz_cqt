package com.czAcqt.assistiveTools;

import java.io.*;
import java.util.List;
import java.util.Scanner;

/**
 * @Class CommandAnalyze
 * @Destination 将数据写入文件
 * @Author Naren
 * @Date 2020/4/11 10:00
 * @Version 1.0
 */
public class DataStorage {

    private File expFile;
    private File ansFile;
    private String id;

    /**
     * 设置文件版号以方便匹配题目与答案
     * @param flag
     * @throws IOException
     */
    private void setFileId(int flag) throws IOException {
        //表达式
        if(flag == 1) {
            System.out.println("请设置待生成文件的3位数字版号：");
            Scanner sc = new Scanner(System.in);
            id = sc.next();
            //存储表达式的文件
            expFile = new File("Exercises" + id + ".txt");
            if(!expFile.exists()) {
                expFile.createNewFile();
            }else{
                System.out.println("该文件已存在，请重新设置。");
                setFileId(flag);
            }
        }
        if(flag == 2) {
            //存储答案的文件
            ansFile = new File("Answers" + id + ".txt");
            if(!ansFile.exists()) {
                ansFile.createNewFile();
            }
        }

    }

    /**
     * 将表达式写入本地文件
     * @param questionList
     */
    void storeExp(List<String> questionList) {

        try {
            setFileId(1);
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
     */
    void storeAns(List<String> answerList) {
        //Myapp.exe -e  -a Answers001.txt

        try {
            setFileId(2);
            BufferedWriter ansWriter = new BufferedWriter(new FileWriter(ansFile,true));
            for(int i = 0, length = answerList.size();i < length;i++){
                ansWriter.write((i + 1) + "." + answerList.get(i) + "\n");
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
     * 将校验信息写入本地文件
     * @param correctList
     * @param wrongList
     */
    public void storeCheckInfo(List<Integer> correctList, List<Integer> wrongList) {

        try{
            //Correct信息
            BufferedWriter  checkWriter = new BufferedWriter( new FileWriter(new File("Grade.txt")));
            checkWriter.write("Correct:" + correctList.size());
            if(checkWriter != null) checkWriter.flush();
            String content = "";
            for (Integer questionId: correctList) {
                if(questionId.equals(correctList.get(0)))
                    content += "(" +questionId + ",";
                else if(!questionId.equals(correctList.get(correctList.size() - 1)))
                    content += questionId + ",";
                else{
                    content += questionId+")";
                }
            }
            checkWriter.write(content + "\n");
            if(checkWriter != null) checkWriter.flush();

            //Wrong信息
            content = "";
            checkWriter.write("Wrong:" + wrongList.size());
            if(checkWriter != null) checkWriter.flush();
            if(wrongList.size() == 0){
                if(checkWriter != null) checkWriter.flush();
                if(checkWriter != null) checkWriter.close();
                return;
            }else if(wrongList.size() == 1){
                content += "(" + wrongList.get(0) + ")";
            }else {
                for (Integer questionId: wrongList) {
                    if(questionId.equals(wrongList.get(0)))
                        content += "(" +questionId + ",";
                    else if(questionId != wrongList.get(wrongList.size() - 1))
                        content += questionId + ",";
                    else{
                        content += questionId+")";
                    }
                }
            }

            checkWriter.write(content + "\n");
            if(checkWriter != null) checkWriter.flush();
            if(checkWriter != null) checkWriter.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
