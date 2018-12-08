package com.thalasoft.butik.data.dialect;

import java.sql.Types;

import org.hibernate.dialect.Oracle10gDialect;

public class CustomOracle10gDialect extends Oracle10gDialect {

	public CustomOracle10gDialect() {
		super();
		registerColumnType(Types.LONGVARCHAR, "clob");
	    registerColumnType(Types.LONGNVARCHAR, "clob");
	    registerColumnType(Types.INTEGER, "number");
	    registerColumnType(Types.BIT, "number");
	    registerColumnType(Types.TIMESTAMP, "date");
	}

}
