package com.czAcqt;


import com.czAcqt.assistiveTools.CheckThread;
import com.czAcqt.assistiveTools.GenerateThread;

import com.czAcqt.graphicMenu.Graph1;


/***
 * @author chenqiting
 */
public class Main {
    /**
     * @return void
     * @Author chenqiting
     * @Date 2020-04-11 21:00
     * @Description main方法
     * @Param String[]
     */
    public static void main(String[] args) {

        //获取用户指令 控制台程序版本
        /*CommandAnalyze commandAnalyze = new CommandAnalyze();
        commandAnalyze.command();*/


        //用户图型界面获取指令 图形界面版本
        Graph1 graph = new Graph1();
        graph.displayGraph1();
        Runnable runnableGenerImp = new GenerateThread();
        Runnable runnableCheckImp = new CheckThread();
        Thread generThread = new Thread(runnableGenerImp);
        Thread checkThread = new Thread(runnableCheckImp);
        generThread.start();
        checkThread.start();

    }
}
