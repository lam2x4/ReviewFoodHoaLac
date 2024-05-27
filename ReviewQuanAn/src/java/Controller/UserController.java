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

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;
import service.MailSender;

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
            User user = login(account, password);
            if (user != null) {
                if (user.getVerify_status() == 0) {
                    session.setAttribute("userToVerify", user);
                    response.sendRedirect("UserController?service=verify");
                } else {
                    String[] roles = {"empty", "admin", "customer"};
                    String role = roles[user.getRole_id()];
                    switch (role) {
                        case "admin":
                            response.sendRedirect("AfterLogin.jsp");
                            break;
                        case "customer":
                            response.sendRedirect("home");
                            break;
                        default:
                            response.sendRedirect("home");
                            break;
                    }
                }
            } else {
                response.sendRedirect("LoginPage.jsp");
            }

        }
        if (service.equals("register")) {
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
                        Vector vector = dao.getAll("select * from [user]");
                        user = (User) vector.get(vector.size()-1);
                        session.setAttribute("userToVerify", user);
                        response.sendRedirect("UserController?service=verify");
                        return;
                    } else {
                        // Handle case where user addition fails
                        response.sendRedirect("RegisterPage.jsp?status=error");
                    }
                } else {
                    // Handle case where registration check fails
                    response.sendRedirect("RegisterPage.jsp?status=failure1");
                }
            } catch (Exception e) {
                e.printStackTrace();  // Log the exception
                response.sendRedirect("RegisterPage.jsp?status=error");
            }
        }
        if (service.equals("verify")) {
            User user = (User) session.getAttribute("userToVerify");
            String submit = request.getParameter("submit");

            if (user == null) {
                response.sendRedirect("RegisterPage.jsp?status=failure");
                return;
            } else {
                if (submit == null) {
                    MailSender mailSender = new MailSender();
                    String code = mailSender.getVifificationCode();
                    session.setAttribute("verificationCode", code);
                    mailSender.sentEmail(user.getEmail(), code);
                    response.sendRedirect("VerifyPage.jsp");
                    return;
                } else if (submit != null) {
                    String code = request.getParameter("Vcode");
                   
                    String sessionCode = (String) session.getAttribute("verificationCode");

                    if (code.equals(sessionCode)) {
                        user.setVerify_status(1);
                        int n = dao.updateUser(user);
                        if (n == 1) {
                            response.sendRedirect("LoginPage.jsp");
                            return;
                        } else {
                            response.sendRedirect("RegisterPage.jsp?status=error");
                        }
                    } else {
                        response.sendRedirect("RegisterPage.jsp?status=error1");
                    }
                } else {
                    response.sendRedirect("RegisterPage.jsp?status=error2");
                }
            }
        }
    }

    public User login(String account, String password) {
        DAOUser dao = new DAOUser();
        Vector<User> vector = dao.getAll("select * from [user]");
        for (User user : vector) {
            if (user.getUser_name().equals(account) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public boolean checkRegister(User inputUser) {
        DAOUser dao = new DAOUser();
        Vector<User> vector = dao.getAll("select * from [user]");
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
