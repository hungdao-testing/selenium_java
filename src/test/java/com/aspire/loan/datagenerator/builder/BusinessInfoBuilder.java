package com.aspire.loan.datagenerator.builder;

import com.aspire.loan.datagenerator.builder.rest_service.*;
import com.aspire.loan.models.uidata.AdditionalRoleDetail;
import com.aspire.loan.models.uidata.BusinessModel;
import com.aspire.loan.models.uidata.configtype.BusinessRegistrationMethodType;
import com.aspire.loan.models.uidata.configtype.BusinessRoleType;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class BusinessInfoBuilder extends BuilderSetup{

    private static final String defaultCountry = "Singapore";

    private BusinessModel.BusinessModelBuilder setBusiness(){
        return BusinessModel.setBusiness().withCountry(defaultCountry);
    }

    private AdditionalRoleDetail.AdditionalRoleDetailBuilder setAdditionalRoleDetailForCorporateBusiness(){
        List<String> randomSolutionOptions = new OptionServices().getRandomSolutionOptions();
        return AdditionalRoleDetail.setAdditionalInfo()
                .withCountry(defaultCountry)
                .withSolutions(randomSolutionOptions)
                .withIsCompanyRegister(false);
    }

    public BusinessModel generateStandardForBusinessRole(BusinessRoleType roleType){

        String countryCode = new CountryService().getCountryCodeByName(defaultCountry);

        Map<String, String> randomEntityCategory = new OptionServices().getRandomEntityCategory();
        Map<String, String> industryData = new IndustryService().getRandomIndustryTypeByCountryCode(countryCode);

        BusinessModel businessInfo = new BusinessInfoBuilder()
                .setBusiness()
                .withRoleName(roleType)
                .withBusinessRegistrationMethodType(BusinessRegistrationMethodType.STANDARD)
                .withAdditionalRoleDetail(setAdditionalRoleDetailForCorporateBusiness().build())
                .withRegistrationMethodType(BusinessRegistrationMethodType.STANDARD)
                .withBusinessLegalName(faker.funnyName().name())
                .withEntityCategory(randomEntityCategory.get("entityCategory"))
                .withEntityType(randomEntityCategory.get("entityType"))
                .withBusinessRegistrationNumber(faker.regexify("([0-9]{8,9}[a-zA-Z]{1})"))
                .withIndustry(industryData.get("industry"))
                .withSubIndustry(industryData.get("subIndustry"))
                .build();

        LOGGER.info("Generate Standard Business Info Data: {}, for role {} ", businessInfo.toString(), roleType.toString());
        return businessInfo;
    }
}
