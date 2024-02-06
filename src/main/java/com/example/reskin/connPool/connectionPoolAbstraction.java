package com.example.reskin.connPool;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class connectionPoolAbstraction {
    protected connectionImplementor imp;
    public abstract Connection setConnection() throws SQLException;
}
