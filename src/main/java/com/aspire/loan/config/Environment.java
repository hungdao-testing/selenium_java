package com.aspire.loan.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"classpath:${env}.env.properties"})
public interface Environment extends Config{

    @Key("app.baseUrl")
    String webUrl();

    @Key("app.apiUrl")
    String apiUrl();

    @Key("app.locale")
    String locale();

    @Key("locale_file")
    String localeFile();

}
