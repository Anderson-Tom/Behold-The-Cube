package servlets;

import db_service.DBException;
import db_service.DBService;
import db_service.data_sets.UserDataSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author IvanLis
 * @version 1.0
 * @since 26/12/2015.
 * <p/>
 * version2
 */
public class SignInServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("password");
        resp.setContentType("text/html;charset=utf-8");


        try {
            UserDataSet userSet = DBService.getInstance().getUserByName(login);
            System.out.println("Pass in db:" + userSet.getPass());
            System.out.println("Pass in the form:" + pass);
            if (userSet.getPass().equals(pass)) {
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().println("Authorized");
            } else {
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                resp.getWriter().println("Unauthorized");
            }

        } catch (DBException e) {
            e.printStackTrace();
        }

    }
}
