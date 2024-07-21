/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import entity.Blog;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;

import dao.DAOBlog;
import dao.DAOImages;
import entity.Images;
import jakarta.mail.Message;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lam1
 */
public class Home extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(true);
        
        DAOBlog dao = new DAOBlog();
        DAOImages daoImage = new DAOImages();

        HashMap<Blog, Vector<Images>> Blog_Image = new HashMap<>();
        Vector<Images> listFake;
        
        //HANDLE FILTERS
        String filterApply = (String) session.getAttribute("filterS");
        String submitFilter = request.getParameter("filterq");
        if (submitFilter != null) {
//            System.out.println("SubmitFILTER: " + submitFilter);
            filterApply = submitFilter;
        }
        filterApply = filterApply == null ? "none" : filterApply;
//        System.out.println("FILTER: "  + filterApply);
        session.setAttribute("filterS", filterApply);
        // IF SESSION FILTER CONTENT EXIST -> USE THAT
        // IF SUBMIT FOR FILTERING IS CLICKED -> USE THE NEW FILTER CONTENT
        // SET ATTRIBUTE FOR KEEPING THE FILTER EVEN IF CHANGE PAGES

        //HANDLE SEARCHES
        String searchC = (String) session.getAttribute("searchC");
        String submitSearch = request.getParameter("search");
        if (submitSearch != null) {
            searchC = request.getParameter("search1");
        }
        searchC = (searchC == null) ? "" : searchC;
//        System.out.println("SEARCH: "+ searchC);
        session.setAttribute("searchC", searchC);
        // IF SESSION SEARCH CONTENT EXIST -> USE THAT
        // IF SUBMIT FOR SEARCHING IS CLICKED -> USE THE NEW SEARCH CONTENT
        // SET ATTRIBUTE FOR KEEPING THE SEARCH EVEN IF CHANGE PAGES

        //GET PAGE
        String currentPage = request.getParameter("page");
        currentPage = currentPage == null ? "1" : currentPage;
        int page = Integer.parseInt(currentPage);
//        System.out.println("Page: " + page);

        String filter = request.getParameter("applyFilter");
        request.setAttribute("filt", filter);
        String showHome = request.getParameter("original");
        if (showHome != null) {
            if (showHome.equals("true")) {
                session.setAttribute("searchC", "");
                session.setAttribute("filterS", "none");
                searchC = "";
                filterApply = "none";
            }
        }
        Vector<Blog> list = new Vector<>();
        try {

            list = dao.newPaging(page, dao.searchThenFilter(searchC, filterApply));
            for (Blog blog : list) {
                listFake = new Vector();
                
                Vector<Images> imageList = daoImage.findImagesByBlog_id(blog.getId());
                
                for (Images images : imageList) {
                    listFake.add(images);
                }
                Blog_Image.put(blog, listFake);

            }
            //Pagination
//            int numberpage = 6;
            int size = dao.searchThenFilter(searchC, filterApply).size();
//            System.out.println("Size: " + size);
            int num = (size % 6 == 0 ? (size / 6) : ((size / 6) + 1)); //so trang
//            System.out.println("Num: " + num);
//            System.out.println("NUMBER: " + num);
//            String xpage = request.getParameter("page");
//            if (xpage == null) {
//                page = 1;
//            } else {
//                page = Integer.parseInt(xpage);
//            }
//            int start, end;
//            start = (page - 1) * numberpage;
//            end = Math.min(page * numberpage, size);
//            Vector<Blog> list1 = dao.getListBlogByPage(list, start, end);

            request.setAttribute("page", page);
            request.setAttribute("num", num);
            request.setAttribute("blog_image", Blog_Image);
            request.setAttribute("list", list);
            
            request.getRequestDispatcher("HomePage.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
        }
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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        DAOBlog dao = new DAOBlog();
        try {
            Vector<Blog> list = dao.getAll();
            request.setAttribute("list", list);
            request.getRequestDispatcher("HomePage.jsp").forward(request, response);
        } catch (Exception e) {

        }
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
