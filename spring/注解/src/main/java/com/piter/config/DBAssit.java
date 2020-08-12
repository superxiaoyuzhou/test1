package com.piter.config;

import org.springframework.stereotype.Component;

import java.sql.Connection;

@Component("dbAssit")
public class DBAssit {
    public Connection getCurrentConnection() {
        return null;
    }

    public void releaseConnection() {

    }
}
