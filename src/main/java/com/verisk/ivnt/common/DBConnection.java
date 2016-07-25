package com.verisk.ivnt.common;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DBConnection {
	private static final String DB_URL = "jdbc:oracle:thin:@(DESCRIPTION=(LOAD_BALANCE=yes)(FAILOVER=ON)(ADDRESS=(PROTOCOL=TCP)(HOST=192.168.72.117)(PORT=1521))(CONNECT_DATA=(SERVER=DEDICATED)(SERVICE_NAME=d2he1)))";
	private static final String PASS = "oracle";
	private static final String USER = "I82742";

	private DriverManagerDataSource datasource;

	public NamedParameterJdbcTemplate getJdbcTemplate() {
		datasource = new DriverManagerDataSource(DB_URL, USER, PASS);
		return new NamedParameterJdbcTemplate(datasource);
	}
}
