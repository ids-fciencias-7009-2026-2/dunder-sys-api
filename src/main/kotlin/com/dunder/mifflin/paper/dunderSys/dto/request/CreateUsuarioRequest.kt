package com.dunder.mifflin.paper.dunderSys.dto.request

/**
 * DTO (Data Transfer Object) utilizado para recibir los datos necesarios
 * para crear un nuevo usuario en el sistema.
 *
 * ---------------------------------------------------------
 * ¿Qué representa este objeto?
 * ---------------------------------------------------------
 *
 * Representa el cuerpo (body) de una petición HTTP POST
 * enviada por el cliente.
 *
 * Ejemplo de JSON que lo construye automáticamente:
 *
 * {
 *   "nombre": "Luis",
 *   "email": "luis@email.com"
 * }
 *
 * Spring convierte automáticamente ese JSON en una instancia
 * de esta clase cuando usamos @RequestBody en el Controller.
 *
 * ---------------------------------------------------------
 * ¿Por qué es un DTO y no un objeto de dominio?
 * ---------------------------------------------------------
 *
 * Porque este objeto solo transporta datos.
 * No contiene lógica de negocio.
 * No representa directamente al modelo interno del sistema.
 *
 * Después de recibirlo, lo convertimos en un objeto de dominio
 * usando la extension function:
 *
 *     createUsuarioRequest.toUsuario()
 *
 * Esto mantiene separadas las responsabilidades del sistema.
 */
data class CreateUsuarioRequest(

    /**
     * Nombre del usuario enviado por el cliente.
     */
    val nombre: String,

    /**
     * Correo electrónico enviado por el cliente.
     */
    val email: String
)
