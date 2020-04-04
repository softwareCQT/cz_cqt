package com.czAcqt.Assistive_tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * 数据存储类V1.0
 */
public class DataStorage {

    /**
     * 将表达式和答案写入本地文件
     * @param expMap
     * @throws IOException
     */
    public void printExp(Map<String,String> expMap) throws IOException {

        //expSet存储表达式
        Set<String> expSet = expMap.keySet();

        //存储表达式的文件
        File expFile = new File("Exercises.txt");
        //存储答案的文件
        File ansFile = new File("Answers.txt");
        if(!expFile.exists())
            expFile.createNewFile();
        if(!ansFile.exists())
            ansFile.createNewFile();

        BufferedWriter bw1 = new BufferedWriter(new FileWriter(expFile,true));
        BufferedWriter bw2 = new BufferedWriter(new FileWriter(ansFile,true));

        //循环将表达式和答案分别写入两个文件
        for(String key: expSet) {
            //TODO 待添加：存储的同时表明序号
            bw1.write(key);
            bw1.write("\n");
            bw2.write(expMap.get(key));
            bw2.write("\n");
        }

        if(bw1 != null){
            bw1.flush();
            bw1.close();
        }
        if(bw2 != null){
            bw2.flush();
            bw2.close();
        }
        System.out.println("文件已生成，请进入对应目录进行查阅。");
    }

}
