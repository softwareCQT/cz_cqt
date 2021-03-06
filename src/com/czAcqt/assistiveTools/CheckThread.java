package com.czAcqt.assistiveTools;

import com.czAcqt.checkingTools.AnswerChecking;
import com.czAcqt.graphicMenu.Graph1;
import com.czAcqt.graphicMenu.Graph3;

/**
 * @Class CheckThread
 * @Description 批改作业线程 图形用户界面需要
 * @Author Naren
 * @Date 2020/4/13 18:29
 * @Version 1.0
 */
public class CheckThread implements Runnable {
    @Override
    public void run() {

        String expFileName = null;
        String ansFileName = null;
        while(true){
            //阻塞循环获取参数
            while(expFileName == null || ansFileName == null){
                try {
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                expFileName = new Graph3().expFileName;
                ansFileName = new Graph3().ansFileName;
            }
            AnswerChecking ac = new AnswerChecking();
            ac.checkFile(expFileName, ansFileName);
            expFileName = null;
            ansFileName = null;
        }

    }
}
