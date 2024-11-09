<?php

    session_start();

    if(!isset($_SESSION['rol'])){
        header('location: login.php');
    }else{
        if($_SESSION['rol'] != 1){
            header('location: login.php');
        }
    }
?>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Bufete de Abogados Valbuena - Administrador</title>
    <link rel="stylesheet" href="css/styles.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.5.0/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        /* Imagen de fondo en movimiento */
        body {
            background: url('Images/fondo.jpg') no-repeat center center fixed;
            background-size: cover;
            animation: movimientoFondo 10s infinite linear;
        }

        /* Ajuste del cuadro de bienvenida */
        .alert {
            margin-top: 20px;
            max-width: 800px;
            background-color: rgba(255, 255, 255, 0.9);
        }
    </style>
</head>
<body>
    <header class="bg-dark text-white text-center py-3">
        <h1 style="margin-bottom: 2rem;">Bienvenido ADMIN</h1>
        <nav>
            <ul class="nav nav-pills nav-fill justify-content-center">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="Administrador.php">
                        <i class="bi bi-house"></i> Inicio
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="creaCa.php">
                        <i class="bi bi-folder-plus"></i> Creación y asignación de casos
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="seguimiento.php">
                        <i class="bi bi-binoculars"></i> Seguimiento de casos
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="regiUser.php">
                        <i class="bi bi-person-plus"></i> Registro de usuarios
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="gesArchi.php">
                        <i class="bi bi-file-earmark-text"></i> Gestión de documentos
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="mCap.php">
                        <i class="bi bi-book"></i> Módulo de capacitación
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="logout.php">
                        <i class="bi bi-door"></i> Cerrar sesión
                    </a>
                </li>
            </ul>
        </nav>
    </header>

    <!-- Cuadro informativo -->
    <div class="alert alert-primary shadow-lg rounded p-4 mx-auto mt-4">
        <h4 class="alert-heading text-center">Bienvenido, usuario administrador</h4>
        <p class="mt-3 text-center">En esta sección podrás realizar las siguientes acciones:</p>
        <ul class="list-group list-group-flush text-center">
            <li class="list-group-item"><i class="bi bi-folder-plus"></i> Crear y asignar casos a los abogados que se necesiten.</li>
            <li class="list-group-item"><i class="bi bi-journal-check"></i> Dar seguimiento al avance de los casos asignados y/o abiertos.</li>
            <li class="list-group-item"><i class="bi bi-person-plus-fill"></i> Registrar usuarios con rol de secretaria/o, auxiliar, abogado, y usuario, además de crear otros ADMIN.</li>
            <li class="list-group-item"><i class="bi bi-file-earmark-text-fill"></i> Gestionar documentos.</li>
            <li class="list-group-item"><i class="bi bi-upload"></i> Subir archivos al módulo de capacitación para que tus abogados utilicen este material de apoyo.</li>
        </ul>
    </div>

    <footer class="bg-dark text-white text-center py-3 mt-5">
        <p>Valbuena abogados &copy; 2024</p>
    </footer>
</body>
</html>
