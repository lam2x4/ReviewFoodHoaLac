/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package service;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;
import dao.DAOImages;
import entity.Images;
import dao.DAOBlog;
import entity.Blog;
import entity.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class PostUpload extends HttpServlet {

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(true);
        String service = request.getParameter("service");
        String uploadPath = getServletContext().getRealPath("/img") + File.separator;

        //Uploaded to: ReviewQuanAn\build\web\img\
        DAOImages daoImg = new DAOImages();
        DAOBlog daoBlog = new DAOBlog();

        if (service == null) {
            String postTitle = request.getParameter("postTitle");
            String postDescription = request.getParameter("postDescription");
//        response.getWriter().println("Post Title: " + postTitle);
//        response.getWriter().println("Post Description: " + postDescription);

            try {
                LocalDateTime date = LocalDateTime.now();
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
                String create_date = date.format(dateFormat);
                int userId = Integer.parseInt(request.getParameter("userId"));
                Blog blogTemp = new Blog(userId, postTitle, postDescription, create_date, 0, 0, 0, userId);
//            response.getWriter().println(blogTemp.toString());
//            Blog blogTemp = new Blog(1, postTitle, postDescription, 0);
                daoBlog.addBlog(blogTemp);
                for (Part part : request.getParts()) {
                    String contentType = part.getContentType();

                    if (contentType != null && contentType.startsWith("image")) {
                        String originalFileName = part.getSubmittedFileName();
                        String fileName = originalFileName.substring(0, 10) + "_" + UUID.randomUUID().toString() + originalFileName.substring(originalFileName.lastIndexOf("."));
                        Files.copy(part.getInputStream(), Paths.get(uploadPath, fileName));
                        daoImg.addImages(new Images(daoBlog.getLastInsertedBlog(), fileName));
//                    response.getWriter().println("The file uploaded sucessfully to: " + uploadPath + fileName);
                    }
                }
                String message = "Blog successfully applied!";
                response.sendRedirect("ApplyPostPage.jsp" + "?message=" + message);
            } catch (ServletException | IOException | NumberFormatException | SQLException e) {
                response.getWriter().println("Error: " + e.getMessage());
                //response.sendRedirect("HomePage.jsp");
            }

        } else if (service.equals("repost")) {
            try {
                User currentUser = (User) session.getAttribute("User");
                DAOBlog daoB = new DAOBlog();
                Blog current = daoB.getBlog(Integer.parseInt(request.getParameter("bid")));
                Blog repost = current;
                repost.setUser_id(currentUser.getId());
                repost.setTitle(repost.getTitle() + " (Repost)");

                LocalDate date = LocalDate.now();
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
                String create_date = date.format(dateFormat);

                repost.setCreate_date(create_date);

                repost.setCreate_date(service);
                daoB.addBlog(repost);
                daoB.getLastInsertedBlog();

                DAOImages daoI = new DAOImages();
                int lastBId = daoB.getLastInsertedBlog();
                Vector<Images> vector = daoI.findImagesByBlog_id(current.getId());
                for (Images image : vector) {
                    image.setBlog_id(lastBId);
                    image.setLink(image.getLink().substring(0, 10) + "_" + UUID.randomUUID().toString());
                    daoI.addImages(image);
                }
                response.sendRedirect("home");
            } catch (Exception e) {
                response.sendRedirect("LoginPage.jsp");
            }
        } else if (service.equals("edit")) {
            try {
                String postTitle = request.getParameter("postTitle");
                String postDescription = request.getParameter("postDescription");
                Blog blog = daoBlog.getBlog(Integer.parseInt(request.getParameter("blogId")));
                //Blog repost = current;
                //repost.setUser_id(currentUser.getId());
                //repost.setTitle(repost.getTitle() + " (Repost)");

                LocalDateTime date = LocalDateTime.now();
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");

                blog.setCreate_date(date.format(dateFormat));
                blog.setIs_approved(0);
                blog.setTitle(postTitle);
                blog.setContent(postDescription);

                try {
                    Vector<Images> pastImg = daoImg.findImagesByBlog_id(blog.getId());

                    //Delete one prior bill
                    boolean billUpload = false;
                    Part billPart = request.getPart("billUpload");
                    if (billPart != null && billPart.getSize() > 0) {
                        Images billOld = pastImg.firstElement();
                        Path billPathOld = Paths.get(billOld.getLink());
                        try {
                            Files.deleteIfExists(billPathOld);
                        } catch (IOException e) {
                            response.getWriter().println("Bill Delete Error: " + e.getMessage());
                        }
                        String originalBillName = billPart.getSubmittedFileName();
                        String fileName = originalBillName.substring(0, 10) + "_" + UUID.randomUUID().toString() + originalBillName.substring(originalBillName.lastIndexOf("."));

                        Files.copy(billPart.getInputStream(), Paths.get(uploadPath, fileName));

                        billOld.setLink(fileName);
                        daoImg.updateImages(billOld);
                        billUpload = true;
                    }

                    //Delete any prior images
                    Part imagePart = request.getPart("imageUpload");
                    if (imagePart != null && imagePart.getSize() > 0) {
                        for (int i = 1; i < pastImg.size(); i++) {
                            daoImg.removeImages(pastImg.get(i).getId());
                        }
                    }

                    //Add the new images
                    for (Part part : request.getParts()) {
                        String contentType = part.getContentType();

                        if (contentType != null && contentType.startsWith("image")) {
                            if (billUpload == true) {
                                billUpload = false;
                                continue;
                            }
                            String originalFileName = part.getSubmittedFileName();
                            String fileName = originalFileName.substring(0, 10) + "_" + UUID.randomUUID().toString() + originalFileName.substring(originalFileName.lastIndexOf("."));

                            Files.copy(part.getInputStream(), Paths.get(uploadPath, fileName));
                            daoImg.addImages(new Images(blog.getId(), fileName));
//                    response.getWriter().println("The file uploaded sucessfully to: " + uploadPath + fileName);
                        }
                    }
                    daoBlog.editBlog(blog);
                } catch (ServletException | IOException | NumberFormatException | SQLException e) {
                    response.getWriter().println("Error: " + e.getMessage());
                    //response.sendRedirect("HomePage.jsp");
                }

                String message = "Blog successfully updated";
                response.sendRedirect("BlogEdit?blogId=" + blog.getId() + "&message=" + message);
            } catch (Exception e) {
                response.getWriter().println("Outer Post Upload Error: " + e.getMessage());
                //response.sendRedirect("LoginPage.jsp");
            }
        }
    }

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
        response.sendRedirect("ApplyPostPage.jsp");
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
