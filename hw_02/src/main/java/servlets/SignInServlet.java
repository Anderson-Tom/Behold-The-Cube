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
public class SignInServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        resp.setContentType("text/html;charset=utf-8");

        UserProfile profile = AccountService.getInstance().getUserByLogin(login);

        if (profile == null || !profile.getPass().equals(password)) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.getWriter().println("Unauthorized");
            return;
        }
        else {
            AccountService.getInstance().addSession(req.getSession().getId(), profile);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println("Authorized");
        }
    }
}