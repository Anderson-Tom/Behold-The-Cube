package main;

import accounts.AccountService;
import accounts.UserProfile;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.SessionServlet;
import servlets.UserServlet;

/**
 * @author IvanLis
 * @version 1.0
 * @since 19/12/2015.
 * <p/>
 * web-servers
 */
public class Main {

    public static void main(String[] args) throws Exception {
        AccountService accountService = new AccountService();
        accountService.addNewUser(new UserProfile("admin"));

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new UserServlet(accountService)), "api/users");
        context.addServlet(new ServletHolder(new SessionServlet(accountService)), "api/sessions");

        ResourceHandler handler = new ResourceHandler();
        handler.setResourceBase("public_html");

        HandlerList handlerList = new HandlerList();
        handlerList.setHandlers(new Handler[]{handler, context});

        Server server = new Server(8080);
        server.setHandler(handlerList);
        server.start();
        server.join();
    }
}
