<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Your existing head section remains unchanged -->
    <!-- Include SweetAlert CSS -->
    <link rel="stylesheet" href="https://unpkg.com/sweetalert/dist/sweetalert.css">
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }

        .container {
            width: 100%;
            max-width: 600px;
            text-align: center;
            padding: 20px;
        }

        .forgot {
            background-color: #fff;
            border: 1px solid #ddd;
            padding: 20px;
            margin-bottom: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .forgot h2 {
            color: #007bff;
        }

        .forgot p {
            margin-bottom: 15px;
        }

        .forgot ol {
            text-align: left;
            padding-left: 20px;
        }

        .forgot li {
            font-size: 16px;
            margin-bottom: 10px;
        }

        .card {
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin-top: 20px;
        }

        .form-group {
            padding: 20px;
        }

        .form-group label {
            margin-bottom: 10px;
            display: block;
            font-weight: bold;
        }

        .form-control {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }

        .form-text {
            font-size: 14px;
        }

        .card-footer {
            padding: 20px;
            text-align: center;
        }

        .btn-success,
        .btn-danger {
            padding: 10px 20px;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .btn-success {
            background-color: #28a745;
        }

        .btn-danger {
            background-color: #dc3545;
        }
    </style>
</head>
<body oncontextmenu='return false' class='snippet-body'>
    <div class="container">
        <div class="forgot">
            <h2>Forgot your password?</h2>
            <p>Change your password in three easy steps. This will help you to secure your password!</p>
            <ol class="list-unstyled">
                <li><span class="text-primary text-medium">1. </span>Enter your email address below.</li>
                <li><span class="text-primary text-medium">2. </span>Our system will send you an OTP to your email</li>
                <li><span class="text-primary text-medium">3. </span>Enter the OTP on the next page</li>
            </ol>
        </div>

        <!-- Form for submitting email to get OTP -->
        <form class="card" action="GenrateOtp" method="post">
            <div class="form-group">
                <label for="email-for-pass">Enter your email address</label>
                <input class="form-control" type="text" name="email" id="email-for-pass" required="">
                <small class="form-text text-muted">Enter the registered email address. Then we'll email an OTP to this address.</small>
            </div>
            <div class="card-footer">
                <button class="btn btn-success" type="submit" onclick="showSuccessAlert()">Get OTP</button>
                <a class="btn btn-danger" href="login.jsp">Back to Login</a>
            </div>
        </form>

        <!-- Form for entering OTP -->
        <form class="card" action="FpValidateOtp" method="post">
            <div class="form-group">
                <label for="otp">Enter OTP</label>
                <input class="form-control" type="text" name="otp" id="otp" required="">
                <small class="form-text text-muted">Enter the OTP you received in your email.</small>
            </div>
            <div class="card-footer">
                <button class="btn btn-success" type="submit" onclick="showSuccessAlert()">Submit OTP</button>
                <a class="btn btn-danger" href="login.jsp">Back to Login</a>
            </div>
        </form>
    </div>
    
    <input type="hidden" id="status" name="status" value="<%=request.getAttribute("status")%>">
    <!-- Include SweetAlert JS -->
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

    <!-- Your existing script tags remain unchanged -->
    <script type="text/javascript">
        document.addEventListener("DOMContentLoaded", function () {
            var status = document.getElementById("status").value;
            if (status == "success") {
                swal("Congrats", "OTP sent successfully to your email.");
            }
            if (status == "failed") {
                swal("Oops", "Error while processing OTP request. Please try again.");
            }
            if (status == "Otpfailed") {
                swal("Oops", "Invalid OTP. Please try again.");
            }
            if (status == "Otpsuccess") {
                swal("Success", "Verified Successfully.");
            }
        });
    </script>
</body>
</html>
