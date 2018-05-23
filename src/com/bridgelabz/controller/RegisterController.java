package com.bridgelabz.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bridgelabz.dao.DatabaseUtil;
import com.bridgelabz.model.UserModel;

public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RegisterController() {
        super();
    }

    @Override
   public void init() throws ServletException
   {
      super.init();
      DatabaseUtil.getConnection();
   }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserModel userModel = new UserModel();
		userModel.setName(request.getParameter("username"));
		userModel.setPassword(request.getParameter("password"));
		userModel.setEmail(request.getParameter("email"));
		userModel.setMobile(Long.parseLong(request.getParameter("mobile")));
		userModel.setDateOfBirth(request.getParameter("dob"));
		boolean flag = DatabaseUtil.registerUser(userModel);
		if(!flag) {
		   request.setAttribute("error", "Username Already Exits");
		}
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("register.jsp");
		requestDispatcher.forward(request, response);
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
