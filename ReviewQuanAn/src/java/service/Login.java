/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package service;

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
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author ACER
 * Commented  : TRUE
 */
public class Login extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(true);
        
        //GET USER INPUT
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        
        //CHECK IF ACCOUNT/PASSWORD ARE CORRECT
        User user = login(account, password);
        
        //SUCCESS
        if (user != null) {
            
            //USER HAS NOT VERIFIED ACCOUNT
            if (user.getVerify_status() == 0) {
                
                session.setAttribute("userToVerify", user);
                response.sendRedirect("UserController?service=verify");
                
            } 
            //USER HAS VERIFIED ACCOUNT
            else {
                //REMOVE SESSION'S INFORMATION THAT MIGHT NOT BE SAFE
                session.removeAttribute("userToVerify");
                session.removeAttribute("verifyCode");
                session.removeAttribute("user");
                
                //LOGIN WITH ROLES
                String[] roles = {"empty", "admin", "customer"};
                String role = roles[user.getRole_id()];
                switch (role) {
                    case "admin":
                        session.setAttribute("Admin", user);
                        response.sendRedirect("home");
                        break;
                    case "customer":
                        session.setAttribute("User", user);
                        response.sendRedirect("home");
                        break;
                    case "empty":
                        response.sendRedirect("LoginPage.jsp?status=1");
                        break;
                    //EXCEPTION (UNKNOWN)
                    default:
                        response.sendRedirect("LoginPage.jsp?status=2");
                        break;
                }
            }
        } 
        //COULD NOT FIND USER IN DATABASE
        else {
            response.sendRedirect("LoginPage.jsp?status=1");
        }
    }

    public User login(String account, String password) {
        DAOUser dao = new DAOUser();
        account = account.toLowerCase();
        Vector<User> vector = dao.getAll();
        for (User user : vector) {
            if ((user.getUsername().equals(account) || user.getEmail().equals(account))) {
                try{
                    boolean checkPassword = BCrypt.checkpw(password, user.getPassword());
                    if(checkPassword){
                        return user;
                    }
                }catch(Exception e){
                    return null;
                }
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
