package com.bridgelabz.filter;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ValidationFilter implements Filter {

   private static final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
   private static Pattern pattern;

   public ValidationFilter() {
      pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
    }

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	   
	   RequestDispatcher requestDispatcher = request.getRequestDispatcher("register.jsp");
	   String username=request.getParameter("username");
	   String password = request.getParameter("password");
	   String email = request.getParameter("email");
	   
	   long mobile;
	   
	   if(username.equals("")) {
	      request.setAttribute("usernameError", "Username cannot be blank");
	      requestDispatcher.forward(request, response);
	   }else if(password.equals("")) {
	      request.setAttribute("passwordError", "Password cannot be blank");
	      requestDispatcher.forward(request, response);
	   }else if(password.length() < 5 && password.length() > 10) {
	      request.setAttribute("passwordError", "Password length should be between 5-10");
	      requestDispatcher.forward(request, response);
	   }else if(!pattern.matcher(email).matches()) {
	      request.setAttribute("emailError", "Invalid Email");
	      requestDispatcher.forward(request, response);
	   }else if(request.getParameter("mobile").equals("")) {
         request.setAttribute("mobileError", "Mobile Cannot be blank");
         requestDispatcher.forward(request, response);
      }else {
         mobile= Long.parseLong(request.getParameter("mobile"));
         if(mobile < 1111111111 || mobile > (1111111111*9)) {
            request.setAttribute("mobileError", "Invalid Mobile Number");
            requestDispatcher.forward(request, response);
         }else {
            chain.doFilter(request, response);  
         }
      }
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
