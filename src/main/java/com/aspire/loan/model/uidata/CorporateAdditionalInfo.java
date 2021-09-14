package com.aspire.loan.model.uidata;


import com.aspire.loan.ui.pages.businessrole.AbstractAdditionalData;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder(builderMethodName = "setAdditionalInfo", setterPrefix = "with", toBuilder = true)
public class CorporateAdditionalInfo extends AbstractAdditionalData {

    private String country;
    private List<String> solutions;
    private boolean isCompanyRegister;


}
