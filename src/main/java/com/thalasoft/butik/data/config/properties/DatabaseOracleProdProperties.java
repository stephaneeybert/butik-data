package com.thalasoft.butik.data.config.properties;

import com.thalasoft.toolbox.condition.DbOracle;
import com.thalasoft.toolbox.condition.EnvProd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@EnvProd
@DbOracle
@Component
@PropertySource({ "classpath:oracle/data-source-prod.properties", "classpath:oracle/flyway.properties" })
public class DatabaseOracleProdProperties extends AbstractDatabaseProperties {

	private static Logger logger = LoggerFactory.getLogger(DatabaseOracleProdProperties.class);

	public DatabaseOracleProdProperties() {
		logger.debug("Loading the Oracle Prod properties file");
	}

}
