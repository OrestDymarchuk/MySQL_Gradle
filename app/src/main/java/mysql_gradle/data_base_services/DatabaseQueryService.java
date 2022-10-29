package mysql_gradle.data_base_services;

import mysql_gradle.data_base.DataBase;
import mysql_gradle.data_base_query_classes.*;
import mysql_gradle.preferences.Preferences;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {
    private static final Preferences preferences = new Preferences();

    List<MaxSalaryWorker> maxSalaryWorker(DataBase dataBase) {
        List<MaxSalaryWorker> maxSalaryWorkers = new ArrayList<>();

        try {
            String initDbFilename = preferences.getString(Preferences.MAX_SALARY_WORKER_DB_SQL_PATH);

            String sql = String.join(
                    "\n",
                    Files.readAllLines(Paths.get(initDbFilename))
            );

            Statement st = dataBase.getConnection().createStatement();
            ResultSet resultSet = st.executeQuery(sql);

            while (resultSet.next()) {
                maxSalaryWorkers.add(new MaxSalaryWorker(
                        resultSet.getString("name"),
                        resultSet.getString("salary")
                ));
            }
            st.close();

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return maxSalaryWorkers;
    }

    List<MaxProjectsClient> findMaxProjectsClient(DataBase dataBase) {
        List<MaxProjectsClient> maxProjectsClients = new ArrayList<>();

        try {
            String initDbFilename = preferences.getString(Preferences.MAX_PROJECTS_CLIENT_DB_SQL_PATH);

            String sql = String.join(
                    "\n",
                    Files.readAllLines(Paths.get(initDbFilename))
            );

            Statement st = dataBase.getConnection().createStatement();
            ResultSet resultSet = st.executeQuery(sql);

            while (resultSet.next()) {
                maxProjectsClients.add(new MaxProjectsClient(
                        resultSet.getString("name"),
                        resultSet.getString("project_count")
                ));
            }
            st.close();

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return maxProjectsClients;
    }

    List<LongestProject> findLongestProject(DataBase dataBase) {
        List<LongestProject> longestProjects = new ArrayList<>();

        try {
            String initDbFilename = preferences.getString(Preferences.LONGEST_PROJECT_SQL_PATH);

            String sql = String.join(
                    "\n",
                    Files.readAllLines(Paths.get(initDbFilename))
            );

            Statement st = dataBase.getConnection().createStatement();
            ResultSet resultSet = st.executeQuery(sql);

            while (resultSet.next()) {
                longestProjects.add(new LongestProject(
                        resultSet.getString("Project_ID"),
                        resultSet.getString("month_count")
                ));
            }
            st.close();

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return longestProjects;
    }

    List<YoungestEldestWorkers> findYoungestEldestWorkers(DataBase dataBase) {
        List<YoungestEldestWorkers> youngestEldestWorkers = new ArrayList<>();

        try {
            String initDbFilename = preferences.getString(Preferences.YOUNGEST_ELDEST_WORKERS);

            String sql = String.join(
                    "\n",
                    Files.readAllLines(Paths.get(initDbFilename))
            );

            Statement st = dataBase.getConnection().createStatement();
            ResultSet resultSet = st.executeQuery(sql);

            while (resultSet.next()) {
                youngestEldestWorkers.add(new YoungestEldestWorkers(
                        resultSet.getString("name"),
                        resultSet.getString("birthday"),
                        resultSet.getString("type")
                ));
            }
            st.close();

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return youngestEldestWorkers;
    }

    List<ProjectPrices> findProjectPrices(DataBase dataBase) {
        List<ProjectPrices> projectPrices = new ArrayList<>();

        try {
            String initDbFilename = preferences.getString(Preferences.PROJECT_PRICES);

            String sql = String.join(
                    "\n",
                    Files.readAllLines(Paths.get(initDbFilename))
            );

            Statement st = dataBase.getConnection().createStatement();
            ResultSet resultSet = st.executeQuery(sql);

            while (resultSet.next()) {
                projectPrices.add(new ProjectPrices(
                        resultSet.getString("project_id"),
                        resultSet.getString("price")
                ));
            }
            st.close();

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return projectPrices;
    }

    public static void main(String[] args) {
        DataBase dataBase = DataBase.getInstance();
        new DatabaseQueryPrinter().queryPrinter(dataBase);

    }
}
