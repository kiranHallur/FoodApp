<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="food.moduels.Order" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Your existing head content remains unchanged -->
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 0;
            text-align: center;
        }

        h1 {
            color: #007bff;
        }

        .order-details {
            background-color: #ffffff;
            border: 1px solid #ddd;
            padding: 20px;
            margin: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        p {
            font-size: 16px;
            margin: 10px 0;
        }

        a {
            display: inline-block;
            padding: 10px 20px;
            background-color: #007bff;
            color: #ffffff;
            text-decoration: none;
            border-radius: 5px;
            margin-top: 20px;
        }

        a:hover {
            background-color: #0056b3;
        }

        /* Additional CSS for the feedback form */
        .feedback-section {
            margin-top: 20px;
            padding: 20px;
            background-color: #ffffff;
            border: 1px solid #ddd;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: left;
            max-width: 400px;
            margin: 0 auto;
        }

        .feedback-section label {
            display: block;
            margin-bottom: 5px;
        }

        .feedback-section textarea {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            box-sizing: border-box;
        }

        .feedback-section button {
            background-color: #007bff;
            color: #ffffff;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .feedback-section button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <h1>Order Confirmation</h1>

    <% 
        Order order = (Order) session.getAttribute("order");

        if (order != null) {
    %>

    <div class="order-details">
        <p>Thank you for your order!</p>
        <p>Order ID: <%= order.getOrderId() %></p>
        <p>Total Amount: <%= order.getTotalAmount() %></p>
        <p>Status: <%= order.getStatus() %></p>
        <p>Payment Method: <%= order.getPaymentMethod() %></p>

        <!-- Add more details here if needed -->

    </div>

    <!-- Feedback Section -->
    <div class="feedback-section">
        <h2>Provide Feedback</h2>
        <form action="SubmitFeedback" method="post">
            <label for="feedback">Your Feedback:</label>
            <textarea id="feedback" name="feedback" rows="4" required></textarea>
            <button type="submit">Submit Feedback</button>
        </form>
    </div>

    <% } else { %>

    <p>Order details are not available at the moment.</p>

    <% } %>

    <a href="index.jsp">Return to Home</a>

    <!-- Your existing scripts or other body content remains unchanged -->

</body>
</html>
