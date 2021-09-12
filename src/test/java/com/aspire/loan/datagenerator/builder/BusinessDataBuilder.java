package com.aspire.loan.datagenerator.builder;

import com.aspire.loan.datagenerator.builder.helper.CountryServiceHelper;
import com.aspire.loan.datagenerator.builder.helper.EntityCategoryServiceHelper;
import com.aspire.loan.datagenerator.builder.helper.IndustryServiceHelper;
import com.aspire.loan.model.uidata.BusinessInfo;
import com.aspire.loan.model.uidata.configtype.BusinessRegistrationMethodType;
import com.aspire.loan.model.uidata.configtype.BusinessRoleType;
import com.aspire.loan.model.uidata.configtype.EntityType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BusinessDataBuilder {

    private static final String defaultCountry = "Singapore";
    public Logger LOGGER = LoggerFactory.getLogger(this.getClass().getSimpleName());

    private BusinessInfo.BusinessInfoBuilder setBusiness(){
        return BusinessInfo.setBusiness().withCountry(defaultCountry);
    }

    public BusinessInfo generateStandardCorporateBusinessInCountry(Map<String,String> additionalRoleDetails){
        String countryCode = new CountryServiceHelper().getCountryCodeByName(defaultCountry);
        List<BusinessRoleType> businessRoleTypes = Arrays.asList(BusinessRoleType.DIRECTOR, BusinessRoleType.EMPLOYEE, BusinessRoleType.FREELANCER);
        List<EntityType> entityTypes = Arrays.asList(
                                        EntityType.LIMITED_LIABILITY_COMP, EntityType.LIMITED_LIABILITY_PARTNER,
                                        EntityType.LIMITED_PARTNER, EntityType.PRIVATE_LIMITED);

        BusinessRoleType randomBusinessRole = BuilderSetup.getRandomValueInDefinedRange(businessRoleTypes);
        EntityType randomEntityType = BuilderSetup.getRandomValueInDefinedRange(entityTypes);
        Map<String, String> industryData = new IndustryServiceHelper().getRandomIndustryTypeByCountryCode(countryCode);

        BusinessInfo businessInfo = new BusinessDataBuilder()
                .setBusiness()
                .withRoleName(randomBusinessRole)
                .withAdditionalRoleDetail(additionalRoleDetails)
                .withRegistrationMethodType(BusinessRegistrationMethodType.STANDARD)
                .withBusinessLegalName(BuilderSetup.faker.funnyName().name())
                .withEntityCategory(new EntityCategoryServiceHelper().getRandomEntityCategory())
                .withEntityType(randomEntityType)
                .withBusinessRegistrationNumber(BuilderSetup.faker.regexify("([0-9]{8,9}[a-zA-Z]{1})"))
                .withIndustry(industryData.get("industry"))
                .withSubIndustry(industryData.get("subIndustry"))
                .build();

        LOGGER.info("Generate Business Info Data: {} ", businessInfo.toString());
        return businessInfo;
    }

}
