package com.dunder.mifflin.paper.dunderSys.dto.response

/**
 * DTO utilizado para enviar información al cliente
 * cuando un usuario cierra sesión en el sistema.
 *
 * ---------------------------------------------------------
 * ¿Qué representa este objeto?
 * ---------------------------------------------------------
 *
 * Representa la respuesta (response body) de un endpoint HTTP.
 *
 * En este caso:
 *
 *   POST /usuarios/logout
 *
 * Ejemplo de respuesta JSON:
 *
 * {
 *   "userId": "3456",
 *   "logoutDateTime": "2026-02-17T22:10:30"
 * }
 *
 * ---------------------------------------------------------
 * ¿Por qué es un DTO de respuesta?
 * ---------------------------------------------------------
 *
 * Porque su única responsabilidad es transportar datos
 * desde el sistema hacia el cliente.
 *
 * No contiene lógica de negocio.
 * No representa una entidad de base de datos.
 * No es el modelo interno del sistema.
 *
 * Simplemente define qué información queremos exponer externamente.
 */
data class LogoutResponse(

    /**
     * Identificador del usuario que cerró sesión.
     */
    val userId: String,

    /**
     * Fecha y hora en que se realizó el logout.
     *
     * Se envía como String para facilitar su serialización a JSON.
     * En sistemas más avanzados podría enviarse como objeto Date
     * con un formato estándar ISO-8601.
     */
    val logoutDateTime: String
)
