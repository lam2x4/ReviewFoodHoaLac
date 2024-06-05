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
import java.util.Vector;

/**
 *
 * @author ACER
 * Commented: TRUE
 */
public class SendVerifyCode extends HttpServlet {

    private String vCode;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(true);

        String service = request.getParameter("service");
        //SEND VERIFICATION CODE
        if ("sendVerificationCode".equals(service)) {
            
            //GET EMAIL OF CURRENT ACCOUNT ON THE SESSION
            String email = request.getParameter("email");
            
            //CHECK USER IN DATABASE
            User user = checkUserExist(email);
            
            //COULD NOT FIND USER IN THE DATABASE
            if (user == null) {
                response.setStatus(1);
                return;
            }
            
            //SEND EMAIL
            boolean success = sendVerificationCode(email);
            
            //EMAIL SENT
            if (success) {
                
                //SETUP VERIFY CODE AND USER FOR LATER COMPAREMENT 
                session.setAttribute("verifyCode", vCode);
                session.setAttribute("user", user);
                
                //RETURN STATUS OF RESPONSE ==> MESSAGE OUTPUT AT SCREEN
                response.setStatus(HttpServletResponse.SC_OK);
                
            //EMAIL COULD NOT SEND    
            } else {
                
                //RETURN STATUS OF RESPONSE ==> MESSAGE OUTPUT AT SCREEN
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
    }
    
    //SEND MAIL FUNCTION TRUE IF SENT, FALSE IF NOT SENT
    private boolean sendVerificationCode(String email) {
        MailSender mailSender = new MailSender();
        String code = mailSender.getVifificationCode();
        try {

            mailSender.sentEmail(email, code);
            vCode = code;
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    //RETRIVE USERS IN DATABASE, RETURN USER WITH EMAIL = INPUTTED EMAIL
    public User checkUserExist(String email) {
        DAOUser dao = new DAOUser();
        Vector<User> vector = dao.getAll();
        for (User user : vector) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
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
