package com.example.knowme.Teacher;

public class SubjectModel {
    String code,name,sname,total,sem,cls;

    public SubjectModel() {
    }

    public SubjectModel(String code, String name, String sname, String total, String sem, String cls) {
        this.code = code;
        this.name = name;
        this.sname = sname;
        this.total = total;
        this.sem = sem;
        this.cls = cls;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getSem() {
        return sem;
    }

    public void setSem(String sem) {
        this.sem = sem;
    }

    public String getCls() {
        return cls;
    }

    public void setCls(String cls) {
        this.cls = cls;
    }
}

