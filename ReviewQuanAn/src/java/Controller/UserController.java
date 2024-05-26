/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

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
 * @author AnhTT
 */
public class UserController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String service = request.getParameter("service");
        HttpSession session = request.getSession(true);
        DAOUser dao = new DAOUser();
        if (service.equals("login")) {

            String account = request.getParameter("account");
            String password = request.getParameter("password");
            String role = login(account, password);

            switch (role) {
                case "admin":
                    response.sendRedirect("HomePage.jsp");
                    break;
                case "customer":
                    response.sendRedirect("home");
                    break;
                case "storeManager":
                    response.sendRedirect("StoreManagerPage.jsp");
                    break;
                default:
                    response.sendRedirect("LoginPage.jsp");
                    break;
            }
        }
        if(service.equals("register")){
            String name = request.getParameter("name");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String avatar = "";
            int gender = Integer.parseInt(request.getParameter("gender"));
            String description = "";
            int role = Integer.parseInt(request.getParameter("role"));
            User user = new User(username, password, email, phone, avatar, gender, description, role);
            dao.addUser(user);
            response.sendRedirect("LoginPage.jsp");
        }

    }

    public String login(String account, String password) {
        String[] roles = {"admin", "storeManager","customer"};
        DAOUser dao = new DAOUser();
        Vector<User> vector = dao.getAll("select * from [user]");
        for (User user : vector) {
            if ((user.getUse_rname().equals(account) || user.getEmail().equals(account)) && user.getPassword().equals(password)) {
                return roles[user.getRole_id()-1];
            }
        }
        return "empty";
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
