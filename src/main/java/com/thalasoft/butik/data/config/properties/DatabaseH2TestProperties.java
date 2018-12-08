package com.thalasoft.butik.data.config.properties;

import com.thalasoft.toolbox.condition.DbH2;
import com.thalasoft.toolbox.condition.EnvTest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@EnvTest
@DbH2
@Component
@PropertySource({ "classpath:h2/data-source-test.properties", "classpath:h2/flyway.properties" })
public class DatabaseH2TestProperties extends AbstractDatabaseProperties {

    private static Logger logger = LoggerFactory.getLogger(DatabaseH2TestProperties.class);

    public DatabaseH2TestProperties() {
        logger.debug("Loading the H2 Test properties file");
    }

}
