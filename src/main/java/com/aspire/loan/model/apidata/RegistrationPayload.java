package com.aspire.loan.model.apidata;

import com.google.gson.annotations.SerializedName;

public class RegistrationPayload {
    @SerializedName(value = "full_name")
    private String fullName;
    private String email;
    private String phone;
    private boolean privacy;

    @SerializedName(value = "heard_about")
    String hearAbout;


    public RegistrationPayload(String fullName, String email, String phone, boolean privacy, String hearAbout) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.privacy = privacy;
        this.hearAbout = hearAbout;
    }
}
