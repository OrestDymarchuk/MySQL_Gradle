package mysql_gradle.client_service;

import mysql_gradle.data_base.DataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientService {

    private PreparedStatement createClientStatement;
    private PreparedStatement selectClientByIdStatement;
    private PreparedStatement setNameByIdStatement;
    private PreparedStatement deleteByIdStatement;
    private PreparedStatement selectAllClientsStatement;

    public ClientService(DataBase dataBase) {
        Connection conn = dataBase.getConnection();

        try {
            createClientStatement = conn.prepareStatement(
                    "INSERT INTO client (name) VALUES (?);",
                    Statement.RETURN_GENERATED_KEYS
            );

            selectClientByIdStatement = conn.prepareStatement(
                    "SELECT id, name FROM client where id = ?"
            );

            setNameByIdStatement = conn.prepareStatement(
                    "UPDATE client SET name = ? WHERE id = ?;"
            );

            deleteByIdStatement = conn.prepareStatement(
                    "DELETE from client where id = ?;"
            );

            selectAllClientsStatement = conn.prepareStatement(
                    "SELECT id, name FROM client;"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public long create(String name) {
        long id = -1;
        if (name.length() < 2 || name.length() >= 100) {
            System.out.println("A new client cannot be created due to the restrictions, " +
                    "the name must be at least 2 characters and no more than 100 characters, " +
                    "and the name length of the provided client is: " + name.length());

        } else {
            try {
                createClientStatement.setString(1, name);
                createClientStatement.executeUpdate();
                ResultSet rs = createClientStatement.getGeneratedKeys();
                if (rs.next()) {
                    id = rs.getLong(1);
                }
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return id;
    }

    public String getById(long id) {
        try {
            selectClientByIdStatement.setLong(1, id);
            selectClientByIdStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();
        try (ResultSet rs = selectClientByIdStatement.executeQuery()) {
            if (!rs.next()) {
                sb.append("The client with ID ")
                        .append(id)
                        .append(" does not exist");
            } else {
                sb.append(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public void setName(long id, String name) {
        try {
            selectClientByIdStatement.setLong(1, id);
            selectClientByIdStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (ResultSet rs = selectClientByIdStatement.executeQuery()) {
            if (!rs.next()) {
                System.out.println("The client name cannot be changed due to a non-existent client ID " + id);
            } else {
                setNameByIdStatement.setString(1, name);
                setNameByIdStatement.setLong(2, id);
                setNameByIdStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(long id) {
        try {
            selectClientByIdStatement.setLong(1, id);
            selectClientByIdStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (ResultSet rs = selectClientByIdStatement.executeQuery()) {
            if (!rs.next()) {
                System.out.println("The client cannot be deleted due to a non-existent client ID " + id);
            } else {
                deleteByIdStatement.setLong(1, id);
                deleteByIdStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Client> listAll() {
        List<Client> clients = new ArrayList<>();

        try (ResultSet rs = selectAllClientsStatement.executeQuery()) {
            while (rs.next()) {
                clients.add(new Client(
                        rs.getLong("id"),
                        rs.getString("name"))
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(clients.isEmpty()){
            System.out.println("No client has been found");
        }
        return clients;
    }
}