/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package service;

import dao.DAOUser;
import entity.User;
import java.io.IOException;
import java.util.Vector;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author ACER
 */
public class Register extends HttpServlet {

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
        DAOUser dao = new DAOUser();
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String avatar = "";
        int gender = Integer.parseInt(request.getParameter("gender"));
        String description = request.getParameter("description");

        User user = new User(username, password, email, phone, avatar, gender, description, 0, 2);

        try {
            if (checkRegister(user)) {
                int n = dao.addUser(user);
                if (n > 0) {
                    Vector vector = dao.getAll();
                    user = (User) vector.get(vector.size() - 1);
                    session.setAttribute("userToVerify", user);
                    response.sendRedirect("UserController?service=verify");
                    return;
                } else {
                    // Can't add user
                    response.sendRedirect("RegisterPage.jsp?status=2");
                }
            } else {
                // Handle case where registration check fails
                response.sendRedirect("RegisterPage.jsp?status=1");
            }
        } catch (Exception e) {
            e.printStackTrace();  // Log the exception
            response.sendRedirect("RegisterPage.jsp?status=3");
        }
    }
    public boolean checkRegister(User inputUser) {
        DAOUser dao = new DAOUser();
        Vector<User> vector = dao.getAll();
        for (User user : vector) {
            if (inputUser.getEmail().equals(user.getEmail()) || inputUser.getPhone().equals(user.getPhone())) {
                return false;
            }
        }
        return true;
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
