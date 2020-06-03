package com.bookstore.repository;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.search.TablesDependencyHelper;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.ext.mysql.MySqlMetadataHandler;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseExportSample {
    public static void main(String[] args) throws Exception {
        // database connection
        Class driverClass = Class.forName("com.mysql.cj.jdbc.Driver");
        Connection jdbcConnection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/book_schema", "root", "root");
        IDatabaseConnection connection = new DatabaseConnection(jdbcConnection, "book_schema");
        //connection.getConfig().setProperty(DatabaseConfig.FEATURE_QUALIFIED_TABLE_NAMES, true);
        connection.getConfig().setProperty(DatabaseConfig.PROPERTY_FETCH_SIZE, 99999);
        connection.getConnection().setSchema("book_schema");
        connection.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());
        connection.getConfig().setProperty(DatabaseConfig.PROPERTY_METADATA_HANDLER, new MySqlMetadataHandler());

        // partial database export
        // QueryDataSet partialDataSet = new QueryDataSet(connection);
        //partialDataSet.addTable("publishers", "SELECT * FROM publishers");
        //partialDataSet.addTable("BAR");
        //FlatXmlDataSet.write(partialDataSet, new FileOutputStream("partial.xml"));

        // full database export
        IDataSet fullDataSet = connection.createDataSet();
        FlatXmlDataSet.write(fullDataSet, new FileOutputStream("e://full.xml"));

        // dependent tables database export: export table X and all tables that
        // have a PK which is a FK on X, in the right order for insertion
        String[] depTableNames = TablesDependencyHelper.getAllDependentTables(connection, "book_authors");
        IDataSet depDataset = connection.createDataSet(depTableNames);
        FlatXmlDataSet.write(depDataset, new FileOutputStream("e://dependents.xml"));

    }
}
