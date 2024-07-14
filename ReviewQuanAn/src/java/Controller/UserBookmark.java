/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import dao.DAOBlog;
import dao.DAOImages;
import dao.DAOUser;
import dao.DAOBookmark;
import entity.Blog;
import entity.Bookmark;
import entity.Images;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Stack;
import java.util.Vector;

/**
 *
 * @author lam1
 */
public class UserBookmark extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserBlogManagement</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserBlogManagement at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        DAOUser daoUser = new DAOUser();
        //int userID = Integer.parseInt(request.getParameter("user_id"));

        User pageUser = daoUser.getUser(Integer.parseInt(request.getParameter("user_id")));
        //User account = (User) session.getAttribute("User");

        DAOBlog daoBlog = new DAOBlog();
        DAOImages daoImage = new DAOImages();
        DAOBookmark daoBookmark = new DAOBookmark();

        HashMap<Blog, Vector<Images>> Blog_Image = new HashMap<>();
        Vector<Images> listTemp;
        Vector<Blog> listBlog = new Vector<>();
        Vector<Bookmark> listBookmark;

        try {
            listBookmark = daoBookmark.getAllByUserId(pageUser.getId());
            for (Bookmark bookmark : listBookmark) {
                listBlog.add(daoBlog.getBlog(bookmark.getBlogID()));
            }

            Vector<Images> imageList = daoImage.getAll();
            for (Blog blog : listBlog) {

                listTemp = new Vector();

                for (Images images : imageList) {

                    if (images.getBlog_id() == blog.getId()) {
                        listTemp.add(images);
                    }
                }
                Blog_Image.put(blog, listTemp);

            }

            //Paging
            int page, numberpage = 6;
            int size = listBlog.size();
            int PageNumber = (size % 6 == 0 ? (size / 6) : ((size / 6) + 1));
            String xpage = request.getParameter("page");
            if (xpage == null) {
                page = 1;
            } else {
                page = Integer.parseInt(xpage);
            }
            int start, end;
            start = (page - 1) * numberpage;
            end = Math.min(page * numberpage, size);
            Vector<Blog> list1 = daoBlog.getListBlogByPage(listBlog, start, end);

//            String service = request.getParameter("service");
//            if (service.equals("getUpdatedList")) {
//                Vector<Blog> updatedList = daoBlog.getListBlogByPage(listBlog, start, end);
//                request.setAttribute("list", updatedList);
//                request.getRequestDispatcher("UserBookmarkPartials.jsp").forward(request, response); // Return the partial view
//            }

            request.setAttribute("pageUser", pageUser);
            request.setAttribute("user", pageUser);
            request.setAttribute("page", page);
            request.setAttribute("num", PageNumber);
            request.setAttribute("blog_image", Blog_Image);
            request.setAttribute("list", list1);
            request.getRequestDispatcher("UserBookmark.jsp").forward(request, response);
        } catch (Exception e) {

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
