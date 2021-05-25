package com.ekros.library.controller.listeners;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {

    private static final Logger log = Logger.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        initLog4J(sce.getServletContext());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }



    private void initLog4J(ServletContext servletContext) {
        log.info("Log4J init...");
        try {
            PropertyConfigurator.configure(servletContext.getRealPath("WEB-INF/classes/log4j.properties"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        log.info("Log4J init complete.");
    }
}
