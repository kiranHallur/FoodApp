package food.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import food.daoImp.UserDAOImpl;

@WebServlet("/FpNewPassword")
public class FpNewPassword extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("new password servlet started");
        
        String newPassword = request.getParameter("password");
        String confirmPassword = request.getParameter("confPassword");

        // Assuming you have the user's email in the session
        HttpSession session = request.getSession();
        String userEmail = (String) session.getAttribute("userEmail");

        // Perform necessary validation, for example, ensuring that the passwords match
        if (newPassword.equals(confirmPassword)) {
            // Call the updatePassword method
            System.out.println("both passwords are equal");
            UserDAOImpl udi = new UserDAOImpl();

            int updateResult = udi.updatePassword(userEmail, newPassword);

            System.out.println("return value from update password " + updateResult);

            if (updateResult > 0) {
                // Password updated successfully
                System.out.println("new password servlet ended");
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            } else {
                // Failed to update password
                request.setAttribute("message", "Failed to update password. Please try again.");
                request.getRequestDispatcher("/newPassword.jsp").forward(request, response);
            }
        } else {
            // Passwords do not match, set an attribute to display an error message
            request.setAttribute("message", "Passwords do not match. Please try again.");
            // Forward back to the password reset page
            request.getRequestDispatcher("/newPassword.jsp").forward(request, response);
        }
    }
}
