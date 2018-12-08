package com.thalasoft.butik.data.config.properties;

import org.springframework.beans.factory.annotation.Value;

public abstract class AbstractDatabaseProperties implements DatabaseProperties {

    @Value("${" + PropertyNames.CONFIG_DATA_SOURCE_DIALECT + "}")
    private String hibernateDialect;
    @Value("${" + PropertyNames.CONFIG_DATA_SOURCE_DRIVER + "}")
    private String hibernateDriverClassName;
    @Value("${" + PropertyNames.CONFIG_DATA_SOURCE_URL + "}")
    private String dataSourceUrl;
    @Value("${" + PropertyNames.CONFIG_DATA_SOURCE_USERNAME + "}")
    private String dataSourceUsername;
    @Value("${" + PropertyNames.CONFIG_DATA_SOURCE_PASSWORD + "}")
    private String dataSourcePassword;
    @Value("${" + PropertyNames.CONFIG_DATA_SOURCE_DDL_AUTO + "}")
    private String ddlAuto;

    public String getHibernateDialect() {
        return hibernateDialect;
    }

    public String getHibernateDriverClassName() {
        return hibernateDriverClassName;
    }

    public String getDataSourceUrl() {
        return dataSourceUrl;
    }

    public String getDataSourceUsername() {
        return dataSourceUsername;
    }

    public String getDataSourcePassword() {
        return dataSourcePassword;
    }

    public String getDdlAuto() {
        return ddlAuto;
    }

}
