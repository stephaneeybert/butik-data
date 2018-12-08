package com.thalasoft.butik.data.config.properties;

import com.thalasoft.toolbox.condition.DbOracle;
import com.thalasoft.toolbox.condition.EnvPreProd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@EnvPreProd
@DbOracle
@Component
@PropertySource({ "classpath:oracle/data-source-preprod.properties", "classpath:oracle/flyway.properties" })
public class DatabaseOraclePreProdProperties extends AbstractDatabaseProperties {

	private static Logger logger = LoggerFactory.getLogger(DatabaseOraclePreProdProperties.class);

	public DatabaseOraclePreProdProperties() {
		logger.debug("Loading the Oracle Preprod properties file");
	}

}
