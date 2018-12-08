package com.thalasoft.butik.data.config.properties;

import com.thalasoft.toolbox.condition.DbMySQL;
import com.thalasoft.toolbox.condition.EnvPreProd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@EnvPreProd
@DbMySQL
@Component
@PropertySource({ "classpath:mysql/data-source-preprod.properties", "classpath:mysql/flyway.properties" })
public class DatabaseMySQLPreProdProperties extends AbstractDatabaseProperties {

	private static Logger logger = LoggerFactory.getLogger(DatabaseMySQLPreProdProperties.class);

	public DatabaseMySQLPreProdProperties() {
		logger.debug("Loading the MySQL Preprod properties file");
	}
	
}
