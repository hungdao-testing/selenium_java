package com.aspire.loan.model.uidata;

import com.aspire.loan.model.uidata.configtype.BusinessRegistrationMethodType;
import com.aspire.loan.model.uidata.configtype.BusinessRoleType;
import lombok.Builder;
import lombok.Data;


@Data
@Builder(builderMethodName = "setBusiness", setterPrefix = "with", toBuilder = true)
public class BusinessInfo {

    private BusinessRoleType roleName;
    private BusinessRegistrationMethodType businessRegistrationMethodType;
    private AdditionalRoleDetailInfo additionalRoleDetail;
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

