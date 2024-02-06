package com.example.reskin.connPool;

import java.sql.Connection;
import java.sql.SQLException;

public interface connectionImplementor {
    public Connection getConnection() throws SQLException;
}
