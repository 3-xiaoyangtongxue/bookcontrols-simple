package com.java1234.model;
/*用户实体*/
public class User {
    private  int id;
    private String userName;
    private  String password;
    public User(){
        super();
    }
    public User(String userName,String password){
        this.userName=userName;
        this.password=password;
    }
    public  int getId(int id){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
