package com.example.knowme;

public class TeacherModel {
    String mobile,name,empno,cls,type;

    public TeacherModel() {
    }

    public TeacherModel(String mobile, String name, String empno, String cls, String type) {
        this.mobile = mobile;
        this.name = name;
        this.empno = empno;
        this.cls = cls;
        this.type = type;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmpno() {
        return empno;
    }

    public void setEmpno(String empno) {
        this.empno = empno;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
