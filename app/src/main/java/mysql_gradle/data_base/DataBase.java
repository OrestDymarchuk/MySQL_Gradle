package mysql_gradle.data_base;

import mysql_gradle.preferences.Preferences;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {
    private static final DataBase instance = new DataBase();
    private Connection connection;

    private DataBase() {
        try {
            String connectionUrl = new Preferences().getString(Preferences.DB_URL);
            String user = new Preferences().getString(Preferences.USER);
            String password = new Preferences().getString(Preferences.PASSWORD);

            connection = DriverManager.getConnection(connectionUrl, user, password);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int executeUpdate(String sql){
        try(Statement st = connection.createStatement()) {
            return st.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static DataBase getInstance(){
        return instance;
    }

    public Connection getConnection(){
        return connection;
    }

    public void close(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
