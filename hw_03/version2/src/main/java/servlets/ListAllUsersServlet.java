package servlets;

import db_service.DBException;
import db_service.DBService;
import db_service.data_sets.UserDataSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author IvanLis
 * @version 1.0
 * @since 27/12/2015.
 * <p/>
 * version2
 */
public class ListAllUsersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<UserDataSet> users = DBService.getInstance().getAllUsers();
            users.forEach(System.out::println);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}
