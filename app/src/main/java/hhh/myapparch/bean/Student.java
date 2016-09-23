package hhh.myapparch.bean;

import java.util.Date;

/**
 * Created by hhh on 2016/6/12.
 */
public class Student {
    private String name;
    private String address;
    private Date birthday;

    public Student(){}

    public Student(String name, String address, Date birthday) {
        this.name = name;
        this.address = address;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
