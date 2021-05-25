package com.ekros.library.model.dao.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DBCPDataSource {

    private static final BasicDataSource ds = new BasicDataSource();
    private static final Logger log = LogManager.getLogger(DBCPDataSource.class);
    static {
        try (InputStream input = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream("properties/db.properties")) {

        Properties prop = new Properties();
        prop.load(input);

        ds.setUrl(prop.getProperty("connection.url"));
        ds.setMinIdle(5);
        ds.setMaxIdle(10);
        ds.setMaxOpenPreparedStatements(100);
        } catch (IOException e) {
            log.info(e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    private DBCPDataSource(){ }
}
