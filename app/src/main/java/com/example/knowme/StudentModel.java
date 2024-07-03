package com.example.knowme;

public class StudentModel {
    String name,mobile,reg,cls,type;

    public StudentModel() {
    }

    public StudentModel(String name, String mobile, String reg, String cls, String type) {
        this.name = name;
        this.mobile = mobile;
        this.reg = reg;
        this.cls = cls;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getReg() {
        return reg;
    }

    public void setReg(String reg) {
        this.reg = reg;
    }

    public String getCls() {
        return cls;
    }

    public void setCls(String cls) {
        this.cls = cls;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
