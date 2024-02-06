package com.example.reskin.connPool;

import java.sql.Connection;
import java.sql.SQLException;

public class connectionPoolReal extends connectionPoolAbstraction {
    @Override
    public Connection setConnection() throws SQLException {
        imp = new connectionReal();
        return imp.getConnection();
    }
}
