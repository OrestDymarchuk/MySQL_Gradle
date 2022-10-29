package mysql_gradle.data_base_services;

import mysql_gradle.data_base.DataBase;
import mysql_gradle.preferences.Preferences;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DatabaseInitService {

    public void initDB(DataBase dataBase){
        try {
            String initDbFilename = new Preferences().getString(Preferences.INIT_DB_SQL_PATH);

            String sql = String.join(
                    "\n",
                    Files.readAllLines(Paths.get(initDbFilename))
            );

            System.out.println(sql);

            dataBase.executeUpdate(sql);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DataBase dataBase = DataBase.getInstance();
        new DatabaseInitService().initDB(dataBase);
    }
}
