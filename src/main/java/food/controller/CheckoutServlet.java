package food.controller;

// Import statements
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import food.dao.OrderDAO;
import food.daoImp.OrderDAOImpl;
import food.moduels.Cart;
import food.moduels.CartItem;
import food.moduels.Order;
import food.moduels.User;
import food.utility.OrderConfirmationEmail;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private OrderDAO orderDAO;

    @Override
    public void init() {
        orderDAO = new OrderDAOImpl(); // You need to implement OrderDAO and OrderDAOImpl
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        User user = (User) session.getAttribute("User");

        if (cart != null && user != null && !cart.getItems().isEmpty()) {
            // Extract checkout form data
            String paymentMethod = request.getParameter("paymentMethod");
            String deliveryAddress = request.getParameter("address");
            String phoneNumber = request.getParameter("phoneNumber");
            String location = request.getParameter("location");
            System.out.println("location:"+location );
            // Create and populate the order object
            Order order = new Order();
            order.setUserId(user.getUserId());
            order.setRestaurantId((int) session.getAttribute("restaurantId"));
            order.setOrderDate(new Date());
            order.setPaymentMethod(paymentMethod);
            order.setStatus("Pending");

            // Add cart items to the order and calculate the total amount
            double totalAmount = 0;
            StringBuilder orderDetails = new StringBuilder("Order details:\n");
            for (CartItem item : cart.getItems().values()) {
                totalAmount += item.getPrice() * item.getQuantity();
                orderDetails.append(item.getItemName())
                        .append(" - Quantity: ").append(item.getQuantity())
                        .append(", Price: $").append(item.getPrice())
                        .append("\n");
            }
            order.setTotalAmount(totalAmount);

            // Save the order to the database
            int orderId = orderDAO.addOrder(order);

            if (orderId > 0) {
                // Get user email from User object
                String userEmail = user.getEmail(); // Assuming you have a getEmail() method in your User class

                // Prepare email details
                String emailSubject = "Order Confirmation";
                String emailBody = "Thank you for your order!\n\n" +
                        orderDetails.toString() +
                        "\nTotal Amount: $" + totalAmount +
                        "\n\nPayment Method: " + paymentMethod +
                        "\nDelivery Address: " + deliveryAddress +
                        "\nDelivery Time: " + "15 Minutes" +
                        "\nPhone Number: " + phoneNumber;

                // Send order confirmation email
                OrderConfirmationEmail.sendOrderConfirmationEmail(userEmail, emailSubject, emailBody);
            }

            // Clear the cart
             session.removeAttribute("cart");

            // Set order details in the session for the confirmation page
            session.setAttribute("order", order);

            // Redirect to the order confirmation page
            response.sendRedirect("orderConformation.jsp");
        } else {
            // Handle the case where the cart is empty or user is not logged in
            response.sendRedirect("cart.jsp");
        }
    }
}
