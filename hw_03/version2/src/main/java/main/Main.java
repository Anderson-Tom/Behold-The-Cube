package main;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;
import servlets.ListAllUsersServlet;
import servlets.SignInServlet;
import servlets.SignUpServlet;

import java.net.MalformedURLException;

/**
 * @author IvanLis
 * @version 1.0
 * @since 26/12/2015.
 * <p/>
 * version2
 */
public class Main {

    public static void main(String[] args) throws Exception {


        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.addServlet(new ServletHolder(new SignInServlet()), "/signin");
        contextHandler.addServlet(new ServletHolder(new SignUpServlet()), "/signup");
        contextHandler.addServlet(new ServletHolder(new ListAllUsersServlet()), "/listall");
        Resource theBaseResource = null;
        try {
            theBaseResource = Resource.newResource("src/main/resources");
        } catch (MalformedURLException e) {
            System.err.println("setup failed on newResource with the exception " + e.toString());
            System.exit(0);
        }
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setBaseResource(theBaseResource);

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resourceHandler, contextHandler});
        System.out.println("Server started");
        Server server = new Server(8080);
        server.setHandler(handlers);

        server.start();
        try {
            server.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}

