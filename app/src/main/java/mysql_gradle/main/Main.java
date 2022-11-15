package mysql_gradle.main;

import mysql_gradle.client_service.ClientService;
import mysql_gradle.data_base.DataBase;

public class Main {
    public static void clientPrint(ClientService clientService) {
        clientService.listAll().forEach(el -> System.out.println("The client id(s): "
                + el.getId()
                + ", the client's name "
                + el.getName()
        ));
    }

    public static void main(String[] args) {
        DataBase dataBase = DataBase.getInstance();
        ClientService clientService = new ClientService(dataBase);

        // Print all clients
        clientPrint(clientService);

        // Creating client with violation
        System.out.println();
        System.out.println(clientService.create("a"));

        // Creating client without violation
        System.out.println();
        System.out.println(clientService.create("Arthur"));

        // Print non-existing client
        System.out.println();
        System.out.println(clientService.getById(100000));

        // Print existing client
        System.out.println();
        System.out.println(clientService.getById(5));

        // Set name to non -existing client
        System.out.println();
        clientService.setName(100000, "Karl");

        // Set name to existing client
        clientService.setName(5, "Jason");

        // Delete non-existing client
        System.out.println();
        clientService.deleteById(10000);

        // Delete existing client
        clientService.deleteById(7);

        // Print all clients
        System.out.println();
        clientPrint(clientService);
    }
}

