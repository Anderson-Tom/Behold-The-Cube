package main;

import chat.WebSocketChatServlet;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * @author IvanLis
 * @version 1.0
 * @since 15/01/2016.
 * <p/>
 * chat
 */
public class Main {

    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.addServlet(new ServletHolder(new WebSocketChatServlet()), "/chat");

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(true);
        resource_handler.setResourceBase("src/main/resources/web");

        HandlerList handlerList = new HandlerList();
        handlerList.setHandlers(new Handler[]{resource_handler, context});

        server.setHandler(handlerList);
        System.out.println("Server started");
        server.join();
        server.start();
    }

}
