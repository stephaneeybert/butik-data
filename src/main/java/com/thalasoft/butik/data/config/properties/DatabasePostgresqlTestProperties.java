package com.thalasoft.butik.data.config.properties;

import com.thalasoft.toolbox.condition.DbPostgresql;
import com.thalasoft.toolbox.condition.EnvTest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@EnvTest
@DbPostgresql
@Component
@PropertySource({ "classpath:postgresql/data-source-test.properties", "classpath:postgresql/flyway.properties" })
public class DatabasePostgresqlTestProperties extends AbstractDatabaseProperties {

    private static Logger logger = LoggerFactory.getLogger(DatabaseH2TestProperties.class);

    public DatabasePostgresqlTestProperties() {
        logger.debug("Loading the Postgresql Test properties file");
    }

}
