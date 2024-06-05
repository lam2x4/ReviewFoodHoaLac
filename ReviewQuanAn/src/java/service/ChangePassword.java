/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package service;

import Utility.MailSender;
import dao.DAOUser;
import entity.User;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Vector;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author ACER
 * Commented: TRUE
 */
public class ChangePassword extends HttpServlet {

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
        HttpSession session = request.getSession(true);

        String service = request.getParameter("service");
        
        //COMPARE VERIFY CODE SENT AND USER INPUT
        if ("checkCode".equals(service)) {
            
            //USER INPUT
            String verifyCode = request.getParameter("code");
            //VERIFY CODE SENT
            String vCode = (String) session.getAttribute("verifyCode");
            
            //MATCHED
            if (vCode.equals(verifyCode)) {

                response.setStatus(HttpServletResponse.SC_OK);

            } 
            //NOT MATCHED
            else if (!vCode.equals(verifyCode)) {

                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

            } 
            //EXCEPTION (NET WORK FAILURE)
            else {

                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
        
        //CHANGE PASSWORD
        if (service.equals("changePassword")) {
            try {
                
                User user = (User) session.getAttribute("user");
                
                String password = request.getParameter("newPassword");
                
                //ENCRYPT PASSWORD
                String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
                
                //UPDATE TO DATABASE
                user.setPassword(hashedPassword);
                DAOUser dao = new DAOUser();
                int n = dao.updateUser(user);
                
                //SUCCESS
                if (n == 1) {
                    response.sendRedirect("ChangePasswordPage.jsp?status=7");
                } 
                //FAILED
                else {
                    response.sendRedirect("ChangePasswordPage.jsp?status=8");
                }
            } catch (Exception e) {
                
                //NULL USER
                response.sendRedirect("ChangePasswordPage.jsp?status=9");
            }
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
