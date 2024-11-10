document.getElementById("crearCasoForm").addEventListener("submit", function(event) {
    event.preventDefault(); // Prevenir recarga de la página
    
    const nombreCaso = document.getElementById("nombreCaso").value;
    const descripcion = document.getElementById("descripcion").value;
    const fechaInicio = document.getElementById("fechaInicio").value;
    const estado = document.getElementById("estado").value;
    const idAbogado = document.getElementById("idAbogado").value;

    // Realizar la solicitud POST al backend
    fetch("/CrearCasoServlet", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        },
        body: `nombreCaso=${encodeURIComponent(nombreCaso)}&descripcion=${encodeURIComponent(descripcion)}&fechaInicio=${encodeURIComponent(fechaInicio)}&estado=${encodeURIComponent(estado)}&idAbogado=${encodeURIComponent(idAbogado)}`
    })
    .then(response => {
        if (!response.ok) {
            // Si el servidor responde con un código de error HTTP
            throw new Error(`Error del servidor: ${response.status} ${response.statusText}`);
        }
        return response.json();
    })
    .then(data => {
        const messageElement = document.getElementById("message");
        if (data.status === "success") {
            messageElement.innerHTML = "<div class='alert alert-success'>Caso creado exitosamente.</div>";
            document.getElementById("crearCasoForm").reset(); // Limpiar el formulario
        } else {
            messageElement.innerHTML = "<div class='alert alert-danger'>Error al crear el caso en la base de datos.</div>";
        }
    })
    .catch(error => {
        // Manejo de errores de conexión y otros errores inesperados
        const messageElement = document.getElementById("message");

        if (error.message.includes("Failed to fetch")) {
            // Error de conexión
            messageElement.innerHTML = "<div class='alert alert-danger'>No se pudo conectar al servidor. Verifica tu conexión o intenta más tarde.</div>";
        } else if (error.message.includes("Error del servidor")) {
            // Error del servidor específico (por ejemplo, 500 Internal Server Error)
            messageElement.innerHTML = `<div class='alert alert-danger'>${error.message}</div>`;
        } else {
            // Error inesperado
            messageElement.innerHTML = "<div class='alert alert-danger'>Ocurrió un error inesperado. Por favor, intenta de nuevo.</div>";
        }
        
        console.error("Error:", error); // Mostrar el error en la consola para depuración
    });
});
