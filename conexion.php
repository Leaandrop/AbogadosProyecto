<?php
$host = 'localhost';
$db = 'bufete_abogados'; // Nombre de tu base de datos
$user = 'root'; // Usuario de MySQL
$password = ''; // Contraseña de MySQL (en XAMPP usualmente está vacía)

$conexion = new mysqli($host, $user, $password, $db);

if ($conexion->connect_error) {
    die("Error de conexión: " . $conexion->connect_error);
}
?>
