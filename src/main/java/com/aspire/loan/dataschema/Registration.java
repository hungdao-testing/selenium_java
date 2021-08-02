package com.aspire.loan.dataschema;

public class Registration {
    private String fullName;

    private String email;

    private String countryPhoneCode;

    private String phoneNumber;

    private String hearAboutUs;

    @Override
    public String toString() {
        return "Registration{" +
                "fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", countryPhoneCode='" + countryPhoneCode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", hearAboutUs='" + hearAboutUs + '\'' +
                '}';
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setCountryPhoneCode(String countryPhoneCode) {
        this.countryPhoneCode = countryPhoneCode;
    }

    public String getCountryPhoneCode() {
        return this.countryPhoneCode;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setHearAboutUs(String hearAboutUs) {
        this.hearAboutUs = hearAboutUs;
    }

    public String getHearAboutUs() {
        return this.hearAboutUs;
    }
}
