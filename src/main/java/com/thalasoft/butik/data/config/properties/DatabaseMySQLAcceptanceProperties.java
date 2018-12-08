package com.thalasoft.butik.data.config.properties;

import com.thalasoft.toolbox.condition.DbMySQL;
import com.thalasoft.toolbox.condition.EnvAcceptance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@EnvAcceptance
@DbMySQL
@Component
@PropertySource({ "classpath:mysql/data-source-acceptance.properties", "classpath:mysql/flyway.properties" })
public class DatabaseMySQLAcceptanceProperties extends AbstractDatabaseProperties {

	private static Logger logger = LoggerFactory.getLogger(DatabaseMySQLAcceptanceProperties.class);

	public DatabaseMySQLAcceptanceProperties() {
		logger.debug("Loading the MySQL Acceptance properties file");
	}

}
