package com.aspire.loan.models.uidata;

import com.aspire.loan.models.uidata.configtype.BusinessRegistrationMethodType;
import com.aspire.loan.models.uidata.configtype.BusinessRoleType;
import lombok.Builder;
import lombok.Data;


@Data
@Builder(builderMethodName = "setBusiness", setterPrefix = "with", toBuilder = true)
public class BusinessModel {

    private BusinessRoleType roleName;
    private BusinessRegistrationMethodType businessRegistrationMethodType;
    private AdditionalRoleDetail additionalRoleDetail;
    private String country;
    private BusinessRegistrationMethodType registrationMethodType;
    private String businessLegalName;
    private String entityCategory;
    private String entityType;
    private String businessRegistrationNumber;
    private String industry;
    private String subIndustry;
    private String businessActivity;
    private String detailedBusinessActivity;
    private String liveWebsite;
    private String numberEmployees;
    private String annualRevenue;

}

