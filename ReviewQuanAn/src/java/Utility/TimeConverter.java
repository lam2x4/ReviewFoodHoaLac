/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utility;
import dao.DAOBlog;
import entity.Blog;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class TimeConverter {
    
    public static LocalDateTime parseCreateDate(String dateTimeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"); 
        return LocalDateTime.parse(dateTimeString, formatter);
    }
    
//    public static void main(String[] args) {
//        try{
//            TimeConverter t = new TimeConverter();
//        DAOBlog dao = new DAOBlog();
//        Blog blog = dao.getBlog(8);
//            System.out.println(blog.getCreate_date());
//        System.out.println(parseCreateDate(blog.getCreate_date()));
//        }
//        catch(SQLException e){
//            
//        }
//    }

    
}
