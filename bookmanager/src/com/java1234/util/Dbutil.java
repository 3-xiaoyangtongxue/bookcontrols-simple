package com.java1234.util;
import  java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
//import com.mysql.cj.jdbc.Driver;
/*数据库工具类*/
//数据库连接
public class Dbutil {
    private String dbUrl="jdbc:mysql://localhost:3306/xuexi?characterEncoding=utf-8&&serverTimezone=UTC&&useSSL=false";
    private String dbUserName="root";
    private String dbPassWord="123456";
    private String jdbcName="com.mysql.cj.jdbc.Driver";

//    private String dbUrl="jdbc:sqlserver://localhost:1433;databaseName=db_book";
//    private String dbUserName="sa";
//    private String dbPassWord="YZ25085802";
//    private String jdbcName="com.microsoft.sqlserver.jdbc.SQLServerDriver";





    public Connection getCon() throws  Exception{
        Class.forName(jdbcName);
        Connection con=DriverManager.getConnection(dbUrl,dbUserName,dbPassWord);
        return con;
    }
    public void closeCon(Connection con) throws  Exception{
        if(con !=null){
            con.close();
        }
    }

    public static void main(String[] args) {
        Dbutil dbuil=new Dbutil();
        try {
            dbuil.getCon();
            System.out.println("数据库连接成功");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("数据库连接失败");
        }
    }
}
