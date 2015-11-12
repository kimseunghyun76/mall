package com.springapp.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class DriverLoadingListener implements ServletContextListener 
{
    public DriverLoadingListener() {}

    public void contextDestroyed(ServletContextEvent sce)  
    { 
    	
    }

    public void contextInitialized(ServletContextEvent sce)  
    { 
        ServletContext context = sce.getServletContext();
        String driverClass = context.getInitParameter("driver class");
        try 
        {
            Class.forName(driverClass);
            System.out.println("------ Driver Loading Successful ------");
        } 
        catch (ClassNotFoundException e) 
        {
            e.printStackTrace();
        }
    }
	
}
