package com.czAcqt.graphicMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * @Class Graph
 * @Description show graphic menu to users
 * @Author Naren
 * @Date 2020/4/12 10:28
 * @Version 1.0
 */
public class Graph1 extends JFrame{

    private static JFrame frm1 = new JFrame("四则运算[生成-校验]器");
    private static Container c = frm1.getContentPane();
    private static JButton btnGener = new JButton("生成题目");
    private static JButton btnCheck = new JButton("校验答案");

    ImageIcon img = new ImageIcon("img/logo.jpg");
    public void displayGraph1(){


        JLabel jll = new JLabel(img);
        frm1.setBounds(400,300,img.getIconWidth(), img.getIconHeight());
        jll.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
        this.getLayeredPane().add(jll,new Integer(Integer.MIN_VALUE));
        ((JPanel)this.getContentPane()).setOpaque(false); //设置透明
        frm1.setLayout(null);

        btnGener.setBounds(80,180,120,40);
        btnCheck.setBounds(80,240,120,40);

        frm1.add(jll);
        frm1.add(btnGener);
        frm1.add(btnCheck);
        frm1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm1.setVisible(true);
        btnGener.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frm1.dispose();
                Graph2 graph2 = new Graph2();
                graph2.displayGraph2();
            }
        });
        btnCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frm1.dispose();
                Graph3 graph3 = new Graph3();
                graph3.displayGraph3();
            }
        });

    }

}
