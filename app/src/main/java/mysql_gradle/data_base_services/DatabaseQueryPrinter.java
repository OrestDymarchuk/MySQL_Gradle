package mysql_gradle.data_base_services;

import mysql_gradle.data_base.DataBase;

public class DatabaseQueryPrinter {
    private static final DatabaseQueryService dataBaseQueryService = new DatabaseQueryService();

    public void queryPrinter(DataBase dataBase) {

        dataBaseQueryService.maxSalaryWorker(dataBase)
                .forEach(el -> System.out.println("Max salary worker(s): "
                        + el.getName()
                        + " has a salary of "
                        + el.getSalary()
                ));

        System.out.println("____________\n");

        dataBaseQueryService.findMaxProjectsClient(dataBase)
                .forEach(el -> System.out.println("Max projects client(s): "
                        + el.getName()
                        + " has "
                        + el.getProjectCount()
                        + " project(s)"
                ));

        System.out.println("____________\n");

        dataBaseQueryService.findLongestProject(dataBase)
                .forEach(el -> System.out.println("The longest project(s): id "
                        + el.getId()
                        + " lasts "
                        + el.getMonthCount()
                        + " month(s)"
                ));

        System.out.println("____________\n");

        dataBaseQueryService.findYoungestEldestWorkers(dataBase)
                .forEach(el -> System.out.println("The "
                        + el.getType()
                        + " is "
                        + el.getName()
                        + " was born on "
                        + el.getBirthday()
                ));

        System.out.println("____________\n");

        dataBaseQueryService.findProjectPrices(dataBase)
                .forEach(el -> System.out.println("The project " +
                        el.getProjectId()
                        + " costs "
                        + el.getPrice()
                ));
    }
}
