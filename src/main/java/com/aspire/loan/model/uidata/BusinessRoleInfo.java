package com.aspire.loan.model.uidata;

import com.aspire.loan.model.uidata.configtype.BusinessRoleType;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder(builderMethodName = "setRole", setterPrefix = "with", toBuilder = true)
public class BusinessRoleInfo {

    private BusinessRoleType roleName;
    private Map<String,String> additionalDetails;

}

