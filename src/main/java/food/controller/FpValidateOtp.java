package food.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/FpValidateOtp")
public class FpValidateOtp extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ValidateOtp servlet started...!");

        // Retrieve entered OTP from the form
        String enteredOtp = request.getParameter("otp");
        System.out.println(enteredOtp);
        // Retrieve stored OTP from the session
        HttpSession session = request.getSession();
        String storedOtp = (String) session.getAttribute("generatedOtp");
        String userEmail = (String) session.getAttribute("userEmail");
        
        // Check if entered OTP matches the stored OTP
        if (enteredOtp.equals(storedOtp)) {
            // OTP is correct, proceed with the necessary actions
            // Store user email in session for further processing
            session.setAttribute("userEmail", userEmail);
            request.setAttribute("status", "Otpsuccess");
            // Redirect to the password reset page
            System.out.println("ValidateOtp servlet end...!");
            response.sendRedirect(request.getContextPath() + "/newPassword.jsp");
        } else {
        	
        	request.setAttribute("status", "Otpfailed");
            // Incorrect OTP, set an attribute to display an error message
            request.setAttribute("message", "Incorrect OTP. Please try again.");

            // Forward back to the OTP entry page
            System.out.println("ValidateOtp servlet end...!");
            request.getRequestDispatcher("/forgotPassword.jsp").forward(request, response);
        }
    }
}
