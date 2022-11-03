package mysql_gradle.data_base_services;

import mysql_gradle.constants_classes.DataBasePopulateConstants;
import mysql_gradle.data_base.DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabasePopulateService {
    private static final Connection CONNECTION = DataBase.getInstance().getConnection();

    public void insertDataIntoTableWorker(){
        String insertDataIntoTableWorkerSql = """
                INSERT INTO worker
                   (id, name, birthday, level, salary) VALUES
                   (1, 'Stan', '1970-03-04', ?, 8000),
                   (2, 'Roger', '1920-04-10', ?, 8000),
                   (3, 'Steve', '2001-06-08', ?, 4000),
                   (4, 'Klaus', '1973-01-18', ?, 5000),
                   (5, 'Hayley', '1996-10-28', ?, 4500),
                   (6, 'Barry', '2001-05-03', ?, 880),
                   (7, 'Francine', '1975-05-16', ?, 950),
                   (8, 'Jeff', '1995-08-02', ?, 450),
                   (10, 'Toshi', '2001-08-06', ?, 850),
                   (11, 'Snot', '2001-03-04', ?, 800)
                   ;""";
        try (PreparedStatement insertDataIntoTableWorkerSt =
                     CONNECTION.prepareStatement(insertDataIntoTableWorkerSql)){
            insertDataIntoTableWorkerSt.setString(1,
                    DataBasePopulateConstants.SENIOR_LEVEL);
            insertDataIntoTableWorkerSt.setString(2,
                    DataBasePopulateConstants.SENIOR_LEVEL);
            insertDataIntoTableWorkerSt.setString(3,
                    DataBasePopulateConstants.MIDDLE_LEVEL);
            insertDataIntoTableWorkerSt.setString(4,
                    DataBasePopulateConstants.MIDDLE_LEVEL);
            insertDataIntoTableWorkerSt.setString(5,
                    DataBasePopulateConstants.MIDDLE_LEVEL);
            insertDataIntoTableWorkerSt.setString(6,
                    DataBasePopulateConstants.JUNIOR_LEVEL);
            insertDataIntoTableWorkerSt.setString(7,
                    DataBasePopulateConstants.JUNIOR_LEVEL);
            insertDataIntoTableWorkerSt.setString(8,
                    DataBasePopulateConstants.JUNIOR_LEVEL);
            insertDataIntoTableWorkerSt.setString(9,
                    DataBasePopulateConstants.JUNIOR_LEVEL);
            insertDataIntoTableWorkerSt.setString(10,
                    DataBasePopulateConstants.JUNIOR_LEVEL);

            insertDataIntoTableWorkerSt.executeUpdate();

        } catch (SQLException e) {
        e.printStackTrace();
        }
    }

    public void insertDataIntoTableClient(){
        String insertDataIntoTableClientSql = """
                INSERT INTO client
                   (id, name) VALUES
                   (1, ?),
                   (2, ?),
                   (3, ?),
                   (4, ?),
                   (5, ?),
                   (6, ?)
                   ;""";
        try (PreparedStatement insertDataIntoTableClientSt =
                     CONNECTION.prepareStatement(insertDataIntoTableClientSql)){
            insertDataIntoTableClientSt.setString(1,
                    DataBasePopulateConstants.PETER_CLIENT_NAME);
            insertDataIntoTableClientSt.setString(2,
                    DataBasePopulateConstants.LOIS_CLIENT_NAME);
            insertDataIntoTableClientSt.setString(3,
                    DataBasePopulateConstants.Brian_CLIENT_NAME);
            insertDataIntoTableClientSt.setString(4,
                    DataBasePopulateConstants.STEWIE_CLIENT_NAME);
            insertDataIntoTableClientSt.setString(5,
                    DataBasePopulateConstants.CHRIS_CLIENT_NAME);
            insertDataIntoTableClientSt.setString(6,
                    DataBasePopulateConstants.MEG_CLIENT_NAME);

            insertDataIntoTableClientSt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertDataIntoTableProject(){
        String insertDataIntoTableClientSql = """
                INSERT INTO project
                   (id, client_id, start_date, finish_date) VALUES
                   (1, ?, '2019-06-06', '2020-04-08'),
                   (2, ?, '2020-09-21', '2021-01-04'),
                   (3, ?, '2022-08-16', '2022-12-11'),
                   (4, ?, '2021-07-14', '2022-02-02'),
                   (5, ?, '2020-02-01', '2020-03-02'),
                   (6, ?, '2022-10-05', '2022-12-16'),
                   (7, ?, '2018-04-24', '2019-01-16'),
                   (8, ?, '2022-04-08', '2022-07-09'),
                   (9, ?, '2020-03-18', '2020-08-18'),
                   (10, ?, '2022-05-17', '2022-06-18')
                   ;""";
        try (PreparedStatement insertDataIntoTableProjectSt =
                     CONNECTION.prepareStatement(insertDataIntoTableClientSql)){
            insertDataIntoTableProjectSt.setInt(1,
                    DataBasePopulateConstants.CLIENT_ID_ONE);
            insertDataIntoTableProjectSt.setInt(2,
                    DataBasePopulateConstants.CLIENT_ID_ONE);
            insertDataIntoTableProjectSt.setInt(3,
                    DataBasePopulateConstants.CLIENT_ID_TWO);
            insertDataIntoTableProjectSt.setInt(4,
                    DataBasePopulateConstants.CLIENT_ID_TWO);
            insertDataIntoTableProjectSt.setInt(5,
                    DataBasePopulateConstants.CLIENT_ID_THREE);
            insertDataIntoTableProjectSt.setInt(6,
                    DataBasePopulateConstants.CLIENT_ID_THREE);
            insertDataIntoTableProjectSt.setInt(7,
                    DataBasePopulateConstants.CLIENT_ID_THREE);
            insertDataIntoTableProjectSt.setInt(8,
                    DataBasePopulateConstants.CLIENT_ID_FOUR);
            insertDataIntoTableProjectSt.setInt(9,
                    DataBasePopulateConstants.CLIENT_ID_FIVE);
            insertDataIntoTableProjectSt.setInt(10,
                    DataBasePopulateConstants.CLIENT_ID_SIX);

            insertDataIntoTableProjectSt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertDataIntoTableProjectWorker(){
        String insertDataIntoTableClientSql = """
                INSERT INTO project_worker
                   (project_id, worker_id) VALUES
                   (?, 1),
                   (?, 3),
                   (?, 6),
                   (?, 7),
                   (?, 2),
                   (?, 4),
                   (?, 8),
                   (?, 10),
                   (?, 1),
                   (?, 5),
                   (?, 11),
                   (?, 2),
                   (?, 3),
                   (?, 6),
                   (?, 10),
                   (?, 11),
                   (?, 2),
                   (?, 3),
                   (?, 4),
                   (?, 5),
                   (?, 6),
                   (?, 7),
                   (?, 4),
                   (?, 7),
                   (?, 8),
                   (?, 4),
                   (?, 5),
                   (?, 2),
                   (?, 7),
                   (?, 8)
                   ;""";
        try (PreparedStatement insertDataIntoTableProjectWorkerSt =
                     CONNECTION.prepareStatement(insertDataIntoTableClientSql)){
            insertDataIntoTableProjectWorkerSt.setInt(1,
                    DataBasePopulateConstants.PROJECT_ID_ONE);
            insertDataIntoTableProjectWorkerSt.setInt(2,
                    DataBasePopulateConstants.PROJECT_ID_ONE);
            insertDataIntoTableProjectWorkerSt.setInt(3,
                    DataBasePopulateConstants.PROJECT_ID_ONE);
            insertDataIntoTableProjectWorkerSt.setInt(4,
                    DataBasePopulateConstants.PROJECT_ID_ONE);

            insertDataIntoTableProjectWorkerSt.setInt(5,
                    DataBasePopulateConstants.PROJECT_ID_TWO);
            insertDataIntoTableProjectWorkerSt.setInt(6,
                    DataBasePopulateConstants.PROJECT_ID_TWO);
            insertDataIntoTableProjectWorkerSt.setInt(7,
                    DataBasePopulateConstants.PROJECT_ID_TWO);
            insertDataIntoTableProjectWorkerSt.setInt(8,
                    DataBasePopulateConstants.PROJECT_ID_TWO);

            insertDataIntoTableProjectWorkerSt.setInt(9,
                    DataBasePopulateConstants.PROJECT_ID_THREE);
            insertDataIntoTableProjectWorkerSt.setInt(10,
                    DataBasePopulateConstants.PROJECT_ID_THREE);
            insertDataIntoTableProjectWorkerSt.setInt(11,
                    DataBasePopulateConstants.PROJECT_ID_THREE);

            insertDataIntoTableProjectWorkerSt.setInt(12,
                    DataBasePopulateConstants.PROJECT_ID_FOUR);
            insertDataIntoTableProjectWorkerSt.setInt(13,
                    DataBasePopulateConstants.PROJECT_ID_FOUR);
            insertDataIntoTableProjectWorkerSt.setInt(14,
                    DataBasePopulateConstants.PROJECT_ID_FOUR);

            insertDataIntoTableProjectWorkerSt.setInt(15,
                    DataBasePopulateConstants.PROJECT_ID_FIVE);
            insertDataIntoTableProjectWorkerSt.setInt(16,
                    DataBasePopulateConstants.PROJECT_ID_FIVE);

            insertDataIntoTableProjectWorkerSt.setInt(17,
                    DataBasePopulateConstants.PROJECT_ID_SIX);

            insertDataIntoTableProjectWorkerSt.setInt(18,
                    DataBasePopulateConstants.PROJECT_ID_SEVEN);
            insertDataIntoTableProjectWorkerSt.setInt(19,
                    DataBasePopulateConstants.PROJECT_ID_SEVEN);
            insertDataIntoTableProjectWorkerSt.setInt(20,
                    DataBasePopulateConstants.PROJECT_ID_SEVEN);
            insertDataIntoTableProjectWorkerSt.setInt(21,
                    DataBasePopulateConstants.PROJECT_ID_SEVEN);
            insertDataIntoTableProjectWorkerSt.setInt(22,
                    DataBasePopulateConstants.PROJECT_ID_SEVEN);

            insertDataIntoTableProjectWorkerSt.setInt(23,
                    DataBasePopulateConstants.PROJECT_ID_EIGHT);
            insertDataIntoTableProjectWorkerSt.setInt(24,
                    DataBasePopulateConstants.PROJECT_ID_EIGHT);
            insertDataIntoTableProjectWorkerSt.setInt(25,
                    DataBasePopulateConstants.PROJECT_ID_EIGHT);

            insertDataIntoTableProjectWorkerSt.setInt(26,
                    DataBasePopulateConstants.PROJECT_ID_NINE);
            insertDataIntoTableProjectWorkerSt.setInt(27,
                    DataBasePopulateConstants.PROJECT_ID_NINE);

            insertDataIntoTableProjectWorkerSt.setInt(28,
                    DataBasePopulateConstants.PROJECT_ID_TEN);
            insertDataIntoTableProjectWorkerSt.setInt(29,
                    DataBasePopulateConstants.PROJECT_ID_TEN);
            insertDataIntoTableProjectWorkerSt.setInt(30,
                    DataBasePopulateConstants.PROJECT_ID_TEN);

            insertDataIntoTableProjectWorkerSt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        DatabasePopulateService databasePopulateService = new DatabasePopulateService();
        databasePopulateService.insertDataIntoTableWorker();
        databasePopulateService.insertDataIntoTableClient();
        databasePopulateService.insertDataIntoTableProject();
        databasePopulateService.insertDataIntoTableProjectWorker();
    }
}
