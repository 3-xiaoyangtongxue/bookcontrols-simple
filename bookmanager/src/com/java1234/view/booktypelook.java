/*
 * Created by JFormDesigner on Sat Apr 20 11:40:01 CST 2024
 */

package com.java1234.view;

import java.awt.event.*;
import javax.swing.border.*;
import com.java1234.dao.bookTypedao;
import com.java1234.model.book;
import com.java1234.model.booktype;
import com.java1234.util.Dbutil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;
import  com.java1234.util.stringutil;
/**
 * @author 86198
 */
public class booktypelook extends JFrame {
    public booktypelook() {
        initComponents();
    }
//图书类别查询事件
    private void chaxun(ActionEvent e) {
        String s_booktypename=this.booktypetxt.getText();
        booktype booktype1=new booktype();
        booktype1.setBookTypeName(s_booktypename);
        this.filltable(booktype1);
    }
//表格行点击事件
    private void booktypetableMousePressed(MouseEvent e) {
        int row=booktypetable.getSelectedRow();
        idtxt.setText((String) booktypetable.getValueAt(row,0));
        booktypenametxt.setText((String) booktypetable.getValueAt(row,1));
        booktypedesctxt.setText((String) booktypetable.getValueAt(row,2));

    }
//表格类别修改
    private void xiugai(ActionEvent e) {
        String id=idtxt.getText();
        String booktypename=booktypenametxt.getText();
        String booktypedesc=booktypedesctxt.getText();
        if(stringutil.isempty(id)){
            JOptionPane.showMessageDialog(null,"请选择要修改的记录");
            return;
        }
        booktype booktype=new booktype(Integer.parseInt(id),booktypename,booktypedesc);
        Connection con=null;
        try{
            con=dbutil.getCon();
            int modifynum=bookTypedao.update(con,booktype);
            if(modifynum==1){
                JOptionPane.showMessageDialog(null,"修改成功");
                this.resetvalue();
                this.filltable(new booktype());
            }else {
                JOptionPane.showMessageDialog(null,"修改失败");
            }
        }catch (Exception e1){
            e1.printStackTrace();
            JOptionPane.showMessageDialog(null,"修改失败");
        }finally {
            try{
                dbutil.closeCon(con);
            }catch (Exception evt){
                evt.printStackTrace();
            }
        }
    }
    //重置表单
    private void resetvalue(){
        this.idtxt.setText("");
        this.booktypenametxt.setText("");
        this.booktypedesctxt.setText("");
    }
//图书类别删除
    private void shanchu(ActionEvent e) {
        String id=idtxt.getText();
        if(stringutil.isempty(id)){
            JOptionPane.showMessageDialog(null,"请选择要修改的记录");
            return;
        }
        int n=JOptionPane.showConfirmDialog(null,"确定要删除该记录吗，该类别下的书籍会全部失去");
        if(n==0){
            Connection con=null;
            try{
                con=dbutil.getCon();
                int deletenum=bookTypedao.delete(con,id);
                if(deletenum==1){
                    JOptionPane.showMessageDialog(null,"删除成功");
                    this.resetvalue();
                    this.filltable(new booktype());

                }else {
                    JOptionPane.showMessageDialog(null,"删除失败");
                }
            }catch (Exception e2){
                e2.printStackTrace();
                JOptionPane.showMessageDialog(null,"删除失败");
            }finally {
                try{
                    dbutil.closeCon(con);
                }catch (Exception evt){
                    evt.printStackTrace();
                }
            }
        }
    }
    private Dbutil dbutil=new Dbutil();
    private com.java1234.dao.bookTypedao bookTypedao=new bookTypedao();
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - 托马斯格兰森
        scrollPane1 = new JScrollPane();
        booktypetable = new JTable();
        label1 = new JLabel();
        booktypetxt = new JTextField();
        chaxun = new JButton();
        panel1 = new JPanel();
        label2 = new JLabel();
        idtxt = new JTextField();
        label3 = new JLabel();
        booktypenametxt = new JTextField();
        label4 = new JLabel();
        scrollPane2 = new JScrollPane();
        booktypedesctxt = new JTextArea();
        xiugai = new JButton();
        shanchu = new JButton();

