package com.czAcqt.checkingTools;

import com.czAcqt.generate.Calculate;
import com.czAcqt.generate.Expression;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @version 1.0
 * @class: AnswerChecking
 * @description: 校验答案
 * @author: Naren
 * @date: 2020/4/11 11:33
 */
public class AnswerChecking {

    /**
     * @function:检测文件状态是否正常
     * @param answerFile
     * @param exersicesFile
     */
    public void checkFile(String answerFile, String exersicesFile) {
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
            String id = exersicesFile.substring(9,11);
            String myAnswerFile = "Answers" + id + ".txt";
            File myAnswer = new File(myAnswerFile);
            //若系统中不存在与题目文件相符合的答案文件
            if(!myAnswer.exists()){
                FileReader fr = null;
                try {
                    fr = new FileReader(expFile);
                    BufferedReader br = new BufferedReader(fr);
                    String content = null;
                    ArrayList<String> questionList = new ArrayList<>();
                    while((content = br.readLine()) != null){//(?m)^.*$
                        content = content.split("//.")[1];
                        questionList.add(content);
                    }
                    //TODO 调用起廷方法
                    Expression ex = new Expression(new Calculate());
                    ex.getCorrectAnswerList(questionList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                //调用本类检验方法
                checkAnswer(myAnswer,ansFile);
            }//EndOf else
    }

    /**
     * @function 校验答案文件
     * @param myAnswer
     * @param ansFile
     */
    private void checkAnswer(File myAnswer, File ansFile) {
        FileReader fr1 = null;//正确答案文件
        FileReader fr2 = null;//待检测答案文件
        try {
            fr1 = new FileReader(myAnswer);
            fr2 = new FileReader(ansFile);
            BufferedReader br1 = new BufferedReader(fr1);
            BufferedReader br2 = new BufferedReader(fr2);

            LinkedHashMap<Integer,String> map1 = new LinkedHashMap<>();
            LinkedHashMap<Integer,String> map2 = new LinkedHashMap<>();

            String content = "";
            while((content = br1.readLine()) != null){
                content = content.replaceAll(" +", "").replaceAll("\uFEFF", "");
                //map1正确答案：key:序号，value:答案
                map1.put(Integer.valueOf(content.split("//.")[0]),content.split("//.")[1]);
            }
            while((content = br2.readLine()) != null){
                content = content.replaceAll(" +", "").replaceAll("\uFEFF", "");
                //map2待校验答案：key:序号，value:答案
                map2.put(Integer.valueOf(content.split("//.")[0]),content.split("//.")[1]);
            }

            //保存正确/错误题号的队列
            List<Integer> correctList = new ArrayList<>();
            List<Integer> wrongtList = new ArrayList<>();
            //开始比对
            for (int i = 1; i < map1.size(); i++) {
                if(map1.containsKey(i) && map2.containsKey(i)){
                    if(map1.get(i).equals(map2.get(i))) {
                        correctList.add(i);//正确题号添加进队列
                    }
                    else{
                        wrongtList.add(i);//错误题号添加进队列
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("请检测文件名或路径。");
        }
    }
}
