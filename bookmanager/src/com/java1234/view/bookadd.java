/*
 * Created by JFormDesigner on Sat Apr 20 13:30:30 CST 2024
 */

package com.java1234.view;

import java.awt.*;
import java.awt.event.*;
import com.java1234.model.booktype;
import com.java1234.util.Dbutil;
import com.java1234.dao.bookTypedao;
import com.java1234.dao.bookdao;
import com.java1234.util.stringutil;
import com.java1234.model.book;
import javax.swing.*;
import javax.swing.GroupLayout;
import java.awt.print.Book;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 * @author 86198
 */
public class bookadd extends JFrame {
    public bookadd() {
        initComponents();
    }
//图书添加事件处理
    private void into(ActionEvent e) {
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
        if(menjrb.isSelected()){
            sex="男";
        }else{
            sex="女";
        }
        String x=(String) booktypejcb.getSelectedItem();
        Dbutil dbutil=new Dbutil();
        Connection con1= null;

//        booktype booktype=(booktype) booktypeicb.getSelectedItem();
        booktype booktype=new booktype(x);
//        bookTypedao bookTypedao=new bookTypedao();
//        ResultSet rs=null;
         int booktypeid=0;Vector v=new Vector();
        Connection cno=null;
        try{
            cno=dbutil.getCon();
            ResultSet rs=bookTypedao.list(cno,booktype);
            while(rs.next()){

                v.add(rs.getString("id"));
//                v.add(rs.getString("bookname"));
//                v.add(rs.getString("bookDesc"));

            }
            booktypeid=Integer.parseInt((String) v.get(0));
        }catch (Exception e1){
            e1.printStackTrace();
        }

        //booktype booktype=(booktype) booktypejcb.getSelectedItem();
//         booktypeid=Integer.parseInt(m);
        book book=new book(bookName,author,sex,Float.parseFloat(price),booktypeid,bookdesc);

        Connection con=null;
        try{
            con=dbutil.getCon();
           int addnum= bookdao.add(con,book);
            if(addnum==1){
                JOptionPane.showMessageDialog(null,"图书添加成功");
                resetvalue();
            }else{
                JOptionPane.showMessageDialog(null,"图书添加失败");
            }
        }catch (Exception e1){
            JOptionPane.showMessageDialog(null,"图书添加失败");
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
        booknametxt.setText("");
        authortxt.setText("");
        pricetxt.setText("");
        menjrb.setSelected(true);
        womanjrb.setSelected(false);
        bookdesctxt.setText("");
        if(booktypejcb.getItemCount()>0){
            booktypejcb.setSelectedIndex(0);
        }
    }
//重置事件处理
    private void reset(ActionEvent e) {
        resetvalue();
    }

    private Dbutil dbutil=new Dbutil();
    private bookTypedao bookTypedao=new bookTypedao();
    private  bookdao bookdao=new bookdao();

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - 托马斯格兰森
        label1 = new JLabel();
        booknametxt = new JTextField();
        label2 = new JLabel();
        authortxt = new JTextField();
        label3 = new JLabel();
        menjrb = new JRadioButton();
        womanjrb = new JRadioButton();
        label4 = new JLabel();
        pricetxt = new JTextField();
        label5 = new JLabel();
        scrollPane1 = new JScrollPane();
        bookdesctxt = new JTextArea();
        label6 = new JLabel();
        booktypejcb = new JComboBox();
        into = new JButton();
        reset = new JButton();

        //======== this ========
        setTitle("\u56fe\u4e66\u6dfb\u52a0");
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u56fe\u4e66\u540d\u79f0\uff1a");
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(41, 29), label1.getPreferredSize()));
        contentPane.add(booknametxt);
        booknametxt.setBounds(119, 23, 135, booknametxt.getPreferredSize().height);

        //---- label2 ----
        label2.setText("\u4f5c\u8005\uff1a");
        contentPane.add(label2);
        label2.setBounds(new Rectangle(new Point(272, 29), label2.getPreferredSize()));
        contentPane.add(authortxt);
        authortxt.setBounds(359, 23, 112, authortxt.getPreferredSize().height);

        //---- label3 ----
        label3.setText("\u6027\u522b\uff1a");
        contentPane.add(label3);
        label3.setBounds(new Rectangle(new Point(41, 79), label3.getPreferredSize()));

        //---- menjrb ----
        menjrb.setText("\u7537");
        contentPane.add(menjrb);
        menjrb.setBounds(new Rectangle(new Point(89, 88), menjrb.getPreferredSize()));

        //---- womanjrb ----
        womanjrb.setText("\u5973");
        contentPane.add(womanjrb);
        womanjrb.setBounds(new Rectangle(new Point(130, 88), womanjrb.getPreferredSize()));

        //---- label4 ----
        label4.setText("\u56fe\u4e66\u4ef7\u683c\uff1a");
        contentPane.add(label4);
        label4.setBounds(new Rectangle(new Point(272, 90), label4.getPreferredSize()));
        contentPane.add(pricetxt);
        pricetxt.setBounds(359, 84, 112, pricetxt.getPreferredSize().height);

        //---- label5 ----
        label5.setText("\u56fe\u4e66\u63cf\u8ff0\uff1a");
        contentPane.add(label5);
        label5.setBounds(new Rectangle(new Point(41, 243), label5.getPreferredSize()));

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(bookdesctxt);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(130, 242, 291, 99);

        //---- label6 ----
        label6.setText("\u56fe\u4e66\u7c7b\u522b\uff1a");
        contentPane.add(label6);
        label6.setBounds(new Rectangle(new Point(41, 172), label6.getPreferredSize()));
        contentPane.add(booktypejcb);
        booktypejcb.setBounds(130, 165, 165, booktypejcb.getPreferredSize().height);

        //---- into ----
        into.setText("\u6dfb\u52a0");
        into.addActionListener(e -> into(e));
        contentPane.add(into);
        into.setBounds(new Rectangle(new Point(46, 359), into.getPreferredSize()));

        //---- reset ----
        reset.setText("\u91cd\u7f6e");
        reset.addActionListener(e -> reset(e));
        contentPane.add(reset);
        reset.setBounds(new Rectangle(new Point(278, 359), reset.getPreferredSize()));

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
    private JTextField booknametxt;
    private JLabel label2;
    private JTextField authortxt;
    private JLabel label3;
    private JRadioButton menjrb;
    private JRadioButton womanjrb;
    private JLabel label4;
    private JTextField pricetxt;
    private JLabel label5;
    private JScrollPane scrollPane1;
    private JTextArea bookdesctxt;
    private JLabel label6;
    private JComboBox booktypejcb;
    private JButton into;
    private JButton reset;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
    //初始化图书类别下拉框
    private  void fillbooktype(){
        Connection con=null;
        booktype booktype=null;
        try{
con=dbutil.getCon();
            ResultSet rs=bookTypedao.list(con,new booktype());
            while (rs.next()){
                booktype=new booktype();
                booktype.setId(rs.getInt("id"));
                booktype.setBookTypeName(rs.getString("bookName"));
                this.booktypejcb.addItem(booktype.tostring());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }
    }
    public static void main(String[] args) {
        bookadd bookadd=new bookadd();
        bookadd.setVisible(true);
    }
}
