package com.java1234.dao;

import com.java1234.model.book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.java1234.model.booktype;
import com.java1234.util.stringutil;

//图书dao
public class bookdao {
    public  int add(Connection con, book book)throws  Exception{
        String sql="insert into book values (null,?,?,?,?,?,?)";
        PreparedStatement ptmt=con.prepareStatement(sql);
        ptmt.setString(1,book.getBookname());
        ptmt.setString(2,book.getAuthor());
        ptmt.setString(3,book.getSex());
        ptmt.setFloat(4,book.getPrice());
        System.out.println(book.getBooktypeid());
        ptmt.setInt(5,book.getBooktypeid());
        ptmt.setString(6,book.getBookdesc());
        return ptmt.executeUpdate();
    }
    //图书信息查询
    public ResultSet list(Connection con,book book)throws Exception{
        StringBuilder sb=new StringBuilder("select * from book b join t_booktype bt on b.booktypeid=bt.id ");
        if(stringutil.isnotempty(book.getBookname())){
            sb.append(" and b.bookName like '%"+book.getBookname()+"%'");
        }
        if(stringutil.isnotempty(book.getBookname())){
            sb.append(" and b.auther like'%"+book.getAuthor()+"%'");
        }
//if(book.getBooktypeid()!=null&&book.getBooktypeid()!=-1){
//    sb.append(" and  b.booktypeid="+book.getBooktypeid());
//}
        PreparedStatement pstmt=con.prepareStatement(sb.toString());
        return pstmt.executeQuery();
    }
    //图书信息删除
    public  int delete(Connection con,String id)throws  Exception{
        String sql="delete from book where id=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1,id);
        return pstmt.executeUpdate();
    }
    //图书信息修改
    public int update(Connection con,book book)throws Exception{
        String sql="update book set bookName=?,auther=?,sex=?,price=?,bookdesc=?,booktypeid=? where id =?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1,book.getBookname());
        pstmt.setString(2, book.getAuthor());
        pstmt.setString(3,book.getSex());
        pstmt.setFloat(4,book.getPrice());
        pstmt.setString(5,book.getBookdesc());
        pstmt.setInt(6,book.getBooktypeid());
        pstmt.setInt(7,book.getId());
        return pstmt.executeUpdate();
    }
}
