/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package service;

import dao.DAOBlog;
import dao.DAOImages;
import dao.DAOUser;
import entity.Blog;
import entity.Images;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Vector;

/**
 *
 * @author ACER
 */
public class Search extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        
        String searchContent = request.getParameter("search1");
        
        
        DAOBlog dao = new DAOBlog();
        DAOImages daoImage = new DAOImages();
        HashMap<Blog, Vector<Images>> Blog_Image = new HashMap<>();
        Vector<Images> imageList;
        Vector<Images> listFake;

        try {
            imageList = daoImage.getAll();
            Vector<Blog> list = dao.search1(searchContent);

            for (Blog blog : list) {

                listFake = new Vector();

                for (Images images : imageList) {

                    if (images.getBlog_id() == blog.getId()) {
                        listFake.add(images);
                    }
                }
                Blog_Image.put(blog, listFake);

            }
            //Pagination
            int page, numberpage = 6;
            int size = list.size();
            int num = (size % 6 == 0 ? (size / 6) : ((size / 6) + 1)); //so trang
            String xpage = request.getParameter("page");
            if (xpage == null) {
                page = 1;
            } else {
                page = Integer.parseInt(xpage);
            }
            int start, end;
            start = (page - 1) * numberpage;
            end = Math.min(page * numberpage, size);
            Vector<Blog> list1 = dao.getListBlogByPage(list, start, end);

            request.setAttribute("page", page);
            request.setAttribute("num", num);
            request.setAttribute("blog_image", Blog_Image);
            request.setAttribute("list", list1);
            request.getRequestDispatcher("HomePage.jsp").forward(request, response);
        } catch (Exception e) {

        }
    }

    public String addString(Blog blog) {
        String output = "";
        if (blog == null) {
            return null;
        } else {
            output = blog.getUsername() + blog.getTitle() + blog.getContent() + blog.getCreate_date();
        }
        return output;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
