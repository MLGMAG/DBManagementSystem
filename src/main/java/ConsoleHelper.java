
import CRUD_Operations.*;

import java.util.Scanner;

/**
 *
 * Created by Mag on 17.08.2017.
 *
 */

class ConsoleHelper {

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
                        new Create();
                        break;

                    case 2:
                        new Read();
                        break;

                    case 3:
                        new Update();
                        break;

                    case 4:
                        new Delete();
                        break;

                    case 5:
                        Menu();
                        break;

                    case 6:
                        DataBaseConnection dataBaseConnection = new DataBaseConnection();
                        System.out.println((char) 27 + "[33mURL:" + (char) 27 + "[0m" + DataBaseConnection.getURL());
                        System.out.print("Status:");
                        dataBaseConnection.ConnectionStatus();
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
