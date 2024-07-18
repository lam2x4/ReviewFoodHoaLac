/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utility;

import dao.DAOBlog;
import entity.Blog;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

/**
 *
 * @author ACER
 */
public class BlogFilterPop implements Comparator<Blog> {

    @Override
    public int compare(Blog o1, Blog o2) {
        // First, compare by likes in descending order
        int likesComparison = Integer.compare(o2.getLikes(), o1.getLikes());
        if (likesComparison != 0) {
            return likesComparison;
        }

        // If likes are equal, compare by create date in descending order
        LocalDateTime d1 = TimeConverter.parseCreateDate(o1.getCreate_date());
        LocalDateTime d2 = TimeConverter.parseCreateDate(o2.getCreate_date());
        return d2.compareTo(d1);
    }
    public static void main(String[] args) {
        try{
            DAOBlog dao = new DAOBlog();
            Vector<Blog> vector = dao.getAll();
            Collections.sort(vector, new BlogFilterPop());
            for(Blog blog: vector){
                System.out.println(blog.toString());
            }
        }catch(SQLException e){
            
        }
    }

}
