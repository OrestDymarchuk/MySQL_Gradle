package mysql_gradle.data_base_services;

import mysql_gradle.data_base.DataBase;
import mysql_gradle.preferences.Preferences;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DatabaseInitService {

    public void initDB(DataBase dataBase) {
        try {
            BufferedReader reader = new BufferedReader(
                    new FileReader(new Preferences().
                            getString(Preferences.INIT_DB_SQL_PATH)));

            StringBuilder stringBuilder = new StringBuilder();
            String s = null;
            List<String> collect = new ArrayList<>();

            while ((s = reader.readLine()) != null) {
                collect = Arrays.stream(
                                stringBuilder.
                                        append(s).
                                        append("\n").
                                        toString().
                                        split(";"))
                        .toList();
            }

            for (String sql : collect) {
                dataBase.executeUpdate(sql);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DataBase dataBase = DataBase.getInstance();
        new DatabaseInitService().initDB(dataBase);
        dataBase.close();
    }
}
