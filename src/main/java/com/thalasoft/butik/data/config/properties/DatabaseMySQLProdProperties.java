package com.thalasoft.butik.data.config.properties;

import com.thalasoft.toolbox.condition.DbMySQL;
import com.thalasoft.toolbox.condition.EnvProd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@EnvProd
@DbMySQL
@Component
@PropertySource({ "classpath:mysql/data-source-prod.properties", "classpath:mysql/flyway.properties" })
public class DatabaseMySQLProdProperties extends AbstractDatabaseProperties {

	private static Logger logger = LoggerFactory.getLogger(DatabaseMySQLProdProperties.class);

	public DatabaseMySQLProdProperties() {
		logger.debug("Loading the MySQL Prod properties file");
	}

}
