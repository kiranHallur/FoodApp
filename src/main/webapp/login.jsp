<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login Page</title>

<!-- Bootstrap CSS (assuming you have Bootstrap included) -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://unpkg.com/sweetalert/dist/sweetalert.css">
<!-- Your custom CSS styles can be added here -->
<style>
body {
	background-color: #f8f9fa;
}

.container {
	max-width: 400px;
	margin-top: 50px;
	background-color: #ffffff;
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

h2 {
	text-align: center;
	margin-bottom: 20px;
}

form {
	margin-top: 20px;
}

button {
	width: 100%;
}
</style>
</head>
<body>
	<%@ include file="main.jsp"%>

	<input type="hidden" id="status"
		value="<%=request.getAttribute("status")%>">
	<div class="container">  
		<h2>Login</h2>
		<form action="UserServlet" method="post">
			<div class="form-group">
				<label for="username">Username:</label> <input type="text"
					class="form-control" id="username" name="username" required>
			</div>
			<div class="form-group">
				<label for="password">Password:</label> <input type="password"
					class="form-control" id="password" name="password" required>
			</div>

			<!-- Additional input for OTP -->
			<div class="form-group" id="otpDiv" style="display:none;">
				<label for="otp">Enter OTP:</label>
				<input type="text" class="form-control" id="otp" name="otp">
			</div>

			<button type="submit" class="btn btn-primary" name="action" value="login">Login</button>
		</form>
		<a href="forgotPassword.jsp">Forgot Password..!</a>
	</div>

	<!-- Bootstrap JS (assuming you have Bootstrap included) -->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.0.7/dist/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

	<!-- Include SweetAlert JS -->
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

	<script type="text/javascript">
		document.addEventListener("DOMContentLoaded", function() {
			var status = document.getElementById("status").value;
			if (status == "success") {
				// Use swal function from SweetAlert
				swal("Congrats", "Login successfully", "success").then(
						function() {
							// Redirect to the index page
							window.location.href = "RestaurantServlet";
						});
			}
			if (status == "failed") {
				// Use swal function from SweetAlert
				swal("Oops", "Login Failed", "error");
			}

			// Check if OTP verification is required
			var otpRequired = "<%=request.getAttribute("otpRequired")%>";
			if (otpRequired === "true") {
				// Display the OTP input field
				document.getElementById("otpDiv").style.display = "block";
			}
		});
	</script>
</body>
</html>
