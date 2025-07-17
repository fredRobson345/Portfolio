<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $email = $_POST["email"];
    $password = $_POST["password"];

    // Dummy authentication (Replace with database validation)
    if ($email == "test@example.com" && $password == "password123") {
        echo "Login successful!";
    } else {
        echo "Invalid email or password.";
    }
}
?>
