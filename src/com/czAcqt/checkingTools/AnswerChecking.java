package com.czAcqt.checkingTools;

import com.czAcqt.assistiveTools.DataStorage;
import com.czAcqt.generate.Calculate;
import com.czAcqt.generate.Expression;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Class: AnswerChecking
 * @Description: 校验答案
 * @Author: Naren
 * @Date: 2020/4/11 11:33
 * @Version 1.0
 */
public class AnswerChecking {

    //保存正确/错误题号的队列
    private List<Integer> correctList = new ArrayList<>();
    private List<Integer> wrongList = new ArrayList<>();

    /**
     * 校验待检测文件状态
     * @param exersicesFile
     * @param answerFile
     * @author Naren
     */
    public void checkFile(String exersicesFile,String answerFile) {
            //存储表达式的文件
            File expFile = new File(exersicesFile);
            //存储答案的文件
            File ansFile = new File(answerFile);
            //待校验答案文件不存在
            if(!ansFile.exists()){
                System.out.println("未找到待检验答案文件。");
                return;
            }
            //如果表达式文件不存在
            if(!expFile.exists()) {
                System.out.println("未找到指定题目文件。");
                return;
            }
            //如果全部文件名都正确，检测待校对题目文件是否存在于系统生成历史中
            String id = exersicesFile.substring(9,12);
            String myAnswerFile = "Answers" + id + ".txt"; //Myapp.exe -e Exercises002.txt -a Answers002.txt
            File myAnswer = new File(myAnswerFile);
            //若系统中不存在与题目文件相符合的答案文件
            if(!myAnswer.exists()){
                try {
                    FileReader fr = new FileReader(expFile);
                    BufferedReader br = new BufferedReader(fr);
                    String content = null;
                    ArrayList<String> questionList = new ArrayList<>();
                    while((content = br.readLine()) != null){//(?m)^.*$
                        content = content.split("\\.")[1];
                        questionList.add(content);
                    }
                    //调用起廷方法获得答案队列
                    Expression ex = new Expression(new Calculate());
                    //TODO 传参检验
                    List<String> answerList = ex.getCorrectAnswerList(questionList);
                    //比对
                    checkAnswer(myAnswer,answerList);
                } catch (Exception e) {
                    System.out.println("【答案队列比对异常】文件状态异常");
                }
            }else{
                //调用本类检验方法比对答案文件
                checkAnswer(myAnswer,ansFile);
            }
    }

    /**
     * 将待校验答案文件与现场计算出的答案队列进行比对
     * @param myAnswer
     * @param answerList
     * @author Naren
     */
    private void checkAnswer(File myAnswer, List<String> answerList){
        try {
            //待检测答案文件
            FileReader fr1 = new FileReader(myAnswer);
            BufferedReader br1 = new BufferedReader(fr1);

            LinkedHashMap<Integer,String> map1 = new LinkedHashMap<>();

            String content = "";
            while((content = br1.readLine()) != null){
                content = content.replaceAll(" +", "").replaceAll("\uFEFF", "");
                //map1待校验答案：key:序号，value:答案
                map1.put(Integer.valueOf(content.split("//.")[0]),content.split("\\.")[1]);
            }

            //开始比对
            for (int i = 1; i <= map1.size(); i++) {
                if(map1.containsKey(i) && answerList.get(i) != null){
                    if(map1.get(i).equals(answerList.get(i))) {
                        correctList.add(i);//正确题号添加进队列
                    }
                    else{
                        wrongList.add(i);//错误题号添加进队列
                    }
                }
            }
            //将校验结果写入文件
            new DataStorage().storeCheckInfo(correctList,wrongList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 【重载】将待校验答案文件与本地答案文件进行比对
     * @param myAnswer 待校验答案文件
     * @param ansFile  正确答案文件
     * @author Naren
     */
    private void checkAnswer(File myAnswer, File ansFile) {

        try {
            FileReader fr1 = new FileReader(myAnswer);
            FileReader fr2 = new FileReader(ansFile);
            BufferedReader br1 = new BufferedReader(fr1);
            BufferedReader br2 = new BufferedReader(fr2);

            LinkedHashMap<Integer,String> map1 = new LinkedHashMap<>();//待检测 key:序号，value:答案
            LinkedHashMap<Integer,String> map2 = new LinkedHashMap<>();//正  确 key:序号，value:答案

            //分别按行读出答案
            String content = "";
            while((content = br1.readLine()) != null){
                content = content.replaceAll(" +", "").replaceAll("\uFEFF", "");
                map1.put(Integer.valueOf(content.split("\\.")[0]),content.split("\\.")[1]);
            }
            while((content = br2.readLine()) != null){
                content = content.replaceAll(" +", "").replaceAll("\uFEFF", "");
                map2.put(Integer.valueOf(content.split("\\.")[0]),content.split("\\.")[1]);
            }

            //开始比对
            for (int i = 1; i <= map1.size(); i++) {
                if(map1.containsKey(i) && map2.containsKey(i)){
                    if(map1.get(i).equals(map2.get(i))) {
                        correctList.add(i);//正确题号添加进队列
                    }
                    else{
                        wrongList.add(i);//错误题号添加进队列
                    }
                }
            }
            //将校验结果写入文件
            new DataStorage().storeCheckInfo(correctList,wrongList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
