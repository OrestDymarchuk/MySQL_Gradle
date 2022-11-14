package mysql_gradle.data_base_flyway_service;

import mysql_gradle.preferences.Preferences;
import org.flywaydb.core.Flyway;

public class DatabaseFlyWayService {

    public void initFlyWayDb() {
        String connectionUrl = new Preferences().getString(Preferences.DB_URL);
        String user = new Preferences().getString(Preferences.USER);
        String password = new Preferences().getString(Preferences.PASSWORD);

        Flyway flyway = Flyway.configure()
                .dataSource(connectionUrl, user, password)
                .load();

        flyway.migrate();
    }

    public static void main(String[] args) {
        new DatabaseFlyWayService().initFlyWayDb();
    }
}