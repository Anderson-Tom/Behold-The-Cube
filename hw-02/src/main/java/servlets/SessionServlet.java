package servlets;

import accounts.AccountService;
import accounts.UserProfile;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author IvanLis
 * @version 1.0
 * @since 19/12/2015.
 * <p/>
 * web-servers
 */
public class SessionServlet extends HttpServlet {
    private final AccountService accountService;

    public SessionServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    // get logged user profile as a Json
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sessionId = req.getSession().getId();
        UserProfile profile = accountService.getUserBySessionId(sessionId);
        resp.setContentType("text/html;charset=utf-8");
        if (profile == null) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        else {
            Gson gson = new Gson();
            String json = gson.toJson(profile);
            resp.getWriter().println(json);
            resp.setStatus(HttpServletResponse.SC_OK);

        }
    }

    // sign in
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("pass");

        resp.setContentType("text/html;charset=utf-8");

        if (pass == null || login == null) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        UserProfile profile = accountService.getUserByLogin(login);

        // if profile is not been created yet
        if (profile == null || !profile.getPass().equals(pass)) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        accountService.addSession(req.getSession().getId(), profile);
        Gson gson = new Gson();
        resp.getWriter().println(gson.toJson(profile));
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    // sign out
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sessionId = req.getSession().getId();
        UserProfile profile = accountService.getUserBySessionId(sessionId);
        resp.setContentType("text/html;charset=utf-8");
        if (profile == null) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        else {
            accountService.deleteSession(sessionId);
            resp.getWriter().println("See you later alligator!");
            resp.setStatus(HttpServletResponse.SC_OK);
        }
    }
}
