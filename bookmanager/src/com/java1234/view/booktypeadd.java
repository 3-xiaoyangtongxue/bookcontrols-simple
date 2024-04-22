/*
 * Created by JFormDesigner on Sat Apr 20 11:04:17 CST 2024
 */

package com.java1234.view;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import  java.awt.*;
import java.sql.Connection;

import com.java1234.dao.bookTypedao;
import com.java1234.model.booktype;
import com.java1234.util.Dbutil;
import  com.java1234.util.stringutil;
import  com.java1234.model.booktype;

/**
 * @author 86198
 */
public class booktypeadd extends JFrame {
    public booktypeadd() {
        initComponents();
    }
private Dbutil dbutil=new Dbutil();
    private bookTypedao bookTypedao=new bookTypedao();
    private void reset(ActionEvent e) {
        booktypeinto.setText("");
        beizhu.setText("");
    }

    private void getinto(ActionEvent e) {
        String booktypeName=this.booktypeinto.getText();
        String booktypebeizhu=this.beizhu.getText();
        if(stringutil.isempty(booktypeName)){
            JOptionPane.showMessageDialog(null,"图书类别不能为空");// TODO add your code here
        }
        booktype booktype=new booktype(booktypeName,booktypebeizhu);
        Connection cno=null;
                try{
                    cno=dbutil.getCon();
                    int n=bookTypedao.add(cno,booktype);
//                    System.out.println(n);
                    if(n==1){
                        JOptionPane.showMessageDialog(null,"添加成功");
                        dispose();
                    }else {
                        JOptionPane.showMessageDialog(null,"添加失败");
                    }
                }catch (Exception evt){
                    JOptionPane.showMessageDialog(null,"添加失败");
                }finally {
                    try {
                        dbutil.closeCon(cno);
                    }catch (Exception e1){
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(null,"添加失败");
                    }

                }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - 托马斯格兰森
        label1 = new JLabel();
        booktypeinto = new JTextField();
        label2 = new JLabel();
        getinto = new JButton();
        reset = new JButton();
        scrollPane1 = new JScrollPane();
        beizhu = new JTextArea();

        //======== this ========
        setTitle("\u56fe\u4e66\u7c7b\u522b\u6dfb\u52a0");
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u56fe\u4e66\u7c7b\u522b\uff1a");
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(85, 40), label1.getPreferredSize()));
        contentPane.add(booktypeinto);
        booktypeinto.setBounds(163, 40, 231, booktypeinto.getPreferredSize().height);

        //---- label2 ----
        label2.setText("\u5907\u6ce8\uff1a");
        contentPane.add(label2);
        label2.setBounds(new Rectangle(new Point(101, 126), label2.getPreferredSize()));

        //---- getinto ----
        getinto.setText("\u6dfb\u52a0");
        getinto.addActionListener(e -> getinto(e));
        contentPane.add(getinto);
        getinto.setBounds(new Rectangle(new Point(135, 250), getinto.getPreferredSize()));

        //---- reset ----
        reset.setText("\u91cd\u7f6e");
        reset.addActionListener(e -> {
			reset(e);
			reset(e);
		});
        contentPane.add(reset);
        reset.setBounds(new Rectangle(new Point(333, 250), reset.getPreferredSize()));

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(beizhu);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(170, 130, 235, 75);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - 托马斯格兰森
    private JLabel label1;
    private JTextField booktypeinto;
    private JLabel label2;
    private JButton getinto;
    private JButton reset;
    private JScrollPane scrollPane1;
    private JTextArea beizhu;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

}
