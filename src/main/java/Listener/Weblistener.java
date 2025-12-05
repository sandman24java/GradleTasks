package Listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;



@WebListener
public class Weblistener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent event) {
        System.out.println("contextInitialized");
    }
    public void contextDestroyed(ServletContextEvent event) {
        System.out.println("contextDestroyed");

    }
}
