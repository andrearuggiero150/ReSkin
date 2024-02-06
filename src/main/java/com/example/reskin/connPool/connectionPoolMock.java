package com.example.reskin.connPool;

import java.sql.Connection;
import java.sql.SQLException;

public class connectionPoolMock extends connectionPoolAbstraction {
    @Override
    public Connection setConnection() throws SQLException {
        imp = new connectionMock();
        return imp.getConnection();
    }
}
