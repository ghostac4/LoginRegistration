package com.bridgelabz.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bridgelabz.dao.DatabaseUtil;
import com.bridgelabz.model.UserModel;
import com.fasterxml.uuid.Generators;

public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public LoginController() {
        super();
    }

    @Override
   public void init() throws ServletException
   {
      super.init();
      DatabaseUtil.getConnection();
   }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   String username = request.getParameter("username");
	   String password = request.getParameter("password");
	   UserModel userModel = DatabaseUtil.getUser(username);
	   if(userModel!=null) { 
	      if(password.equals(userModel.getPassword())) {
	         request.setAttribute("username", username);
	         String uuid = Generators.timeBasedGenerator().generate().toString();
	         Cookie cookie = new Cookie("loginApp", uuid);
	         DatabaseUtil.insertUuid(username,uuid);
	         response.addCookie(cookie);
	         request.getSession().setAttribute("user", userModel);
	         RequestDispatcher requestDispatcher = request.getRequestDispatcher(response.encodeURL("home.jsp"));
	         requestDispatcher.forward(request, response);
	      }
	   }else {
	      request.setAttribute("usernameError", "Username does not exist");
	      RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
	      requestDispatcher.forward(request, response);
	   }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	public void destroy()
	{
	   super.destroy();
	   DatabaseUtil.close();
	}
}
