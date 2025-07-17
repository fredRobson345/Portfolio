<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $email = $_POST["email"];

    // Check if email exists in database (dummy check)
    if ($email == "test@example.com") {
        echo "A password reset link has been sent to your email.";
    } else {
        echo "Email not found!";
    }
}
?>
