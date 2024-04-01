package org.example.webtechproj;

import dbconnection.DataSource;
import dbconnection.sqlStatement;

import java.sql.*;


public class PostgreSQLExample {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String SQL_QUERY = "select * from trial1";
        sqlStatement.executeQuery(SQL_QUERY);
    }
}