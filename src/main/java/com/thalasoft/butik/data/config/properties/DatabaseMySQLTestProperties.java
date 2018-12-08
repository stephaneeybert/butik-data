package com.thalasoft.butik.data.config.properties;

import com.thalasoft.toolbox.condition.DbMySQL;
import com.thalasoft.toolbox.condition.EnvTest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@EnvTest
@DbMySQL
@Component
@PropertySource({ "classpath:mysql/data-source-test.properties", "classpath:mysql/flyway.properties" })
public class DatabaseMySQLTestProperties extends AbstractDatabaseProperties {

  private static Logger logger = LoggerFactory.getLogger(DatabaseMySQLTestProperties.class);

  public DatabaseMySQLTestProperties() {
    logger.debug("Loading the MySQL Test properties file");
  }

}
