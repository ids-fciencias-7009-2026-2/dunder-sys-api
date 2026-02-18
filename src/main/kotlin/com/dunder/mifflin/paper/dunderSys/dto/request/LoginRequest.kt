package com.dunder.mifflin.paper.dunderSys.dto.request

/**
 * DTO (Data Transfer Object) utilizado para recibir las credenciales
 * de un usuario cuando intenta autenticarse en el sistema.
 *
 * ---------------------------------------------------------
 * ¿Qué representa este objeto?
 * ---------------------------------------------------------
 *
 * Representa el cuerpo (body) de una petición HTTP POST
 * hacia el endpoint:
 *
 *   POST /usuarios/login
 *
 * Ejemplo de JSON:
 *
 * {
 *   "email": "usuario@email.com",
 *   "password": "Test123."
 * }
 *
 * Spring convierte automáticamente ese JSON en una instancia
 * de esta clase cuando se usa @RequestBody en el Controller.
 *
 * ---------------------------------------------------------
 * ¿Por qué es un DTO?
 * ---------------------------------------------------------
 *
 * Porque únicamente transporta datos desde el cliente hacia el sistema.
 * No contiene lógica de negocio.
 * No valida credenciales.
 * No accede a base de datos.
 *
 * La validación ocurre posteriormente en la capa de servicio.
 */
data class LoginRequest(

    /**
     * Correo electrónico del usuario que intenta autenticarse.
     */
    val email: String,

    /**
     * Contraseña ingresada por el usuario.
     *
     * En un sistema real, nunca debería almacenarse o compararse
     * en texto plano. Se utilizaría hashing y mecanismos de seguridad.
     */
    val password: String
)
