/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;

/**
 *
 * @author AnhTT
 */
public class UserController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String service = request.getParameter("service");

        //Login handler------------------------------------------------------
        if (service.equals("login")) {
            
            RequestDispatcher disp = request.getRequestDispatcher("Login");
            disp.forward(request, response);
        }
        //DONE!---------------------------------------------------------------

        //Register handler----------------------------------------------------
        if (service.equals("register")) {
            RequestDispatcher disp = request.getRequestDispatcher("Register");
            disp.forward(request, response);
        }
        //DONE!---------------------------------------------------------------

        //Verify handler------------------------------------------------------
        if (service.equals("verify")) {
            RequestDispatcher disp = request.getRequestDispatcher("VerifyAccount");
            disp.forward(request, response);
        }
        //DONE!---------------------------------------------------------------

        if (service.equals("changePassword")) {
            try{
                RequestDispatcher disp = request.getRequestDispatcher("ChangePassword");
                disp.forward(request, response);
            }catch(Exception e){
                RequestDispatcher disp = request.getRequestDispatcher("ChangePasswordPage.jsp?status=5");
                disp.forward(request, response);
            }
        }
        if(service.equals("resendVerifyCode")){
            RequestDispatcher disp = request.getRequestDispatcher("ResendCode");
            disp.forward(request, response);
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
