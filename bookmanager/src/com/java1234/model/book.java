package com.java1234.model;
//图书食堂
public class book {
    private int id;
    private String bookname;
    private String author;
    private String sex;
    private Float price;
    private Integer booktypeid;
    private String booktypename;
    private String bookdesc;

    public book() {
        super();
    }

    public book(int id, String bookname, String author, String sex, Float price, Integer booktypeid,  String bookdesc) {
       super();
        this.id = id;
        this.bookname = bookname;
        this.author = author;
        this.sex = sex;
        this.price = price;
        this.booktypeid = booktypeid;

        this.bookdesc = bookdesc;
    }

    public book(String bookname, String author, Integer booktypeid) {
        super();
        this.bookname = bookname;
        this.author=author;
        this.booktypeid=booktypeid;
    }

    public book(String bookname, String author, String sex, Float price, Integer booktypeid, String bookdesc) {

        this.bookname = bookname;
        this.author = author;
        this.sex = sex;
        this.price = price;
this.booktypeid=booktypeid;

        this.bookdesc = bookdesc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getBooktypeid() {
        return booktypeid;
    }

    public void setBooktypeid(Integer booktypeid) {
        this.booktypeid = booktypeid;
    }

    public String getBooktypename() {
        return booktypename;
    }

    public void setBooktypename(String booktypename) {
        this.booktypename = booktypename;
    }

    public String getBookdesc() {
        return bookdesc;
    }

    public void setBookdesc(String bookdesc) {
        this.bookdesc = bookdesc;
    }
}
