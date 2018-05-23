package com.bridgelabz.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bridgelabz.model.UserModel;

public class DatabaseUtil
{
   private final static String JDBC_URL = "jdbc:mysql://localhost:3306/UserLogin";
   private final static String JDBC_USERNAME = "root";
   private final static String JDBC_PASSWORD = "root";
   private static Connection connection;
   
   public static Connection getConnection() {
      try {
         Class.forName("com.mysql.jdbc.Driver");
         connection = 
               DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
      } catch (ClassNotFoundException | SQLException e) {
         e.printStackTrace();
      }
      return connection;
   }
   
   public static boolean registerUser(UserModel userModel) {
      if(!checkUser(userModel.getEmail())) {
         return false;
      }else {
         String sql="insert into users (name,email,dateOfBirth,password,mobile) values(?,?,?,?,?)";
         try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,userModel.getName());
            preparedStatement.setString(2, userModel.getEmail());
            preparedStatement.setString(3, userModel.getDateOfBirth());
            preparedStatement.setString(4, userModel.getPassword());
            preparedStatement.setLong(5, userModel.getMobile());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
         } catch (SQLException e) {
            e.printStackTrace();
            return false;
         }
      }
   }
   
   public static UserModel getUser(String email) {
      String sql="select * from users where email=?";
      try {
         PreparedStatement preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
         preparedStatement.setString(1, email);
         ResultSet rs = preparedStatement.executeQuery();
         if(rs.next()) {
            UserModel userModel = new UserModel();
            userModel.setName(rs.getString(2));
            userModel.setEmail(rs.getString(3));
            userModel.setDateOfBirth(rs.getString(4));
            userModel.setPassword(rs.getString(5));
            userModel.setMobile(rs.getLong(6));
            rs.close();
            preparedStatement.close();
            return userModel;
         }else {
            return null;
         }
      } catch (SQLException e) {
         e.printStackTrace();
         return null;
      }
   }
   
   public static void insertUuid(String username,String uuid) {
      String sql="update users set uuid=? where email=?";
      try {
         PreparedStatement preparedStatement = connection.prepareStatement(sql);
         preparedStatement.setString(1, uuid);
         preparedStatement.setString(2, username);
         preparedStatement.executeUpdate();
         preparedStatement.close();
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
   
   public static UserModel getUserByUuid(String uuid) {
      String sql="select * from users where uuid=?";
      UserModel userModel=new UserModel();
      try {
         PreparedStatement preparedStatement = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
         preparedStatement.setString(1, uuid);
         ResultSet resultSet = preparedStatement.executeQuery();
         if(resultSet.next()) {
            userModel.setName(resultSet.getString(2));
            userModel.setEmail(resultSet.getString(3));
            userModel.setDateOfBirth(resultSet.getString(4));
            userModel.setMobile(resultSet.getLong(6));
         }
         return userModel;
      } catch (SQLException e) {
         e.printStackTrace();
         return null;
      }

   }
   
   public static boolean checkUser(String email) {
      UserModel userModel = getUser(email);
      if(userModel!=null)
         return false;
      return true;
   }
   public static void close() {
      try {
         connection.close();
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
}
