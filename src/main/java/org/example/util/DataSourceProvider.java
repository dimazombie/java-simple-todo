package org.example.util;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DataSourceProvider {

    private DataSource ds;

    public DataSourceProvider() {
        try {
            InitialContext ic = new InitialContext();
            ds = (DataSource)ic.lookup("java:comp/env/jdbc/ds");
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

    public DataSource provide() {
        return ds;
    }
}
