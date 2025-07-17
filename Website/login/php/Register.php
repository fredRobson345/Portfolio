<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $name = $_POST["name"];
    $email = $_POST["email"];
    $password = $_POST["password"];
    $confirmPassword = $_POST["confirm-password"];

    if ($password !== $confirmPassword) {
        die("Passwords do not match!");
    }

    echo "Account created successfully for $name!";
}
?>
