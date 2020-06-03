package com.bookstore.repository;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatDtdDataSet;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;

/*
 * NOTE:
 * If you get AmbiguousTableNameException then run following query to check
 * select * from information_schema.tables;
 * */

public class DatabaseExportSchema {

    public static void main(String[] args) throws Exception {
        // database connection
        Class driverClass = Class.forName("com.mysql.cj.jdbc.Driver");
        Connection jdbcConnection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/book_schema", "root", "root");
        IDatabaseConnection connection = new DatabaseConnection(jdbcConnection, "book_schema");
        connection.getConnection().setSchema("book_schema");
        //connection.getConfig().setProperty(DatabaseConfig.FEATURE_QUALIFIED_TABLE_NAMES, true);
        IDataSet dataSet = connection.createDataSet();
        System.out.println("==================\n" + connection.getSchema());


        // write DTD file
        FlatDtdDataSet.write(dataSet, new FileOutputStream("e://test.dtd"));
    }
}
