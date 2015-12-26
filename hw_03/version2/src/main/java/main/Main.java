package main;

import db_service.DBException;
import db_service.DBService;
import db_service.data_sets.UserDataSet;

/**
 * @author IvanLis
 * @version 1.0
 * @since 26/12/2015.
 * <p/>
 * version2
 */
public class Main {

    public static void main(String[] args) {
        DBService dbService = new DBService();
        dbService.printConnectInfo();

        try {
            long userId = dbService.addUser("John", "qwerty");
            System.out.println("Added user id: " + userId);

            UserDataSet dataSet = dbService.getUser(userId);
            System.out.println("User data set: " + dataSet);

        } catch (DBException e) {
            e.printStackTrace();
        }
    }

}

