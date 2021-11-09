package com.aspire.loan.models.uidata;

import com.aspire.loan.models.uidata.configtype.IncorporatePackageType;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(builderMethodName = "setAdditionalInfo", setterPrefix = "with", toBuilder = true)
public class AdditionalRoleDetail {

    //Corporate Data
    private String country;
    private List<String> solutions;
    private boolean isCompanyRegister;

    //Incorporate Data
    private String nationality;
    private IncorporatePackageType packageType;
    private String businessName;
    private String liveWebsite;
    private String numberOfShareHolder;
    private String financialYearEndDate;
}
