package com.bridgelabz.model;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class UserModel implements HttpSessionBindingListener
{
   private String name;
   private String email;
   private String dateOfBirth;
   private long mobile;
   private String password;
   public String getName()
   {
      return name;
   }
   public void setName(String name)
   {
      this.name = name;
   }
   public String getEmail()
   {
      return email;
   }
   public void setEmail(String email)
   {
      this.email = email;
   }
   public String getDateOfBirth()
   {
      return dateOfBirth;
   }
   public void setDateOfBirth(String dateOfBirth)
   {
      this.dateOfBirth = dateOfBirth;
   }
   public long getMobile()
   {
      return mobile;
   }
   public void setMobile(long mobile)
   {
      this.mobile = mobile;
   }
   public String getPassword()
   {
      return password;
   }
   public void setPassword(String password)
   {
      this.password = password;
   }
   
   @Override
   public void valueBound(HttpSessionBindingEvent event)
   {
      HttpSessionBindingListener.super.valueBound(event);
      System.out.println("User Object Added");
   }
   
   @Override
   public void valueUnbound(HttpSessionBindingEvent event)
   {
      HttpSessionBindingListener.super.valueUnbound(event);
      System.out.println("User Object Deleted");
   }
}
