/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package service;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "UploadServlet", urlPatterns = {"/upload"})
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
        String postTitle = request.getParameter("postTitle");
        String postDescription = request.getParameter("postDescription");

        // Uploaded to: ReviewQuanAn\build\web\img\
        String uploadPath = getServletContext().getRealPath("/img") + File.separator;
        DAOImages daoImg = new DAOImages();
        DAOBlog daoBlog = new DAOBlog();

        try {
            int userId = Integer.parseInt(request.getParameter("userId"));
            Blog blogTemp = new Blog(userId, postTitle, postDescription, 0);
            daoBlog.addBlog(blogTemp);

            for (Part part : request.getParts()) {
                String contentType = part.getContentType();

                if (contentType != null && contentType.startsWith("image")) {
                    String fileName = UUID.randomUUID().toString() + "_" + part.getSubmittedFileName();
                    Files.copy(part.getInputStream(), Paths.get(uploadPath, fileName));
                    daoImg.addImages(new Images(blogTemp.getId(), uploadPath + fileName));
                    //response.getWriter().println("The file uploaded sucessfully to: " + uploadPath + fileName);
                }
            }
        } catch (ServletException | IOException | NumberFormatException | SQLException e) {
            response.sendRedirect("HomePage.jsp");
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
        processRequest(request, response);
    }

    private static final long serialVersionUID = 1L;

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
