package com.czAcqt.graphicMenu;

import com.czAcqt.assistiveTools.DataStorage;
import com.czAcqt.generate.Calculate;
import com.czAcqt.generate.Expression;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Class Graph2
 * @Description 生成题目
 * @Author Naren
 * @Date 2020/4/13 11:23
 * @Version 1.0
 */
public class Graph2 extends JFrame{


    public static int[] arrays;
    public static String fileId;

    private static JFrame frm2 = new JFrame("四则运算[生成-校验]器");
    private static JButton btn1 = new JButton("生成");
    private static JButton btn2 = new JButton("返回");
    private static JTextField jtfNum = new JTextField(40);
    private static JTextField jtfRange = new JTextField(40);
    private static JTextField jtfId = new JTextField(40);
    private static JLabel jlbNum = new JLabel("题目数量");
    private static JLabel jlbRange = new JLabel("数值范围[2~10]");
    private static JLabel jlbId = new JLabel("3位数字版号");
    ImageIcon img = new ImageIcon("img/logo.jpg");
    JLabel jll = new JLabel(img);

    public Graph2(){
        jll.setLayout(null);
        frm2.setBounds(400,300,img.getIconWidth(), img.getIconHeight());
        jll.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
        this.getLayeredPane().add(jll,new Integer(Integer.MIN_VALUE));
        ((JPanel)this.getContentPane()).setOpaque(false); //设置透明
        frm2.setLayout(null);
    }

    /**
     * 展示界面
     * @author Naren
     */
    public void displayGraph2(){

        jtfNum.setBounds(50,50,80,30);
        jtfRange.setBounds(50,100,80,30);
        jlbNum.setBounds(150,50,90,30);
        jlbRange.setBounds(150,100,90,30);
        jtfId.setBounds(50,150,80,30);
        jlbId.setBounds(150,150,90,30);
        btn1.setBounds(50,220,180,40);
        btn2.setBounds(50,280,180,40);

        frm2.add(jtfNum);
        frm2.add(jtfRange);
        frm2.add(jlbNum);
        frm2.add(jlbRange);
        frm2.add(jtfId);
        frm2.add(jlbId);
        frm2.add(jll);
        frm2.add(btn1);
        frm2.add(btn2);

        frm2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm2.setVisible(true);

        //循环获取非空参数

        int range = 0;
//        while(jtfNum == null || jtfRange == null){
            btn1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //获取题目数量
                    jtfNum.getText();
                    //获取数值范围
                    jtfRange.getText();
                    arrays = new int[]{Integer.parseInt(jtfNum.getText()),Integer.parseInt(jtfRange.getText())};

                    //获取文件版号
                    fileId = jtfId.getText();
                }
            });
//        }

        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frm2.dispose();
                Graph1 graph1 = new Graph1();
                graph1.displayGraph1();
            }
        });
    }
}

