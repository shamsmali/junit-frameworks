package com.razorthink.junit.beans;

import java.io.File;
import java.sql.*;

/**
 * Created by shams on 2/3/16.
 */
public class H2DBManager {

    private Connection connection;

    private H2DBManager() {
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/db1");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return this.connection;
    }

    public static H2DBManager getInstance() {
        return new H2DBManager();
    }

}
