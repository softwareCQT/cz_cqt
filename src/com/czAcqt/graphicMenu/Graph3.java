package com.czAcqt.graphicMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Class Graph3
 * @Description Check the answer
 * @Author Naren
 * @Date 2020/4/13 16:29
 * @Version 1.0
 */
public class Graph3 extends JFrame{

    public static String expFileName;
    public static String ansFileName;

    private static JFrame frm2 = new JFrame("四则运算[生成-校验]器");
    private static JButton btn1 = new JButton("批改");
    private static JButton btn2 = new JButton("返回");
    private static JTextField jtfExp = new JTextField(40);
    private static JTextField jtfAns = new JTextField(40);
    private static JLabel jlbExp = new JLabel("题目文件名");
    private static JLabel jlbAns = new JLabel("待批改答案");
    ImageIcon img = new ImageIcon("img/logo.jpg");
    JLabel jll = new JLabel(img);

    public Graph3(){
        jll.setLayout(null);
        frm2.setBounds(400,300,img.getIconWidth(), img.getIconHeight());
        jll.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
        this.getLayeredPane().add(jll,new Integer(Integer.MIN_VALUE));
        ((JPanel)this.getContentPane()).setOpaque(false); //设置透明
        frm2.setLayout(null);
    }

    public void displayGraph3(){

        jtfExp.setBounds(50,50,80,30);
        jtfAns.setBounds(50,100,80,30);
        jlbExp.setBounds(150,50,90,30);
        jlbAns.setBounds(150,100,90,30);
        btn1.setBounds(50,220,180,40);
        btn2.setBounds(50,280,180,40);

        frm2.add(jtfExp);
        frm2.add(jtfAns);
        frm2.add(jlbExp);
        frm2.add(jlbAns);
        frm2.add(jll);
        frm2.add(btn1);
        frm2.add(btn2);

        frm2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm2.setVisible(true);

        //点击获取参数
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取题目文件名
                expFileName = jtfExp.getText();
                //获取答案文件名
                ansFileName = jtfAns.getText();
            }
        });
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
