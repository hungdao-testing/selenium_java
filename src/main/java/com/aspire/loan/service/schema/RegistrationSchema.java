package com.aspire.loan.service.schema;

import com.google.gson.annotations.SerializedName;

public class RegistrationSchema {
    @SerializedName(value = "full_name")
    private String fullName;
    private String email;
    private String phone;
    private boolean privacy;

    @SerializedName(value = "heard_about")
    String hearAbout;


    public RegistrationSchema(String fullName, String email, String phone, boolean privacy, String hearAbout) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.privacy = privacy;
        this.hearAbout = hearAbout;
    }
}
