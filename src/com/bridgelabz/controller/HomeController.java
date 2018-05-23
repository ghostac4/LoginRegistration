package com.bridgelabz.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bridgelabz.dao.DatabaseUtil;
import com.bridgelabz.model.UserModel;

public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public HomeController() {
        super();
    }
    
    @Override
   public void init() throws ServletException
   {
      super.init();
      DatabaseUtil.getConnection();
   }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
	   Cookie[] cookies = request.getCookies();
		String uuid="";
		if(cookies!=null) {
		   for(Cookie cookie : cookies) {
	         if(cookie.getName().equals("loginApp")) {
	            uuid = cookie.getValue();
	            break;
	         }
	      }
		}
		if(uuid.equals("")) {
		   response.sendRedirect(response.encodeURL("index.jsp"));
		}else {
		   UserModel userModel = DatabaseUtil.getUserByUuid(uuid);
		   if(userModel == null) {
		      response.sendRedirect(response.encodeURL("index.jsp"));
		   }else {
		      session = request.getSession();
	         session.setAttribute("user", userModel);
	         response.sendRedirect(response.encodeURL("home.jsp"));
		   }
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
