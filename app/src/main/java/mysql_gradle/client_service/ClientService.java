package mysql_gradle.client_service;

import mysql_gradle.data_base.DataBase;

public class ClientService {
    public static void clientPrint(ClientPreparedStatement clientPreparedStatement){
        clientPreparedStatement.listAll().forEach(el -> System.out.println("The client id(s): "
                + el.getId()
                + ", the client's name "
                + el.getName()
        ));
    }

    public static void main(String[] args) {
        DataBase dataBase = DataBase.getInstance();
        ClientPreparedStatement clientPreparedStatement = new ClientPreparedStatement(dataBase);

        // Print all clients
        clientPrint(clientPreparedStatement);

        // Creating client with violation
        System.out.println();
        System.out.println(clientPreparedStatement.create("a"));

        // Creating client without violation
        System.out.println();
        System.out.println(clientPreparedStatement.create("Bruce"));

        // Print non-existing client
        System.out.println();
        System.out.println(clientPreparedStatement.getById(100000));

        // Print existing client
        System.out.println();
        System.out.println(clientPreparedStatement.getById(5));

        // Set name to non-existing client
        System.out.println();
        clientPreparedStatement.setName(100000, "Karl");

        // Set name to existing client
        clientPreparedStatement.setName(5, "Nathan");

        // Delete non-existing client
        System.out.println();
        clientPreparedStatement.deleteById(10000);

        // Delete existing client
        clientPreparedStatement.deleteById(6);

        // Print all clients
        System.out.println();
        clientPrint(clientPreparedStatement);
    }
}
