/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Entity.Customer;
import Entity.User;
import Model.DAOCustomer;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Vector;
import org.apache.jasper.tagplugins.jstl.core.ForEach;

/**
 *
 * @author ACER
 */
public class CustomerController extends HttpServlet {

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
        DAOCustomer dao = new DAOCustomer();
        String service = request.getParameter("service");
        HttpSession session = request.getSession(true);

        if (service.equals("login")) {

            String account = request.getParameter("account");
            String password = request.getParameter("password");

            int role = login(account, password);
            if(role == 1){
                response.sendRedirect("TA_JSP/IndexCustomer.jsp");
            }
            else if(role == 2){
                response.sendRedirect("TA_JSP/newjsp.jsp");
            }
            else if(role == 3){
                response.sendRedirect("TA_JSP/RegisterPage.jsp");
            }
            
        }

    }

    public int login(String account, String password) {
        DAOCustomer dao = new DAOCustomer();
        Vector<User> vector = dao.getAll("select * from [user]");
        for (User user : vector) {
            if (user.getAccount().equals(account) && user.getPassword().equals(password)) {
                return user.getRole();
            }
        }
        return 0;
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
