package com.czAcqt.graphicMenu;

import javax.swing.*;

/**
 * @Class Tips
 * @Description Give users some tips if necessery
 * @Author Naren
 * @Date 2020/4/13 15:53
 * @Version 1.0
 */
public class Tips extends JFrame {

    public void displayTips(String imgName){
        JFrame frm = new JFrame();
        ImageIcon img = new ImageIcon("img/"+ imgName);
        JLabel jlbTip = new JLabel(img);
        frm.setBounds(600,400,img.getIconWidth(), img.getIconHeight());
        frm.setLayout(null);
        jlbTip.setBounds(0,0,img.getIconWidth(),img.getIconHeight());
        jlbTip.setLayout(null);
        this.getLayeredPane().add(jlbTip,new Integer(Integer.MIN_VALUE));
        ((JPanel)this.getContentPane()).setOpaque(false); //设置透明

        frm.add(jlbTip);

        frm.setVisible(true);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
