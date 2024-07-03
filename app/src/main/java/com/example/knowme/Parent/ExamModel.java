package com.example.knowme.Parent;

public class ExamModel {
    String code,name,marks,total,cgpa;

    public ExamModel() {
    }

    public ExamModel(String code, String name, String marks, String total, String cgpa) {
        this.code = code;
        this.name = name;
        this.marks = marks;
        this.total = total;
        this.cgpa = cgpa;
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

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getCgpa() {
        return cgpa;
    }

    public void setCgpa(String cgpa) {
        this.cgpa = cgpa;
    }
}
