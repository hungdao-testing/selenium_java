package com.aspire.loan.dataschema;

public class DataSchema {
    private Registration registration;

    private BusinessRole businessRole;

    private PersonalDetail personalDetail;

    private BusinessDetail businessDetail;

    private BusinessDetailEdit businessDetailEdit;

    public void setRegistration(Registration registration){
        this.registration = registration;
    }
    public Registration getRegistration(){
        return this.registration;
    }
    public void setBusinessRole(BusinessRole businessRole){
        this.businessRole = businessRole;
    }
    public BusinessRole getBusinessRole(){
        return this.businessRole;
    }
    public void setPersonalDetail(PersonalDetail personalDetail){
        this.personalDetail = personalDetail;
    }
    public PersonalDetail getPersonalDetail(){
        return this.personalDetail;
    }
    public void setBusinessDetail(BusinessDetail businessDetail){
        this.businessDetail = businessDetail;
    }
    public BusinessDetail getBusinessDetail(){
        return this.businessDetail;
    }
    public void setBusinessDetailEdit(BusinessDetailEdit businessDetailEdit){
        this.businessDetailEdit = businessDetailEdit;
    }
    public BusinessDetailEdit getBusinessDetailEdit(){
        return this.businessDetailEdit;
    }
}
