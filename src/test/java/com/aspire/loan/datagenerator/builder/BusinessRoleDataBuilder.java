package com.aspire.loan.datagenerator.builder;

import com.aspire.loan.model.uidata.BusinessRoleInfo;
import com.aspire.loan.model.uidata.configtype.BusinessRoleType;

import java.util.Map;

public class BusinessRoleDataBuilder {

    private BusinessRoleInfo.BusinessRoleInfoBuilder setRole(BusinessRoleType roleType){
        return BusinessRoleInfo.setRole().withRoleName(roleType);
    }

    protected BusinessRoleInfo generateCorporateBusiness(BusinessRoleType roleType, Map<String,String> additionalDetails){

        return new BusinessRoleDataBuilder().setRole(roleType).withAdditionalDetails(additionalDetails).build();
    }
}
