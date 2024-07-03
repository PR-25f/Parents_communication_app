package com.example.knowme.Teacher;

public class FeeModel {
    String reg,sem,tot,pay,date;

    public FeeModel() {
    }

    public FeeModel(String reg, String sem, String tot, String pay, String date) {
        this.reg = reg;
        this.sem = sem;
        this.tot = tot;
        this.pay = pay;
        this.date = date;
    }

    public String getReg() {
        return reg;
    }

    public void setReg(String reg) {
        this.reg = reg;
    }

    public String getSem() {
        return sem;
    }

    public void setSem(String sem) {
        this.sem = sem;
    }

    public String getTot() {
        return tot;
    }

    public void setTot(String tot) {
        this.tot = tot;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}


