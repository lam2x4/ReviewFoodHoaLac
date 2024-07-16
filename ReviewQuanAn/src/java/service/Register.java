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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author AnhTT
 * Commented: TRUE
 */
public class Register extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(true);
        DAOUser dao = new DAOUser();
        
        //DATA SUBMITTED WITH FORM
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        
        User user = new User(email, phone);
        //CHECK IF EMAIL OR PHONE ALREADY EXISTED IN DATABASE
        if (!checkRegister(user)) {
            response.sendRedirect("RegisterPage.jsp?status=1");
            return;
        }

        String avatar = "6c58498e-b7b1-4a27-8596-c8ec9bceda2c_png-transparent-default-avatar-thumbnail.png";
        int gender = Integer.parseInt(request.getParameter("gender"));
        String description = request.getParameter("description");
        
        //GET CURRENT DATE
        LocalDate date = LocalDate.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String create_date = date.format(dateFormat);
        
        //HASH PASSWORD AND ADD TO DATABASE
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        //VERIFY STATUS = 0 MEANS HAVE NOT VERIFY ----- ROLE = 2 MEANS CUSTOMER
        user = new User(username, hashedPassword, email, phone, avatar, gender, description, create_date, 0, 2);

        try {
            //ADD USER TO DATABASE 
            int n = dao.addUser(user);
            System.out.println(n);
            //SUCCESS
            if (n > 0) {
                
                //UPDATE CURRENT USER WITH DATABASE (CURRENT USER DOES NOT HAVE ID)
                user = dao.getUser(email);
                System.out.println(user.toString());
                //EXCEPTION
                if (user == null) {
                    response.sendRedirect("RegisterPage.jsp?status=6");
                } 
                //SUCCESS
                else {
                    session.setAttribute("userToVerify", user);
                    response.sendRedirect("UserController?service=verify");
                }
            } 
            //FAILED
            else {
                //COULD NOT ADD USER
                response.sendRedirect("RegisterPage.jsp?status=2");
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
