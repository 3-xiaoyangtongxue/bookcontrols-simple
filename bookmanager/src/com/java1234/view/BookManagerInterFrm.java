/*
 * Created by JFormDesigner on Sat Apr 20 15:46:43 CST 2024
 */

package com.java1234.view;

import com.java1234.dao.bookTypedao;
import com.java1234.dao.bookdao;
import com.java1234.model.book;
import com.java1234.model.booktype;
import com.java1234.util.Dbutil;
import com.java1234.util.stringutil;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 * @author 86198
 */
public class BookManagerInterFrm extends JFrame {
    public BookManagerInterFrm() {
        initComponents();
    }
//图书查询事件处理
    private void button1(ActionEvent e) {
        String bookname=s_booknametxt.getText();
        String author=s_authornametxt.getText();
        String x=(String) s_booktypejcb.getSelectedItem();
        Dbutil dbutil=new Dbutil();
        Connection con1= null;
        try {
            con1 = dbutil.getCon();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
//        booktype booktype=(booktype) booktypeicb.getSelectedItem();
        booktype booktype=new booktype(x);
        bookTypedao bookTypedao=new bookTypedao();
        ResultSet rs=null;
        int booktypeid=0;
        try {
            rs=bookTypedao.list(con1,booktype);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            while(rs.next()) {
                booktypeid=rs.getInt("id");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        try {
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        try {
            dbutil.closeCon(con1);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
//        booktype booktype=(com.java1234.model.booktype) s_booktypejcb.getSelectedItem();
        booktypeid=booktype.getId();
        book book =new book(bookname,author,booktypeid);
        this.filltable(book);// TODO add your code here
    }
//表格点击事件处理
    private void booktabeMousePressed(MouseEvent e) {
//        fillbooktype("modify");
        int row=this.booktabe.getSelectedRow();
        this.idtxt.setText((String) booktabe.getValueAt(row,0));
        this.booknametxt.setText((String) booktabe.getValueAt(row,1));
        this.authortxt.setText((String) booktabe.getValueAt(row,2));
        String sex=(String) booktabe.getValueAt(row,3);
        if("男".equals(sex)){
            this.manjrb.setSelected(true);this.femalejrb.setSelected(false);// TODO add your code here
        }else if("女".equals(sex)){
            this.femalejrb.setSelected(true);this.manjrb.setSelected(false);
        }
        this.pricetxt.setText((Float)booktabe.getValueAt(row,4)+"");
        this.bookdesctxt.setText((String) booktabe.getValueAt(row,5));
        String booktypename=(String) this.booktabe.getValueAt(row,6);
        int n=this.booktypeicb.getItemCount();
        for(int i=0;i<n;i++){
//            booktype item=(booktype) this.booktypeicb.getItemAt(i);
            String item= (String) this.booktypeicb.getItemAt(i);
//            if(item.getBookTypeName().equals(booktypename)){
//                this.booktypeicb.setSelectedIndex(i);
//            }
            if(item.equals(booktypename)){
                this.booktypeicb.setSelectedIndex(i);
            }
        }
//        fillbooktype("modify");
    }
//修改事件
    private void button3(ActionEvent e) {
        String id=this.idtxt.getText();
        if(stringutil.isempty(id)){
            JOptionPane.showMessageDialog(null,"请选择要修改的功能");
            return;// TODO add your code here
        }
        String bookName=booknametxt.getText();
        String author=authortxt.getText();
        String price=pricetxt.getText();
        String bookdesc=bookdesctxt.getText();
        if(stringutil.isempty(bookName)){
            JOptionPane.showMessageDialog(null,"图书名称不能为空");
            return;// TODO add your code here
        }// TODO add your code here
        if(stringutil.isempty(author)){
            JOptionPane.showMessageDialog(null,"作者不能为空");
            return;// TODO add your code here
        }
        if(stringutil.isempty(price)){
            JOptionPane.showMessageDialog(null,"价格不能为空");
            return;// TODO add your code here
        }
        String sex="";
        if(manjrb.isSelected()){
            sex="男";
        }else{
            sex="女";
        }
        String x=(String) booktypeicb.getSelectedItem();
        Dbutil dbutil=new Dbutil();
        Connection con1= null;
        try {
            con1 = dbutil.getCon();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

//        booktype booktype=(booktype) booktypeicb.getSelectedItem();
        booktype booktype=new booktype(x);
        bookTypedao bookTypedao=new bookTypedao();
        ResultSet rs=null;
        int booktypeid=0;
        try {
            rs=bookTypedao.list(con1,booktype);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
            try {
                while(rs.next()) {
                     booktypeid=rs.getInt("id");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        try {
            dbutil.closeCon(con1);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

//        int booktypeid=booktype.getId();
        book book=new book(Integer.parseInt(id),bookName,author,sex,Float.parseFloat(price),booktypeid,bookdesc);
        Connection con=null;
        try{
            con=dbutil.getCon();
            int addnum= bookdao.update(con,book);
            if(addnum==1){
                JOptionPane.showMessageDialog(null,"图书修改成功");
                resetvalue();
                this.filltable(new book());
            }else{
                JOptionPane.showMessageDialog(null,"图书修改失败");
            }
        }catch (Exception e1){
            JOptionPane.showMessageDialog(null,"图书修改失败");
            e1.printStackTrace();
        }finally {
            try {
                dbutil.closeCon(con);
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
    }
    private void resetvalue(){
        idtxt.setText("");
        booknametxt.setText("");
        authortxt.setText("");
        pricetxt.setText("");
        manjrb.setSelected(true);
        femalejrb.setSelected(false);
        bookdesctxt.setText("");
        if(booktypeicb.getItemCount()>0){
            booktypeicb.setSelectedIndex(0);
        }
    }
//图书删除事件
    private void button2(ActionEvent e) {
        String id=idtxt.getText();
        if(stringutil.isempty(id)){
            JOptionPane.showMessageDialog(null,"请选择要修改的记录");
            return;
        }
        int n=JOptionPane.showConfirmDialog(null,"确定要删除该记录吗");
        if(n==0){
            Connection con=null;
            try{
                con=dbutil.getCon();
                int deletenum=bookdao.delete(con,id);
                if(deletenum==1){
                    JOptionPane.showMessageDialog(null,"删除成功");
                    this.resetvalue();
                    this.filltable(new book());
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
        }// TODO add your code here
    }
    private Dbutil dbutil=new Dbutil();
    private com.java1234.dao.bookTypedao bookTypedao=new bookTypedao();
    private com.java1234.dao.bookdao bookdao=new bookdao();
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        scrollPane1 = new JScrollPane();
        booktabe = new JTable();
        panel1 = new JPanel();
        label1 = new JLabel();
        s_booknametxt = new JTextField();
        button1 = new JButton();
        label2 = new JLabel();
        s_authornametxt = new JTextField();
        label3 = new JLabel();
        s_booktypejcb = new JComboBox();
        panel2 = new JPanel();
        label4 = new JLabel();
        label5 = new JLabel();
        booknametxt = new JTextField();
        label6 = new JLabel();
        manjrb = new JRadioButton();
        femalejrb = new JRadioButton();
        label7 = new JLabel();
        pricetxt = new JTextField();
        label8 = new JLabel();
        authortxt = new JTextField();
        label9 = new JLabel();
        booktypeicb = new JComboBox();
        label10 = new JLabel();
        scrollPane2 = new JScrollPane();
        bookdesctxt = new JTextArea();
        button2 = new JButton();
        button3 = new JButton();
        idtxt = new JTextField();

        //======== this ========
        setTitle("\u56fe\u4e66\u7ba1\u7406");
        var contentPane = getContentPane();

        //======== scrollPane1 ========
        {

            //---- booktabe ----
            booktabe.setModel(new DefaultTableModel(
                new Object[][] {
                    {"\u7f16\u53f7", "\u56fe\u4e66\u540d\u79f0", "\u56fe\u4e66\u4f5c\u8005", "\u4f5c\u8005\u6027\u522b", "\u56fe\u4e66\u4ef7\u683c", "\u56fe\u4e66\u63cf\u8ff0", "\u56fe\u4e66\u7c7b\u522b"},
                    {null, null, null, null, null, null, null},
                },
                new String[] {
                    null, null, null, null, null, null, null
                }
            ));
            booktabe.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    booktabeMousePressed(e);
                }
            });
            scrollPane1.setViewportView(booktabe);
        }

        //======== panel1 ========
        {
            panel1.setBorder(new TitledBorder("\u641c\u7d22\u6761\u4ef6"));

            //---- label1 ----
            label1.setText("\u56fe\u4e66\u540d\u79f0\uff1a");

            //---- button1 ----
            button1.setText("\u641c\u7d22");
            button1.addActionListener(e -> button1(e));

            //---- label2 ----
            label2.setText("\u56fe\u4e66\u4f5c\u8005\uff1a");

            //---- label3 ----
            label3.setText("\u56fe\u4e66\u7c7b\u522b\uff1a");

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(label1)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(s_booknametxt, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(label2)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(s_authornametxt, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(label3)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(s_booktypejcb, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(button1)
                        .addContainerGap(7, Short.MAX_VALUE))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label1)
                            .addComponent(button1)
                            .addComponent(s_booknametxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label2)
                            .addComponent(s_authornametxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label3)
                            .addComponent(s_booktypejcb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(48, Short.MAX_VALUE))
            );
        }

        //======== panel2 ========
        {
            panel2.setBorder(new TitledBorder("\u4fee\u6539\u4e0e\u5220\u9664"));

            //---- label4 ----
            label4.setText("\u7f16\u53f7\uff1a");

            //---- label5 ----
            label5.setText("\u56fe\u4e66\u540d\u79f0\uff1a");

            //---- label6 ----
            label6.setText("\u4f5c\u8005\u6027\u522b\uff1a");

            //---- manjrb ----
            manjrb.setText("\u7537");

            //---- femalejrb ----
            femalejrb.setText("\u5973");

            //---- label7 ----
            label7.setText("\u4ef7\u683c\uff1a");

            //---- label8 ----
            label8.setText("\u56fe\u4e66\u4f5c\u8005\uff1a");

            //---- label9 ----
            label9.setText("\u56fe\u4e66\u7c7b\u522b\uff1a");

            //---- label10 ----
            label10.setText("\u56fe\u4e66\u63cf\u8ff0\uff1a");

            //======== scrollPane2 ========
            {
                scrollPane2.setViewportView(bookdesctxt);
            }

            //---- button2 ----
            button2.setText("\u5220\u9664");
            button2.addActionListener(e -> button2(e));

            //---- button3 ----
            button3.setText("\u4fee\u6539");
            button3.addActionListener(e -> button3(e));

            //---- idtxt ----
            idtxt.setEditable(false);

            GroupLayout panel2Layout = new GroupLayout(panel2);
            panel2.setLayout(panel2Layout);
            panel2Layout.setHorizontalGroup(
                panel2Layout.createParallelGroup()
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(panel2Layout.createParallelGroup()
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addGroup(panel2Layout.createSequentialGroup()
                                        .addComponent(label7)
                                        .addGap(18, 18, 18)
                                        .addComponent(pricetxt, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panel2Layout.createSequentialGroup()
                                        .addComponent(label4)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(idtxt)))
                                .addGap(35, 35, 35)
                                .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addGroup(panel2Layout.createSequentialGroup()
                                        .addComponent(label5)
                                        .addGap(18, 18, 18)
                                        .addComponent(booknametxt, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panel2Layout.createSequentialGroup()
                                        .addComponent(label8)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(authortxt, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(panel2Layout.createParallelGroup()
                                    .addGroup(panel2Layout.createSequentialGroup()
                                        .addComponent(label6)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(manjrb)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(femalejrb))
                                    .addGroup(panel2Layout.createSequentialGroup()
                                        .addComponent(label9)
                                        .addGap(18, 18, 18)
                                        .addComponent(booktypeicb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(61, Short.MAX_VALUE))
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addComponent(label10)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 473, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(83, Short.MAX_VALUE))))
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(button2)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(button3)
                        .addGap(260, 260, 260))
            );
            panel2Layout.setVerticalGroup(
                panel2Layout.createParallelGroup()
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label4)
                            .addComponent(label5)
                            .addComponent(booknametxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label6)
                            .addComponent(manjrb)
                            .addComponent(femalejrb)
                            .addComponent(idtxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label7)
                            .addComponent(pricetxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label8)
                            .addComponent(authortxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label9)
                            .addComponent(booktypeicb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGroup(panel2Layout.createParallelGroup()
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(label10))
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(button2)
                            .addComponent(button3))
                        .addContainerGap(17, Short.MAX_VALUE))
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(panel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(16, 16, 16)
                            .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE)))
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(24, 24, 24)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(panel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(35, Short.MAX_VALUE))
        );
        fillbooktype("search");
        fillbooktype("modify");
        filltable(new book());
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JScrollPane scrollPane1;
    private JTable booktabe;
    private JPanel panel1;
    private JLabel label1;
    private JTextField s_booknametxt;
    private JButton button1;
    private JLabel label2;
    private JTextField s_authornametxt;
    private JLabel label3;
    private JComboBox s_booktypejcb;
    private JPanel panel2;
    private JLabel label4;
    private JLabel label5;
    private JTextField booknametxt;
    private JLabel label6;
    private JRadioButton manjrb;
    private JRadioButton femalejrb;
    private JLabel label7;
    private JTextField pricetxt;
    private JLabel label8;
    private JTextField authortxt;
    private JLabel label9;
    private JComboBox booktypeicb;
    private JLabel label10;
    private JScrollPane scrollPane2;
    private JTextArea bookdesctxt;
    private JButton button2;
    private JButton button3;
    private JTextField idtxt;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
    //初始化下拉框
    private  void fillbooktype(String type){
        Connection con=null;
        booktype booktype=null;
        try{
            con=dbutil.getCon();
            ResultSet rs=bookTypedao.list(con,new booktype());
            if("search".equals(type)){
                booktype=new booktype();
                booktype.setBookTypeName("请选择...");
                booktype.setId(-1);
                this.s_booktypejcb.addItem(booktype.tostring());
            }
            while(rs.next()){
                booktype=new booktype();
                booktype.setBookTypeName(rs.getString("bookName"));
                booktype.setId(rs.getInt("id"));
                if("search".equals(type)){
                    this.s_booktypejcb.addItem(booktype.tostring());
                }else if("modify".equals(type)){
                    this.booktypeicb.addItem(booktype.tostring());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                dbutil.closeCon(con);
            }catch (Exception e1){
                e1.printStackTrace();
            }
        }
    }
    //初始化表格数据
    private  void filltable(book book){
        DefaultTableModel dtm=(DefaultTableModel) booktabe.getModel();
        dtm.setRowCount(0);//清空表格
        Connection cno=null;
        try{
            cno=dbutil.getCon();
            ResultSet rs=bookdao.list(cno,book);

//            JOptionPane.showMessageDialog(null,rs.getString("bookName"));
            while(rs.next()){
                Vector v=new Vector();
                v.add(rs.getString("id"));
                v.add(rs.getString("bookName"));
                v.add(rs.getString("auther"));
                v.add(rs.getString("sex"));
                v.add(rs.getFloat("price"));
                v.add(rs.getString("bookdesc"));
                v.add(rs.getString("bt.bookname"));
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

    public static void main(String[] args) {
        BookManagerInterFrm bookManagerInterFrm=new BookManagerInterFrm();
        bookManagerInterFrm.setVisible(true);
    }
}
