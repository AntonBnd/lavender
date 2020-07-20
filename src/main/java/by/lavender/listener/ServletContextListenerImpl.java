package by.lavender.listener;

import by.lavender.pool.DBConnection;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ServletContextListenerImpl implements ServletContextListener {
    private static Logger logger = LogManager.getLogger(ServletContextListenerImpl.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        logInitialized(servletContextEvent);
        connectionPoolInitialized();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        DBConnection dbConnection = DBConnection.getInstance();
        dbConnection.destroyConnections();
        logger.info("Connections with database closed");
    }

    private void connectionPoolInitialized() {
        DBConnection.getInstance();
        logger.info("Connection pool initialized");
    }

    private void logInitialized(ServletContextEvent servletContextEvent) {
        String prefix = servletContextEvent.getServletContext().getRealPath("/");
        String fileName = servletContextEvent.getServletContext().getInitParameter("log4j");
        if(fileName != null) {
            PropertyConfigurator.configure(prefix + fileName);
        }
        logger.info("Log4j initialized");
    }
}
