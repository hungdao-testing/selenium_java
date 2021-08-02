package com.aspire.loan.dataschema;

public class BusinessRole {
    private String role;

    private AdditionalDetail additionalDetail;

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }

    public void setAdditionalDetail(AdditionalDetail additionalDetail) {
        this.additionalDetail = additionalDetail;
    }

    @Override
    public String toString() {
        return "BusinessRole{" +
                "role='" + role + '\'' +
                ", additionalDetail=" + additionalDetail +
                '}';
    }

    public AdditionalDetail getAdditionalDetail() {
        return this.additionalDetail;
    }
}
