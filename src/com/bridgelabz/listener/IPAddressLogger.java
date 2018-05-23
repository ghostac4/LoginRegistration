package com.bridgelabz.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class IPAddressLogger implements ServletRequestListener {

    public IPAddressLogger() {
    }

    public void requestDestroyed(ServletRequestEvent sre)  {
       System.out.println("Ip Address of request destroyed : "+sre.getServletRequest().getRemoteAddr());
    }

    public void requestInitialized(ServletRequestEvent sre)  { 
       System.out.println("Ip Address of request initialized : "+sre.getServletRequest().getRemoteAddr());
    }
	
}
