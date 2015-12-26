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
public class SignUpServlet extends HttpServlet {

    public boolean isPasswordValid(String pass1, String pass2) {
        if (pass1 == null || pass2 == null || !pass1.equals(pass2)) {
            System.out.println("Password does not match");
            return false;
        }
        return true;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String pass1 = req.getParameter("password1");
        String pass2 = req.getParameter("password2");
        resp.setContentType("text/html;charset=utf-8");

        try {
            UserDataSet userSet = DBService.getInstance().getUserByName(login);
            if (userSet != null ) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.sendRedirect("signup.html");
                System.out.println("User name is already exist");
                return;
            }
            if (!isPasswordValid(pass1, pass2)) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.sendRedirect("signup.html");
                return;
            }
            DBService.getInstance().addUser(login, pass1);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println("Successfully registered");
        } catch (DBException e) {
            e.printStackTrace();
        }


    }
}
