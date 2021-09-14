package com.aspire.loan.model.uidata;

import com.aspire.loan.model.uidata.configtype.IncorporatePackageType;
import com.aspire.loan.ui.pages.businessrole.AbstractAdditionalData;
import lombok.Builder;
import lombok.Data;


@Data
@Builder(builderMethodName = "setAdditionalInfo", setterPrefix = "with", toBuilder = true)
public class IncorporateAdditionalInfo extends AbstractAdditionalData {

    private String nationality;
    private IncorporatePackageType packageType;
    private String businessName;
    private String liveWebsite;
    private String numberOfShareHolder;
    private String financialYearEndDate;
    
}
