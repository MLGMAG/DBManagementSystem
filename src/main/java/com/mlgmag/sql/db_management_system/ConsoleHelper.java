package com.mlgmag.sql.db_management_system;

import com.mlgmag.sql.db_management_system.service.DatabaseConnectionService;

import java.util.Scanner;

class ConsoleHelper extends CRUD_DAO {

    ConsoleHelper() {

        System.out.println(
                "Hello, I'm ConsoleHelper;\n" +
                        "Welcome to ProjectManagementSystem;\n" +
                        "Choose Operation:");

        Menu();
    }

    private void Menu() {
        try {
            Scanner scan = new Scanner(System.in);

            while (true) {

                System.out.println(
                        "Menu:\n" +
                                "1.Create;\n" +
                                "2.Read;\n" +
                                "3.Update;\n" +
                                "4.Delete;\n" +
                                "5.Menu;\n" +
                                "6.FAQ Connection;\n" +
                                "0.Exit;");

                int a = scan.nextInt();

                switch (a) {

                    case 0:
                        System.exit(0);
                        break;

                    case 1:
                        create();
                        break;

                    case 2:
                        read();
                        break;

                    case 3:
                        update();
                        break;

                    case 4:
                        delete();
                        break;

                    case 5:
                        Menu();
                        break;

                    case 6:
                        DatabaseConnectionService databaseConnectionService = DatabaseConnectionService.getInstance();
                        System.out.println((char) 27 + "[33mURL:" + (char) 27 + "[0m" + databaseConnectionService.getURL());
                        System.out.print("Status:");
                        databaseConnectionService.logConnectionStatus();
                        break;

                    default:
                        System.out.println("Menu doesn't contain item with number:" + a);
                        Menu();
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Error:" + e);
            Menu();
        }
    }

}
