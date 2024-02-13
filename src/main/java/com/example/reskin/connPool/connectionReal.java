package com.example.reskin.connPool;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.TimeZone;

public class connectionReal implements connectionImplementor {
    @Override
    public Connection getConnection() throws SQLException {
        DataSource datasource;
        PoolProperties p = new PoolProperties();
        p.setUrl("jdbc:mysql://localhost:3306/ReSkin?serverTimezone=" + TimeZone.getDefault().getID());
        p.setDriverClassName("com.mysql.cj.jdbc.Driver");
        p.setUsername("root");
        p.setPassword("andrea11");
        p.setMaxActive(100);
        p.setInitialSize(10);
        p.setMinIdle(10);
        p.setRemoveAbandonedTimeout(60);
        p.setRemoveAbandoned(true);
        datasource = new DataSource();
        datasource.setPoolProperties(p);
        return datasource.getConnection();
    }
}
