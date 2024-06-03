/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package service;

import Utility.MailSender;
import dao.DAOUser;
import entity.User;
import java.io.IOException;
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
public class VerifyAccount extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String service = request.getParameter("service");
        HttpSession session = request.getSession(true);
        DAOUser dao = new DAOUser();
        //BEGIN
        User user = (User) session.getAttribute("userToVerify");
        String submit = request.getParameter("submit");
        System.out.println(submit);
        //No user found (Access Verify Page by URL)
        if (user == null) {
            response.sendRedirect("RegisterPage.jsp?status=4");
        } 
        else {
            //Send code the first time the page is loaded
            if (submit == null) {
                
                MailSender mailSender = new MailSender();
                String code = mailSender.getVifificationCode();
                
                try {
                    
                    mailSender.sentEmail(user.getEmail(), code);
                    session.setAttribute("verifyCode", code);
                    response.sendRedirect("VerifyPage.jsp");
                    
                } catch (Exception e) {
                    
                    response.sendRedirect("RegisterPage.jsp?status=5");
                    
                }
            } 
            //Check code
            else if (submit != null) 
            {
                String code = request.getParameter("Vcode");
                String sessionCode = (String) session.getAttribute("verifyCode");

                if (code.equals(sessionCode)) 
                {
                    user.setVerify_status(1);
                    int n = dao.updateUser(user);
                    if (n == 1) response.sendRedirect("LoginPage.jsp");
                    //Unknown Error 
                    else response.sendRedirect("VerifyPage.jsp?status=4");
                    
                } 
                //Incorrect verify code
                else 
                {
                    response.sendRedirect("VerifyPage.jsp?status=1");
                }
            } 
            //Unknown Error
            else 
            {
                response.sendRedirect("VerifyPage.jsp?status=4");
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
