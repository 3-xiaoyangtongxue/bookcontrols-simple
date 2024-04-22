package com.java1234.dao;

import com.java1234.model.booktype;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import  com.java1234.util.stringutil;
public class bookTypedao {
    public int add(Connection con, booktype booktype)throws  Exception{
        String sql="insert into t_booktype values(null,?,?)";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1,booktype.getBookTypeName());
        pstmt.setString(2,booktype.getBookdesc());
        return pstmt.executeUpdate();
    }
    //查询图书类别
    public ResultSet list(Connection con,booktype booktype)throws  Exception{
        StringBuilder sb=new StringBuilder("select * from t_booktype");
        if(stringutil.isnotempty(booktype.getBookTypeName())){
            sb.append(" and bookname like '%"+booktype.getBookTypeName()+"%'");
        }PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and","where"));
        ResultSet rs=pstmt.executeQuery();
//        System.out.println(rs.getInt("id"));
        return rs;
    }
    //删除图书类别
    public int delete(Connection con,String id)throws  Exception{
        String sql="delete from t_booktype where id=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1,id);
        return pstmt.executeUpdate();
    }
    //更新图书类别
    public int update(Connection con,booktype booktype)throws Exception{
        String sql="update t_booktype set bookname=?,bookDesc=? where id=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1,booktype.getBookTypeName());
        pstmt.setString(2,booktype.getBookdesc());
        pstmt.setInt(3,booktype.getId());
        return pstmt.executeUpdate();

    }
}
