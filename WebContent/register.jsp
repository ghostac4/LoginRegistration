<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Login and Registration</title>
<link rel="stylesheet" href="css/main.css">
</head>
<body>
<div class='main-container'>
  <div class='login-box'>
  <form action='RegistrationForm' method="post">
    <table>
      <tbody>
        <tr>
          <td class='input-row'><label for='username'>Username</label></td>
          <td class='input-row'><input type='text' id='username' name='username'>
          <%
          	String usernameError="";
          	if(request.getAttribute("usernameError")!=null){
          	  usernameError = request.getAttribute("usernameError").toString();  
          	}
          %>
          <p><%=usernameError %></p>
          </td>
        </tr>
        <tr>
          <td class='input-row'><label for='email'>Email</label></td>
          <td class='input-row'><input type='email' id='email' name='email'>
          <%
          	String emailError="";
          	if(request.getAttribute("emailError")!=null){
          	  emailError = request.getAttribute("emailError").toString();  
          	}
          %>
          <p><%=emailError %></p>
          </td>
        </tr>
        <tr>
          <td class='input-row'><label for='password'>Password</label></td>
          <td class='input-row'><input type='password' id='password' name='password'>
          <%
          	String passwordError="";
          	if(request.getAttribute("passwordError")!=null){
          	  passwordError = request.getAttribute("passwordError").toString();  
          	}
          %>
          <p><%=passwordError %></p>
          </td>
        </tr>
        <tr>
          <td class='input-row'><label for='dob'>Date of Birth</label></td>
          <td class='input-row'><input type='date' id='dob' name='dob'></td>
        </tr>
        <tr>
          <td class='input-row'><label for='mobile'>Mobile No</label></td>
          <td class='input-row'><input type='tel' id='mobile' name='mobile'>
          <%
          	String mobileError="";
          	if(request.getAttribute("mobileError")!=null){
          	  mobileError = request.getAttribute("mobileError").toString();  
          	}
          %>
          <p><%=mobileError %></p>
          </td>
        </tr>
        <tr>
          <td colspan="2"><button type="submit"><p>Register</p></button></td>
        </tr>
      </tbody>
    </table>
    </form>
  </div>
</div>
</body>
</html>
