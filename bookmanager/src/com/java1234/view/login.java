/*
 * Created by JFormDesigner on Sat Apr 20 09:45:15 CST 2024
 */

package com.java1234.view;

import com.java1234.dao.Userdao;
import com.java1234.model.User;
import com.java1234.util.Dbutil;
import com.java1234.util.stringutil;


import javax.swing.*;
import javax.swing.GroupLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

/**
 * @author 86198
 */
public class login extends JFrame {
    public login() {
        initComponents();
    }
    private Dbutil dbutil=new Dbutil();
    private Userdao userdao=new Userdao();
    private JLabel label1= new JLabel();
    private JTextField textField1= new JTextField();
    private JLabel label2= new JLabel();
    private JTextField textField2= new JTextField();
    private JButton button1= new JButton();
    private JButton button2= new JButton();;
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - 托马斯格兰森
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText("");
                textField2.setText("");
                aboutus aboutus=new aboutus();
                aboutus.setVisible(true);
                dispose();
            }
        });
//登录事件
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username=textField1.getText();
                String pwd=textField2.getText();

                if(stringutil.isempty(username)){
                    JOptionPane.showMessageDialog(null,"用户名不能为空");
                    return;// TODO add your code here
                }
                if(stringutil.isempty(pwd)){
                    JOptionPane.showMessageDialog(null,"密码不能为空");
                    return;// TODO add your code here
                }
                User user=new User(username,pwd);
                Connection con=null;
                try {
                    con=dbutil.getCon();
                    User user1=userdao.login(con,user);
                    if(user1!=null){
                        JOptionPane.showMessageDialog(null,"登陆成功");
                        Main m=new Main();
                        m.setVisible(true);
                        dispose();
                    }else{
                        JOptionPane.showMessageDialog(null,"用户名或者密码错误");
                    }

                }catch (Exception w){
                    w.printStackTrace();
                }finally {
                    try{
                        dbutil.closeCon(con);
                    }catch (Exception evt){
                        evt.printStackTrace();
                    }
                }
            }
        });
        //======== this ========
        setTitle("\u56fe\u4e66\u7ba1\u7406\u7cfb\u7edf");
        var contentPane = getContentPane();

        //---- label1 ----
        label1.setText("\u7528\u6237\u540d\uff1a");

        //---- label2 ----
        label2.setText("    \u5bc6\u7801\uff1a");

        //---- button1 ----
        button1.setText("\u767b\u5f55");

        //---- button2 ----
        button2.setText("\u91cd\u7f6e");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(61, 61, 61)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(label2)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(textField2))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(label1)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(textField1, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE))))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(93, 93, 93)
                            .addComponent(button1)
                            .addGap(68, 68, 68)
                            .addComponent(button2)))
                    .addContainerGap(43, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(53, 53, 53)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label1)
                        .addComponent(textField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(33, 33, 33)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label2)
                        .addComponent(textField2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(46, 46, 46)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(button1)
                        .addComponent(button2))
                    .addContainerGap(47, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    public static void main(String[] args) {
        login l=new login();
        l.setVisible(true);
    }
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - 托马斯格兰森

    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
