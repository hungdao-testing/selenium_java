package com.aspire.loan.dataschema;

public class PersonalDetail {
    private String email;

    private String gender;

    private String DOB;

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return this.gender;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    @Override
    public String toString() {
        return "PersonalDetail{" +
                "email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", DOB='" + DOB + '\'' +
                '}';
    }

    public String getDOB() {
        return this.DOB;
    }
}