        //======== this ========
        setTitle("\u56fe\u4e66\u7c7b\u522b\u7ba1\u7406");
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== scrollPane1 ========
        {

            //---- booktypetable ----
            booktypetable.setModel(new DefaultTableModel(
                new Object[][] {
                    {"id", "\u56fe\u4e66\u7c7b\u522b", "\u5907\u6ce8"},
                    {null, null, null},
                },
                new String[] {
                    null, null, null
                }
            ));
            booktypetable.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    booktypetableMousePressed(e);
                }
            });
            scrollPane1.setViewportView(booktypetable);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(40, 85, 461, 183);

        //---- label1 ----
        label1.setText("\u56fe\u4e66\u7c7b\u522b\u540d\u79f0");
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(80, 20), label1.getPreferredSize()));
        contentPane.add(booktypetxt);
        booktypetxt.setBounds(220, 20, 145, booktypetxt.getPreferredSize().height);

        //---- chaxun ----
        chaxun.setText("\u67e5\u8be2");
        chaxun.addActionListener(e -> chaxun(e));
        contentPane.add(chaxun);
        chaxun.setBounds(new Rectangle(new Point(405, 25), chaxun.getPreferredSize()));

        //======== panel1 ========
        {
            panel1.setBorder(new TitledBorder("\u7c7b\u522b\u4fee\u6539\u4e0e\u5220\u9664"));
            panel1.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border.
            EmptyBorder(0,0,0,0), "JFor\u006dDesi\u0067ner \u0045valu\u0061tion",javax.swing.border.TitledBorder.CENTER,javax.swing
            .border.TitledBorder.BOTTOM,new java.awt.Font("Dia\u006cog",java.awt.Font.BOLD,12),
            java.awt.Color.red),panel1. getBorder()));panel1. addPropertyChangeListener(new java.beans.PropertyChangeListener()
            {@Override public void propertyChange(java.beans.PropertyChangeEvent e){if("bord\u0065r".equals(e.getPropertyName()))
            throw new RuntimeException();}});

            //---- label2 ----
            label2.setText("\u7f16\u53f7\uff1a");

            //---- idtxt ----
            idtxt.setEditable(false);

            //---- label3 ----
            label3.setText("\u56fe\u4e66\u7c7b\u522b\u540d\u79f0\uff1a");

            //---- label4 ----
            label4.setText("\u63cf\u8ff0\uff1a");

            //======== scrollPane2 ========
            {
                scrollPane2.setViewportView(booktypedesctxt);
            }

            //---- xiugai ----
            xiugai.setText("\u4fee\u6539");
            xiugai.addActionListener(e -> xiugai(e));

            //---- shanchu ----
            shanchu.setText("\u5220\u9664");
            shanchu.addActionListener(e -> shanchu(e));

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(label2)
                                .addGap(18, 18, 18)
                                .addComponent(idtxt, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(label3)
                                .addGap(18, 18, 18)
                                .addComponent(booktypenametxt, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(label4)
                                .addGap(18, 18, 18)
                                .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 311, GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(28, Short.MAX_VALUE))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(xiugai)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                        .addComponent(shanchu)
                        .addGap(113, 113, 113))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label2)
                            .addComponent(idtxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label3)
                            .addComponent(booktypenametxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label4)
                            .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(xiugai)
                            .addComponent(shanchu))
                        .addGap(22, 22, 22))
            );
        }
        contentPane.add(panel1);
        panel1.setBounds(40, 285, 440, 195);

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
        this.filltable(new booktype());
    }
    //初始化表格
    private  void filltable(booktype booktype){
        DefaultTableModel dtm=(DefaultTableModel) booktypetable.getModel();
        dtm.setRowCount(0);//清空表格
        Connection cno=null;
        try{
            cno=dbutil.getCon();
            ResultSet rs=bookTypedao.list(cno,booktype);
            while(rs.next()){
                Vector v=new Vector();
                v.add(rs.getString("id"));
                v.add(rs.getString("bookname"));
                v.add(rs.getString("bookDesc"));
                dtm.addRow(v);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            {
                try{
                    dbutil.closeCon(cno);
                }catch (Exception evt){
                    evt.printStackTrace();
                }

            }
        }
    }


    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - 托马斯格兰森
    private JScrollPane scrollPane1;
    private JTable booktypetable;
    private JLabel label1;
    private JTextField booktypetxt;
    private JButton chaxun;
    private JPanel panel1;
    private JLabel label2;
    private JTextField idtxt;
    private JLabel label3;
    private JTextField booktypenametxt;
    private JLabel label4;
    private JScrollPane scrollPane2;
    private JTextArea booktypedesctxt;
    private JButton xiugai;
    private JButton shanchu;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
