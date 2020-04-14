package com.czAcqt.assistiveTools;

import com.czAcqt.generate.Calculate;
import com.czAcqt.generate.Expression;
import com.czAcqt.graphicMenu.Graph2;
import com.czAcqt.graphicMenu.Tips;

/**
 * @Class GenerateThread
 * @Description 生成题目线程 图形用户界面需要
 * @Author Naren
 * @Date 2020/4/13 18:28
 * @Version 1.0
 */
public class GenerateThread implements Runnable {
    @Override
    public void run() {
//        while(true){
            //生成
            int[] arrays = new int[2];
            String fileId = null;
            //阻塞，直到获取到参数
            while((arrays == null || fileId == null)){
                arrays = new Graph2().arrays;
                fileId = new Graph2().fileId;
            }
            Expression expression = new Expression(arrays[0],arrays[1],new Calculate());
            //生成表达式，单线程生成，会阻塞
            expression.generateAllExpression();
            //将生成的表达式和答案写入对应文件
            DataStorage dataStorage = new DataStorage();
            dataStorage.storeExp(expression.getExpressionList());
            dataStorage.storeAns(expression.getAnswerList());
            Tips tips = new Tips();
            tips.displayTips("myTip.png");
        }

//    }

}
