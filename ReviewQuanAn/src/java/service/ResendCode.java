/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package service;

import Utility.MailSender;
import dao.DAOUser;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author ACER
 * Commented: TRUE
 */
public class ResendCode extends HttpServlet {

    private String vCode;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(true);
        DAOUser dao = new DAOUser();

        try {
            //GET USER IN SESSION
            User user = (User) session.getAttribute("userToVerify");
            
            //SUCCESS
            if (user != null) {
                String email = user.getEmail();
                
                //SEND EMAIL
                boolean success = sendVerificationCode(email);
                
                //SUCCESS
                if (success == true) {
                    session.setAttribute("verifyCode", vCode);
                    response.sendRedirect("VerifyPage.jsp?status=2");
                } 
                //FAILED
                else {
                    response.sendRedirect("VerifyPage.jsp?status=3");
                }
            } 
            //FAILED
            else {
                response.sendRedirect("VerifyPage.jsp?status=5");
            }
        } catch (Exception e) {
            //USER NULL
            response.sendRedirect("VerifyPage.jsp?status=5");
        }
    }

    private boolean sendVerificationCode(String email) {
        MailSender mailSender = new MailSender();
        try {
            String code = mailSender.getVifificationCode();
            mailSender.sentEmail(email, code);
            vCode = code;
            return true;
        } catch (Exception e) {
            return false;
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
