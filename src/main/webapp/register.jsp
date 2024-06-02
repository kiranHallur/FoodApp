<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register</title>

    <!-- Bootstrap CSS (assuming you have Bootstrap included) -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://unpkg.com/sweetalert/dist/sweetalert.css">
    <link rel="stylesheet"  href="css/register.css"	>
    <!-- Your custom CSS styles can be added here -->

    <style>
       
    </style>
</head>
<body>
<%@ include file="main.jsp" %>

	<input type="hidden" id="status" value="<%=request.getAttribute("status") %>" >

    <div class="container">
        <div class="card">
            <div class="card-body">
                <h2 class="text-center mb-4">Register</h2>
                
                <form action="UserServlet" method="post">
                    <div class="form-group">
                        <label for="username">Username</label>
                        <input type="text" class="form-control" id="name" name="username" placeholder="Enter your name" required>
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" class="form-control" id="password" name="password" placeholder="Enter your password" required>
                    </div>
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="email" class="form-control" id="email" name="email" placeholder="Enter your email" required>
                    </div>
                    <div class="form-group">
                        <label for="address">Address</label>
                        <input type="text" class="form-control" id="address" name="address" placeholder="Enter your address" required>
                    </div>
					<div class="form-group">
					    <label for="role">Role</label>
					    <select class="form-control" id="role" name="role" required>
					        <option value="RestaurantAdmin">RestaurantAdmin</option>
					        <option value="Customer">Customer</option>
					        <!-- Add more options as needed -->
					    </select>
					</div>
                    
                    <button type="submit" class="btn btn-register btn-block" name="action" value="register">Register</button>
                </form>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS (assuming you have Bootstrap included) -->
  <!-- ... Previous HTML code ... -->

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.0.7/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<!-- Include SweetAlert JS -->
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>


    <!-- Your custom script -->
    <script type="text/javascript">
        document.addEventListener("DOMContentLoaded", function() {
            var status = document.getElementById("status").value;
            if (status == "success") {
                swal("Congrats", "Account created successfully", "success")
                    .then(function() {
                        window.location.href = "login.jsp";
                    });
            }
            if (status == "failed") {
                swal("Oops", " Failed", "error");
            }
        });
    </script>
</body>
</html>


</body>
</html>
