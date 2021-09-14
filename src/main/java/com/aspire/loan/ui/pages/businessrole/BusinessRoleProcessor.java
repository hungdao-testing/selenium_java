package com.aspire.loan.ui.pages.businessrole;

import java.util.Map;

public interface BusinessRoleProcessor {

    public void process(Map<String,String> additionalDetails);

    default AbstractAdditionalData processNew(AbstractAdditionalData data){
        return data.getInstance();
    }
}
