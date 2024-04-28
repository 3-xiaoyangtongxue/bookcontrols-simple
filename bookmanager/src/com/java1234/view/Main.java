/*
 * Created by JFormDesigner on Sat Apr 20 10:15:44 CST 2024
 */

package com.java1234.view;

import javax.swing.*;
import javax.swing.GroupLayout;
import java.awt.event.ActionEvent;
import  java.awt.*;
import java.awt.event.ActionListener;

/**
 * @author 86198
 */
public class Main extends JFrame {
    public Main() {
        initComponents();
    }

    private void all3(ActionEvent e) {
       aboutus aboutus=new aboutus();
       aboutus.setVisible(true);// TODO add your code here

    }

    private void all11(ActionEvent e) {
        booktypeadd booktypeadd=new booktypeadd();
     booktypeadd.setVisible(true);

    }

    private void all12(ActionEvent e) {
       booktypelook booktypelook=new booktypelook();
       booktypelook.setVisible(true);// TODO add your code here
    }

    private void all21(ActionEvent e) {
        bookadd bookadd=new bookadd();
        bookadd.setVisible(true);
    }

    private void all22(ActionEvent e) {
        BookManagerInterFrm bookManagerInterFrm=new BookManagerInterFrm();
        bookManagerInterFrm.setVisible(true);
    }

    private void all2(ActionEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        menuBar1 = new JMenuBar();
        all = new JMenu();
        all1 = new JMenu();
        all11 = new JMenuItem();
        all12 = new JMenuItem();
        all2 = new JMenu();
        all21 = new JMenuItem();
        all22 = new JMenuItem();
        all3 = new JMenuItem();

        //======== this ========
        var contentPane = getContentPane();

        //======== menuBar1 ========
        {

            //======== all ========
            {
                all.setText("\u57fa\u672c\u6570\u636e\u7ef4\u62a4");

                //======== all1 ========
                {
                    all1.setText("\u56fe\u4e66\u7c7b\u522b\u7ba1\u7406");

                    //---- all11 ----
                    all11.setText("\u56fe\u4e66\u7c7b\u522b\u6dfb\u52a0");
                    all11.addActionListener(e -> all11(e));
                    all1.add(all11);

                    //---- all12 ----
                    all12.setText("\u56fe\u4e66\u7c7b\u522b\u7ef4\u62a4");
                    all12.addActionListener(e -> all12(e));
                    all1.add(all12);
                }
                all.add(all1);

                //======== all2 ========
                {
                    all2.setText("\u56fe\u4e66\u7ba1\u7406");

                    //---- all21 ----
                    all21.setText("\u56fe\u4e66\u6dfb\u52a0");
                    all21.addActionListener(e -> all21(e));
                    all2.add(all21);

                    //---- all22 ----
                    all22.setText("\u56fe\u4e66\u7ef4\u62a4");
                    all22.addActionListener(e -> all22(e));
                    all2.add(all22);
                }
                all.add(all2);
            }
            menuBar1.add(all);

            //---- all3 ----
            all3.setText("\u5173\u4e8e\u6211\u4eec");
            all3.addActionListener(e -> all3(e));
            menuBar1.add(all3);
        }
        setJMenuBar(menuBar1);

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGap(0, 578, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGap(0, 442, Short.MAX_VALUE)
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JMenuBar menuBar1;
    private JMenu all;
    private JMenu all1;
    private JMenuItem all11;
    private JMenuItem all12;
    private JMenu all2;
    private JMenuItem all21;
    private JMenuItem all22;
    private JMenuItem all3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
    public static void main(String[] args) {
        Main m=new Main();
        m.setVisible(true);
    }
}
