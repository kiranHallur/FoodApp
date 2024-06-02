package food.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import food.utility.OtpGenrater;

@WebServlet("/GenrateOtp")
public class GenrateOtp extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Extract email from the form
            System.out.println("GenrateOtp servlet started....!");
            String email = request.getParameter("email");
            System.out.println(email);

            // Generate OTP
            String generatedOtp = OtpGenrater.generateOtp();
            System.out.println("generatedOtp: " + generatedOtp);

            // Store OTP in the session for verification
            HttpSession session = request.getSession();
            session.setAttribute("generatedOtp", generatedOtp);
            session.setAttribute("userEmail", email);

            // Send OTP via email
            OtpGenrater.sendOtp(email, generatedOtp);

            System.out.println("GenrateOtp servlet ended....!");

            // Forward to the OTP entry page with a success message
            request.setAttribute("status", "success");
            
            // Forward to the OTP entry page
            RequestDispatcher dispatcher = request.getRequestDispatcher("/forgotPassword.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            // Forward to the OTP entry page with an error message
            request.setAttribute("status", "error");
            
            // Forward to the OTP entry page
            RequestDispatcher dispatcher = request.getRequestDispatcher("/forgotPassword.jsp");
            dispatcher.forward(request, response);
        }
    }
}
