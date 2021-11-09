package com.aspire.loan.datagenerator.builder;

import com.aspire.loan.datagenerator.builder.rest_service.*;
import com.aspire.loan.model.uidata.AdditionalRoleDetailInfo;
import com.aspire.loan.model.uidata.BusinessInfo;
import com.aspire.loan.model.uidata.configtype.BusinessRegistrationMethodType;
import com.aspire.loan.model.uidata.configtype.BusinessRoleType;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class BusinessDataBuilder extends BuilderSetup{

    private static final String defaultCountry = "Singapore";

    private BusinessInfo.BusinessInfoBuilder setBusiness(){
        return BusinessInfo.setBusiness().withCountry(defaultCountry);
    }

    private AdditionalRoleDetailInfo.AdditionalRoleDetailInfoBuilder setAdditionalRoleDetailForCorporateBusiness(){
        List<String> randomSolutionOptions = new OptionServices().getRandomSolutionOptions();
        return AdditionalRoleDetailInfo.setAdditionalInfo()
                .withCountry(defaultCountry)
                .withSolutions(randomSolutionOptions)
                .withIsCompanyRegister(false);
    }

    public BusinessInfo generateStandardCorporateBusiness(){
        String countryCode = new CountryService().getCountryCodeByName(defaultCountry);
        List<BusinessRoleType> businessRoleTypes = Arrays.asList(
                                                        BusinessRoleType.DIRECTOR, BusinessRoleType.EMPLOYEE,
                                                        BusinessRoleType.FREELANCER);

        BusinessRoleType randomBusinessRole = BuilderSetup.getRandomValueInDefinedRange(businessRoleTypes);


        Map<String, String> randomEntityCategory = new EntityCategoryService().getRandomEntityCategory();
        Map<String, String> industryData = new IndustryService().getRandomIndustryTypeByCountryCode(countryCode);

        BusinessInfo businessInfo = new BusinessDataBuilder()
                .setBusiness()
                .withRoleName(randomBusinessRole)
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

        LOGGER.info("Generate Standard Corporate Business Info Data: {} ", businessInfo.toString());
        return businessInfo;
    }

    public BusinessInfo generateStandardForBusinessRole(BusinessRoleType roleType){

        String countryCode = new CountryService().getCountryCodeByName(defaultCountry);
        List<BusinessRoleType> corporateBusinessRoleTypes = Arrays.asList(
                BusinessRoleType.DIRECTOR, BusinessRoleType.EMPLOYEE,
                BusinessRoleType.FREELANCER);

        BusinessRoleType randomBusinessRole = BuilderSetup.getRandomValueInDefinedRange(corporateBusinessRoleTypes);

        if(!corporateBusinessRoleTypes.toString().contains(roleType.getRoleDesc())){
            randomBusinessRole = BusinessRoleType.ENTREPRENEUR;
        }

        Map<String, String> randomEntityCategory = new EntityCategoryService().getRandomEntityCategory();
        Map<String, String> industryData = new IndustryService().getRandomIndustryTypeByCountryCode(countryCode);

        BusinessInfo businessInfo = new BusinessDataBuilder()
                .setBusiness()
                .withRoleName(randomBusinessRole)
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
