package com.yundao.customer.h5.listener;

import com.yundao.core.listener.SystemListener;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

/**
 * @author jan
 * @create 2017-07-30 AM12:24
 **/
@Component
@WebListener
public class MySystemListener extends SystemListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        super.contextInitialized(event);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        super.contextDestroyed(event);
    }

}
