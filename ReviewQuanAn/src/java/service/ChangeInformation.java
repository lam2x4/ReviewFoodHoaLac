/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package service;

import dao.DAOUser;
import entity.Images;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import entity.User;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
/**
 *
 * @author kyler
 */
public class ChangeInformation extends HttpServlet {

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
            out.println("<title>Servlet ChangeInformation</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangeInformation at " + request.getContextPath() + "</h1>");
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
//        HttpSession session = request.getSession(true);
//        User user = (User) session.getAttribute("User");
//        session.setAttribute("User", user);
        response.sendRedirect("ChangeInformation.jsp");
        //request.getRequestDispatcher("ChangeInformation.jsp").forward(request, response);
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
        HttpSession session = request.getSession(true);
        try {
            User user = (User) session.getAttribute("User");
            String email = avoidBlank(request.getParameter("email"), user.getEmail());
            String username = avoidBlank(request.getParameter("username"), user.getUsername());
            String password = avoidBlank(request.getParameter("password"), user.getPassword());
            String phone = avoidBlank(request.getParameter("phone"), user.getPhone());

            DAOUser dao = new DAOUser();

            String uploadPath = getServletContext().getRealPath("/img") + File.separator;
            for (Part part : request.getParts()) {
                String contentType = part.getContentType();

                if (contentType != null && contentType.startsWith("image")) {
                    String avatarURL = UUID.randomUUID().toString() + "_" + part.getSubmittedFileName();
                    Files.copy(part.getInputStream(), Paths.get(uploadPath, avatarURL));
//                    User tempUser = new User(User.getId(), username, password, email, phone, avatarURL, User.getGender(), User.getDescription(), User.getCreate_date(), User.getVerify_status(), User.getRole_id());
                    User tempUser = new User(user.getId(), username, password, email, phone, avatarURL, user.getGender(), user.getDescription(), user.getCreate_date(), user.getVerify_status(), user.getRole_id());
//                    response.getWriter().println(tempUser.toString());
                    dao.updateUser(tempUser);
                    session.setAttribute("User", tempUser);
//                    response.getWriter().println("The file uploaded sucessfully to: " + uploadPath + avatarURL);
                }
            }
        } catch (ServletException | IOException e) {
            e.printStackTrace();
            response.getWriter().println("Error updating user: " + e.getMessage());
        }
    }

    private String avoidBlank(String para, String attribute) {
        return (para != null && !para.trim().isEmpty()) ? para : attribute;
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
