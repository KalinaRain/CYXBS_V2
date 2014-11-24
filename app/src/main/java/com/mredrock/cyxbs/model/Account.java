package com.mredrock.cyxbs.model;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Created by David on 2014/11/4.
 */
public class Account {
    private String stuNum;
    private String idNum;
    private String name;
    private String gender;
    private String classNum;
    private String major;
    private String college;
    private String grade;
    public Account() {
    }

    public String getStuNum() {
        return stuNum;
    }

    public void setStuNum(String stuNum) {
        this.stuNum = stuNum;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public static class AccountResult extends Result{
        private Account data;

        public AccountResult() {
        }

        public Account getData() {
            return data;
        }

        public void setData(Account data) {
            this.data = data;
        }
    }
}
