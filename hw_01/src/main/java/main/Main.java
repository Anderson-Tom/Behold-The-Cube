package main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.AllRequestsServlet;
import servlets.ServletTask1;

/**
 * @author IvanLis
 * @version 1.0
 * @since 11/12/2015.
 * <p/>
 * web-server
 */
public class Main {
    public static void main(String[] args) throws Exception{
        AllRequestsServlet allRequestsServlet = new AllRequestsServlet();
        ServletTask1 servlet = new ServletTask1();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
//        context.addServlet(new ServletHolder(allRequestsServlet), "/*");
        context.addServlet(new ServletHolder(servlet), "/mirror");
        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
        System.out.println("Server started");
        server.join();

    }
}
