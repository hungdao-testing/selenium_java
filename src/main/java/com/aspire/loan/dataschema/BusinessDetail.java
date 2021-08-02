package com.aspire.loan.dataschema;

public class BusinessDetail {
    private String legalName;

    private String entity;

    private String entityType;

    private String businessRegistrationNumber;

    private String industry;

    @Override
    public String toString() {
        return "BusinessDetail{" +
                "legalName='" + legalName + '\'' +
                ", entity='" + entity + '\'' +
                ", entityType='" + entityType + '\'' +
                ", businessRegistrationNumber='" + businessRegistrationNumber + '\'' +
                ", industry='" + industry + '\'' +
                ", subIndustry='" + subIndustry + '\'' +
                '}';
    }

    private String subIndustry;

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public String getLegalName() {
        return this.legalName;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getEntity() {
        return this.entity;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getEntityType() {
        return this.entityType;
    }

    public void setBusinessRegistrationNumber(String businessRegistrationNumber) {
        this.businessRegistrationNumber = businessRegistrationNumber;
    }

    public String getBusinessRegistrationNumber() {
        return this.businessRegistrationNumber;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getIndustry() {
        return this.industry;
    }

    public void setSubIndustry(String subIndustry) {
        this.subIndustry = subIndustry;
    }

    public String getSubIndustry() {
        return this.subIndustry;
    }
}
