package com.bridgelabz.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LogoutController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   HttpSession httpSession = request.getSession();
	   httpSession.removeAttribute("user");
	   Cookie cookie = new Cookie("loginApp", null);
	   cookie.setMaxAge(0);
	   response.addCookie(cookie);
	   httpSession.invalidate();
	   response.sendRedirect(response.encodeURL("home"));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
