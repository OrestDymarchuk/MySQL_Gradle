package mysql_gradle.client_service;

import mysql_gradle.data_base.DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientPreparedStatement {
    private PreparedStatement createClientSt;
    private PreparedStatement selectMaxClientIdSt;
    private PreparedStatement selectClientByIdSt;

    private PreparedStatement selectClientByIdStV2;
    private PreparedStatement setNameByIdSt;
    private PreparedStatement deleteByIdSt;
    private PreparedStatement selectAllSt;

    public ClientPreparedStatement(DataBase dataBase){
        Connection conn = dataBase.getConnection();

        try {
            createClientSt = conn.prepareStatement(
                    "INSERT INTO client (name) VALUES (?);");

            selectMaxClientIdSt = conn.prepareStatement(
                    "SELECT MAX(id) AS maxId from client;"
            );

            selectClientByIdSt = conn.prepareStatement(
                    "SELECT name FROM client where id = ?"
            );

            setNameByIdSt = conn.prepareStatement(
                    "UPDATE client SET name = ? WHERE id = ?;"
            );

            selectClientByIdStV2 = conn.prepareStatement(
                    "SELECT id FROM client where id = ?"
            );

            deleteByIdSt = conn.prepareStatement(
                    "DELETE from client where id = ?;"
            );

            selectAllSt = conn.prepareStatement(
                    "SELECT id, name FROM client;"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public long create(String name) {
        if (name.length() < 2 || name.length() >= 100) {
            System.out.println("A new client cannot be created due to the restrictions, " +
                    "the name must be at least 2 characters and no more than 100 characters, " +
                    "and the name length of the provided client is: "+ name.length());
            return -1;

        } else {
            try {
                createClientSt.setString(1, name);
                createClientSt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            long id = -1;

            try (ResultSet rs = selectMaxClientIdSt.executeQuery()) {
                rs.next();
                id = rs.getLong("maxId");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return id;
        }
    }

    public String getById(long id){
        try {
            selectClientByIdSt.setLong(1, id);
            selectClientByIdSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();
        try (ResultSet rs = selectClientByIdSt.executeQuery()){
            if(!rs.next()){
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

    public void setName(long id, String name){
        try {
            selectClientByIdStV2.setLong(1, id);
            selectClientByIdStV2.executeQuery();
            setNameByIdSt.setString(1, name);
            setNameByIdSt.setLong(2, id);
            setNameByIdSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (ResultSet rs = selectClientByIdStV2.executeQuery()){
            if(!rs.next()){
                System.out.println("The client name cannot be changed due to a non-existent client ID " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteById(long id){
        try {
            selectClientByIdStV2.setLong(1, id);
            selectClientByIdStV2.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (ResultSet rs = selectClientByIdStV2.executeQuery()){
            if(!rs.next()){
                System.out.println("The client cannot be deleted due to a non-existent client ID " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            deleteByIdSt.setLong(1, id);
            deleteByIdSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Client> listAll(){
        List<Client> clients = new ArrayList<>();

        try (ResultSet rs = selectAllSt.executeQuery()){
            while (rs.next()){
                clients.add(new Client(
                        rs.getLong("id"),
                        rs.getString("name"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clients;
    }
}
