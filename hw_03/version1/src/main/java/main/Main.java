package main;

import com.sun.istack.internal.NotNull;
import db_service.DBException;
import db_service.DBService;
import db_service.data_sets.UserDataSet;

/**
 * @author IvanLis
 * @version 1.0
 * @since 25/12/2015.
 * <p/>
 * hw-03
 */
public class Main {

    public static void main(String[] args) {
        DBService dbService = new DBService();
        dbService.printConnectionInfo();
        try {
            long userId = dbService.addUser("Ivan");
            System.out.println("Added user id: " + userId);

            UserDataSet ds = dbService.getUser(userId);
            System.out.println("User data set: " + ds);

            dbService.cleanUp();
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}
