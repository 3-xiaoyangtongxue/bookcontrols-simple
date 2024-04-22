package com.java1234.model;
//图书类别实体
public class booktype implements returnstring {
    private int id;
    private  String bookTypeName;
    private  String bookdesc ;

    public String getBookTypeName() {
        return bookTypeName;
    }

    public booktype() {
        super();
    }

    public booktype(String bookTypeName, String bookdesc) {
        super();
        this.bookTypeName = bookTypeName;
        this.bookdesc = bookdesc;
    }

    public booktype(String bookTypeName) {
        this.bookTypeName = bookTypeName;
    }

    public booktype(int id, String bookTypeName, String bookdesc) {
        super();
        this.id = id;
        this.bookTypeName = bookTypeName;
        this.bookdesc = bookdesc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBookTypeName(String bookTypeName) {
        this.bookTypeName = bookTypeName;
    }

    public String getBookdesc() {
        return bookdesc;
    }

    public void setBookdesc(String bookdesc) {
        this.bookdesc = bookdesc;
    }
    @Override
    public  String tostring(){
        return  bookTypeName;
    }
}
