package com.lyl.mybatis.model;

import java.util.Date;

/**
 * @ClassName User
 * @Description TODO
 * @Author liyulong
 * @Date 2020/12/18 15:10
 * @Version 1.0
 **/
public class User {
    private Integer id; // 主键id
    private String username; // 用户名称
    private Date birthday; // 生日
    private String sex; // 性别
    private String address; // 地址

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", birthday=" + birthday + ", sex=" + sex + ", address="
                + address + "]";
    }

}
