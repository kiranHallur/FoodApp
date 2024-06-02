<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map, food.moduels.Cart, food.moduels.CartItem"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Shopping Cart</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: 'Arial', sans-serif;
        }

        .container {
            margin-top: 20px;
        }

        .main {
            display: flex;
            justify-content: space-between;
            align-items: flex-start;
            flex-wrap: wrap;
        }

        .product-details {
            flex: 0 0 48%;
            margin-bottom: 20px;
        }

        .cart-summary {
            flex: 0 0 48%;
            margin-top: 20px;
        }

        .card {
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border: none;
        }

        .btn-primary {
            background-color: #007bff;
            border: none;
            transition: background-color 0.3s ease;
        }

        .btn-primary:hover {
            background-color: #0056b3;
        }

        /* Additional Styles for Better Organization */
        .mb-4 {
            margin-bottom: 1.5rem;
        }

        .add-more-items-btn {
            margin-top: 1rem;
        }

        .proceed-to-checkout-btn {
            margin-top: 1.5rem;
        }

        .card-title {
            font-size: 1.2rem;
            color: #333;
            margin-bottom: 10px;
        }

        .card-text {
            font-size: 1rem;
            color: #666;
            margin-bottom: 0;
        }

        .cart-summary-card {
            padding: 15px;
            background-color: #fff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border: none;
        }

        .cart-summary-title {
            font-size: 1.5rem;
            color: #333;
            margin-bottom: 10px;
        }

        .cart-summary-text {
            font-size: 1.2rem;
            color: #666;
            margin-bottom: 0;
        }

        .proceed-to-checkout-btn-summary {
            margin-top: 1rem;
            width: 100%;
        }
    </style>
</head>
<body>
    <%@ include file="/main.jsp"%>

  
    <div class="container">
        <h2 class="mb-4">Shopping Cart</h2>
        <div class="main">
            <!-- Cart Items Column -->
            <div class="product-details">
                <form action="MenuServlet" method="get">
                    <input type="hidden" name="restaurantId" value="<%= session.getAttribute("restaurantId") %>">
                    <input type="hidden" name="action" value="menu">
                    <button type="submit" class="btn btn-primary add-more-items-btn">Add More Items</button>
                </form>

                <% Cart cart = (Cart) session.getAttribute("cart");
                    if (cart != null && !cart.getItems().isEmpty()) {
                        for (CartItem item : cart.getItems().values()) {
                %>
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title"><%=item.getItemName()%></h5>
                        <p class="card-text">&#x20B9;<%=item.getPrice()%></p>
                    </div>

                    <form action="CartServlet" method="get">
                        <input type="hidden" name="itemId" value="<%=item.getItemId()%>">
                        <label> Quantity: <input type="number" name="quantity" value="<%=item.getQuantity()%>"></label>
                        <input type="submit" value="update" name="action" class="btn btn-primary btn-sm">
                        <input type="submit" value="remove" name="action" class="btn btn-danger btn-sm">
                    </form>
                </div>
                <% }
                    } else { %>
                    <p>Your Cart is Empty</p>
                <% } %>
            </div> <!-- Closing tag for the Cart Items Column -->

            <!-- Cart Summary Column -->
            <div class="cart-summary">
                <div class="card cart-summary-card">
                    <div class="card-body">
                        <h5 class="cart-summary-title">Cart Summary</h5>

                        <% 
                        int count = 0;
                        double price = 0;
                        
                        if (cart != null && !cart.getItems().isEmpty()) {
                            for (CartItem item : cart.getItems().values()) {
                                count += item.getQuantity();
                                price += item.getPrice() * item.getQuantity();
                            }
                        %>

                        <p class="cart-summary-text">Total Items: <%= count %></p>
                        <p class="cart-summary-text">Total Price: &#x20B9;<%= price %></p>

                        <% } else { %>

                        <p class="cart-summary-text">Your Cart is Empty</p>

                        <% } %>

                    </div>
                </div>
            </div>
        </div>
		<%
			if(session.getAttribute("cart") !=  null){
				
			
		%>
		
		
        <a href="checkOut.jsp" class="btn btn-primary proceed-to-checkout-btn-summary">Proceed to Checkout</a>
        
        
        <%
			}
        %>
    </div> <!-- Closing tag for the container -->

    <!-- Bootstrap JS (assuming you have Bootstrap included) -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.0.7/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
