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

    public boolean isIdentical(String val1, String val2) {
        if (val1 == null || val2 == null || !val1.equals(val2)) {
            System.out.println("Not identical");
            return false;
        }
        return true;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String pass1 = req.getParameter("password1");
        String pass2 = req.getParameter("password2");
        String email1 = req.getParameter("email1");
        String email2 = req.getParameter("email2");

        resp.setContentType("text/html;charset=utf-8");

        try {
            UserDataSet userSet = DBService.getInstance().getUserByName(login);
            if (userSet != null ) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.sendRedirect("signup.html");
                System.out.println("User name is already exist");
                return;
            }
            if (!isIdentical(pass1, pass2) || !isIdentical(email1, email2)) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.sendRedirect("signup.html");
                return;
            }
            DBService.getInstance().addUser(login, pass1, email1);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println("Successfully registered");
        } catch (DBException e) {
            e.printStackTrace();
        }


    }
}
