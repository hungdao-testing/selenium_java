package com.aspire.loan.datagenerator.builder;

import com.aspire.loan.datagenerator.builder.helper.CountryServiceHelper;
import com.aspire.loan.datagenerator.builder.helper.EntityCategoryServiceHelper;
import com.aspire.loan.datagenerator.builder.helper.IndustryServiceHelper;
import com.aspire.loan.datagenerator.builder.helper.OptionServiceHelper;
import com.aspire.loan.model.uidata.AdditionalRoleDetailInfo;
import com.aspire.loan.model.uidata.BusinessInfo;
import com.aspire.loan.model.uidata.configtype.BusinessRegistrationMethodType;
import com.aspire.loan.model.uidata.configtype.BusinessRoleType;
import com.aspire.loan.model.uidata.configtype.IncorporatePackageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class BusinessDataBuilder {

    private static final String defaultCountry = "Singapore";
    public Logger LOGGER = LoggerFactory.getLogger(this.getClass().getSimpleName());

    private BusinessInfo.BusinessInfoBuilder setBusiness(){
        return BusinessInfo.setBusiness().withCountry(defaultCountry);
    }

    private AdditionalRoleDetailInfo.AdditionalRoleDetailInfoBuilder setAdditionalRoleDetailForCorporateBusiness(){
        List<String> randomSolutionOptions = new OptionServiceHelper().getRandomSolutionOptions();
        return AdditionalRoleDetailInfo.setAdditionalInfo()
                .withCountry(defaultCountry)
                .withSolutions(randomSolutionOptions)
                .withIsCompanyRegister(false);
    }

    private AdditionalRoleDetailInfo.AdditionalRoleDetailInfoBuilder setAdditionalRoleDetailForInCorporateBusiness(){
        List<IncorporatePackageType> packageTypes = Arrays.asList(
                IncorporatePackageType.KICKSTART, IncorporatePackageType.KICKSTART_PLUS);

        IncorporatePackageType randomPackageType = BuilderSetup.getRandomValueInDefinedRange(packageTypes);

        return AdditionalRoleDetailInfo.setAdditionalInfo()
                .withNationality(defaultCountry)
                .withPackageType(randomPackageType)
                .withBusinessName(BuilderSetup.faker.funnyName().name())
                .withLiveWebsite("https://www.local.com")
                .withNumberOfShareHolder(String.valueOf(BuilderSetup.faker.number().numberBetween(12, 25)))
                .withNumberOfShareHolder(String.valueOf(BuilderSetup.faker.date()));
    }

    public BusinessInfo generateStandardCorporateBusiness(){
        String countryCode = new CountryServiceHelper().getCountryCodeByName(defaultCountry);
        List<BusinessRoleType> businessRoleTypes = Arrays.asList(
                                                        BusinessRoleType.DIRECTOR, BusinessRoleType.EMPLOYEE,
                                                        BusinessRoleType.FREELANCER);

        BusinessRoleType randomBusinessRole = BuilderSetup.getRandomValueInDefinedRange(businessRoleTypes);


        Map<String, String> randomEntityCategory = new EntityCategoryServiceHelper().getRandomEntityCategory();
        Map<String, String> industryData = new IndustryServiceHelper().getRandomIndustryTypeByCountryCode(countryCode);

        BusinessInfo businessInfo = new BusinessDataBuilder()
                .setBusiness()
                .withRoleName(randomBusinessRole)
                .withBusinessRegistrationMethodType(BusinessRegistrationMethodType.STANDARD)
                .withAdditionalRoleDetail(setAdditionalRoleDetailForCorporateBusiness().build())
                .withRegistrationMethodType(BusinessRegistrationMethodType.STANDARD)
                .withBusinessLegalName(BuilderSetup.faker.funnyName().name())
                .withEntityCategory(randomEntityCategory.get("entityCategory"))
                .withEntityType(randomEntityCategory.get("entityType"))
                .withBusinessRegistrationNumber(BuilderSetup.faker.regexify("([0-9]{8,9}[a-zA-Z]{1})"))
                .withIndustry(industryData.get("industry"))
                .withSubIndustry(industryData.get("subIndustry"))
                .build();

        LOGGER.info("Generate Standard Corporate Business Info Data: {} ", businessInfo.toString());
        return businessInfo;
    }

    public BusinessInfo generateStandardForBusinessRole(BusinessRoleType roleType){

        String countryCode = new CountryServiceHelper().getCountryCodeByName(defaultCountry);
        List<BusinessRoleType> corporateBusinessRoleTypes = Arrays.asList(
                BusinessRoleType.DIRECTOR, BusinessRoleType.EMPLOYEE,
                BusinessRoleType.FREELANCER);

        BusinessRoleType randomBusinessRole = BuilderSetup.getRandomValueInDefinedRange(corporateBusinessRoleTypes);

        if(!corporateBusinessRoleTypes.toString().contains(roleType.getRoleDesc())){
            randomBusinessRole = BusinessRoleType.ENTREPRENEUR;
        }

        Map<String, String> randomEntityCategory = new EntityCategoryServiceHelper().getRandomEntityCategory();
        Map<String, String> industryData = new IndustryServiceHelper().getRandomIndustryTypeByCountryCode(countryCode);

        BusinessInfo businessInfo = new BusinessDataBuilder()
                .setBusiness()
                .withRoleName(randomBusinessRole)
                .withBusinessRegistrationMethodType(BusinessRegistrationMethodType.STANDARD)
                .withAdditionalRoleDetail(setAdditionalRoleDetailForCorporateBusiness().build())
                .withRegistrationMethodType(BusinessRegistrationMethodType.STANDARD)
                .withBusinessLegalName(BuilderSetup.faker.funnyName().name())
                .withEntityCategory(randomEntityCategory.get("entityCategory"))
                .withEntityType(randomEntityCategory.get("entityType"))
                .withBusinessRegistrationNumber(BuilderSetup.faker.regexify("([0-9]{8,9}[a-zA-Z]{1})"))
                .withIndustry(industryData.get("industry"))
                .withSubIndustry(industryData.get("subIndustry"))
                .build();

        LOGGER.info("Generate Standard Business Info Data: {}, for role {} ", businessInfo.toString(), roleType.toString());
        return businessInfo;
    }
}
