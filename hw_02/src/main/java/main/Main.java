package main;

import accounts.AccountService;
import accounts.UserProfile;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;
import servlets.SessionServlet;
import servlets.SignInServlet;
import servlets.SignUpServlet;
import servlets.UserServlet;

import java.net.MalformedURLException;

/**
 * @author IvanLis
 * @version 1.0
 * @since 19/12/2015.
 * <p/>
 * web-servers
 */
public class Main {

    public static void main(String[] args) throws Exception {

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
//        context.addServlet(new ServletHolder(new UserServlet()), "/users");
//        context.addServlet(new ServletHolder(new SessionServlet()), "/sessions");
        context.addServlet(new ServletHolder(new SignInServlet()), "/signin");
        context.addServlet(new ServletHolder(new SignUpServlet()), "/signup");
        System.out.println("Server started");

        Resource theBaseResource = null;
        try{
            theBaseResource = Resource.newResource( "home" );
        }
        catch( MalformedURLException e ){
            System.err.println( "setup failed on newResource with the exception " + e.toString() );
            System.exit(0);
        }

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setBaseResource(theBaseResource);

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});

        Server server = new Server(8080);
        server.setHandler(handlers);

        server.start();
        server.join();
    }
}
