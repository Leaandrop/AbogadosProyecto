<?php
    include_once 'database.php';

    session_start();

    if(isset($_GET['cerrar_sesion'])){
        session_unset();

        session_destroy();
    }

    if(isset($_SESSION['rol'])){
        switch($_SESSION['rol']){
            case 1:
                header('location: Administrador.php');
            break;

            case 2:
            header('location: colab.php');
            break;

            default:
        }
    }

    if(isset($_POST['username']) && isset($_POST['password'])){
        $username = $_POST['username'];
        $password = $_POST['password'];

        $db = new Database();
        $query = $db->connect()->prepare('SELECT*FROM usuarios WHERE username = :username AND password = :password');
        $query->execute(['username' => $username, 'password' => $password]);

        $row = $query->fetch(PDO::FETCH_NUM);
        if($row == true){
            // validar rol
            $rol = $row[3];
            $_SESSION['rol'] = $rol;

            switch($_SESSION['rol']){
                case 1:
                    header('location: Administrador.php');
                break;
    
                case 2:
                header('location: colab.php');
                break;
    
                default:
            }
        }else{
            // no existe el usuario
            echo "El usuario o contraseña son incorrectos";
        }

    }
    

?>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Bufete de Abogados - Acceso</title>
        <link rel="stylesheet" href="css/styles.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.5.0/font/bootstrap-icons.css" rel="stylesheet">
    </head>
    <body>
        <header class="bg-dark text-white text-center py-3">
            <h1 style="margin-bottom: 2rem;">Acceso a la Plataforma</h1>
            <nav>
                <ul class="nav nav-pills nav-fill justify-content-center">
                    <li class="nav-item"><a class="nav-link" href="index.html"><i class="bi bi-house"></i> Inicio</a></li>
                    <li class="nav-item"><a class="nav-link" href="servicios.html"><i class="bi bi-briefcase"></i> Servicios</a></li>
                    <li class="nav-item"><a class="nav-link" href="contacto.html"><i class="bi bi-envelope"></i> Contacto</a></li>
                    <li class="nav-item"><a class="nav-link active" aria-current="page" href="Acceso.html"><i class="bi bi-box-arrow-in-right"></i> Acceso</a></li>
                </ul>
            </nav>
        </header>

        <section class="container my-5">
            <div class="row align-items-center">
                <div class="col-md-6 text-center">
                    <img src="Images/logoempresarial.jpg" class="img-fluid" alt="Logo Empresarial">
                </div>
                
                <div class="col-md-6">
                    <h2 class="text-center mb-4">Iniciar Sesión</h2>
                    <div class="alert alert-info text-center">
                        <strong>Nota Importante:</strong> Dependiendo de tu rol, tendrás acceso a diferentes funcionalidades dentro de la plataforma.
                    </div>
                    <form action="Administrador.php" method="POST" class="row g-3">
                        <div class="col-md-12">
                            <label for="email" class="form-label">Correo Electrónico:</label>
                            <input type="email" class="form-control" id="email" name="email" required>
                        </div>
                        <div class="col-md-12">
                            <label for="password" class="form-label">Contraseña:</label>
                            <input type="password" class="form-control" id="password" name="password" required>
                        </div>
                        <div class="col-12 text-center">
                            <button type="submit" class="btn btn-primary mt-3">Iniciar Sesión</button>
                        </div>
                    </form>
                </div>
            </div>
        </section>

        <footer class="bg-dark text-white text-center py-3">
            <p>Valbuena abogados &copy; 2024</p>
        </footer>
    </body>
</html>
