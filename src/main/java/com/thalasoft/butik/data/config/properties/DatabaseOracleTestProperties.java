package com.thalasoft.butik.data.config.properties;

import com.thalasoft.toolbox.condition.DbOracle;
import com.thalasoft.toolbox.condition.EnvTest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@EnvTest
@DbOracle
@Component
@PropertySource({ "classpath:oracle/data-source-test.properties", "classpath:oracle/flyway.properties" })
public class DatabaseOracleTestProperties extends AbstractDatabaseProperties {

    private static Logger logger = LoggerFactory.getLogger(DatabaseOracleTestProperties.class);

    public DatabaseOracleTestProperties() {
        logger.debug("Loading the Oracle Test properties file");
    }

}
