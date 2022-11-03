package mysql_gradle.data_base_services;

import mysql_gradle.constants_classes.DataBaseInitConstants;
import mysql_gradle.data_base.DataBase;
import mysql_gradle.preferences.Preferences;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DatabaseInitService {

    private static final Connection CONNECTION = DataBase.getInstance().getConnection();

    public void createTableWorker() {
        String createTableWorkerSql = """
                    CREATE TABLE IF NOT EXISTS worker(
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(?) CONSTRAINT worker_name_ck CHECK
                         (LENGTH(name) >= ?) NOT NULL,
                         birthday DATE CONSTRAINT worker_birthdate_ck CHECK
                         (birthday > ?),
                         level VARCHAR(?) CONSTRAINT level_ck CHECK
                         (level IN (?, ?, ?, ?)) NOT NULL,
                         salary INT CONSTRAINT worker_salary_ck CHECK
                         (salary >= ? AND salary <= ?)
                         );""";

        try (PreparedStatement createWorkerTableSt =
                     CONNECTION.prepareStatement(createTableWorkerSql)){
            createWorkerTableSt.setInt(1,
                    DataBaseInitConstants.MAX_NAME_LENGTH);
            createWorkerTableSt.setInt(2,
                    DataBaseInitConstants.MIN_NAME_LENGTH);
            createWorkerTableSt.setString(3,
                    DataBaseInitConstants.MIN_BIRTH_DATE);
            createWorkerTableSt.setInt(4,
                    DataBaseInitConstants.MIN_LEVEL_LENGTH);
            createWorkerTableSt.setString(5,
                    DataBaseInitConstants.TRAINEE_CONSTRAINT);
            createWorkerTableSt.setString(6,
                    DataBaseInitConstants.JUNIOR_CONSTRAINT);
            createWorkerTableSt.setString(7,
                    DataBaseInitConstants.MIDDLE_CONSTRAINT);
            createWorkerTableSt.setString(8,
                    DataBaseInitConstants.SENIOR_CONSTRAINT);
            createWorkerTableSt.setInt(9,
                    DataBaseInitConstants.MIN_SALARY);
            createWorkerTableSt.setInt(10,
                    DataBaseInitConstants.MAX_SALARY);

            createWorkerTableSt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTableClient() {
        String createClientTableSql = """
                    CREATE TABLE IF NOT EXISTS client(
                    \tid INT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(?) CONSTRAINT client_name_ck CHECK
                        (LENGTH(name) >= ?) NOT NULL
                        );""";
        try (PreparedStatement createClientTableSt =
                     CONNECTION.prepareStatement(createClientTableSql)){
            createClientTableSt.setInt(1,
                    DataBaseInitConstants.MAX_NAME_LENGTH);
            createClientTableSt.setInt(2,
                    DataBaseInitConstants.MIN_NAME_LENGTH);

            createClientTableSt.executeUpdate();

        } catch (SQLException e) {
        e.printStackTrace();
        }
    }

    public void createTableProject() {
        String createProjectTableSql = """
                CREATE TABLE IF NOT EXISTS project(
                \tid INT AUTO_INCREMENT PRIMARY KEY,
                    client_id INT,
                    start_date DATE,
                    finish_date DATE
                    );""";

        try (PreparedStatement createProjectTableSt =
                     CONNECTION.prepareStatement(createProjectTableSql)){

            createProjectTableSt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTableProjectWorker() {
        String createProjectWorkerTableSql = """
                CREATE TABLE IF NOT EXISTS project_worker(
                \tproject_id INT,
                    worker_id INT,
                    PRIMARY KEY (project_id, worker_id),
                    FOREIGN KEY(project_id) REFERENCES project(id),
                    FOREIGN KEY(worker_id) REFERENCES worker(id)
                    );""";
        try (PreparedStatement createProjectWorkerTableSt =
                     CONNECTION.prepareStatement(createProjectWorkerTableSql)){

            createProjectWorkerTableSt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        DatabaseInitService databaseInitService = new DatabaseInitService();
        databaseInitService.createTableWorker();
        databaseInitService.createTableClient();
        databaseInitService.createTableProject();
        databaseInitService.createTableProjectWorker();
    }
}
