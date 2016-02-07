package servlets;

import accounts.AccountService;
import accounts.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author IvanLis
 * @version 1.0
 * @since 20/12/2015.
 * <p/>
 * hw_02
 */
public class SignUpServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        resp.setContentType("text/html;charset=utf-8");
        if (password == null || login == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        UserProfile profile = new UserProfile(login, password);
        AccountService.getInstance().addNewUser(profile);
        resp.setStatus(HttpServletResponse.SC_OK);

    }
}
