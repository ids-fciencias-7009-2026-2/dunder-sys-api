package com.dunder.mifflin.paper.dunderSys.domain

/**
 * Representa el modelo de dominio "Usuario" dentro del sistema.
 *
 * ---------------------------------------------------------
 * ¿Qué es un modelo de dominio?
 * ---------------------------------------------------------
 *
 * Es la representación interna de una entidad del negocio.
 * Contiene los datos con los que el sistema trabaja realmente.
 *
 * No es:
 *  - Un DTO (no representa directamente un JSON del cliente).
 *  - Una entidad de base de datos (no tiene anotaciones JPA).
 *
 * Es el modelo que vive en la lógica del sistema.
 *
 * ---------------------------------------------------------
 * Diferencia con otros objetos:
 * ---------------------------------------------------------
 *
 * DTO:
 *   Se usa para transportar datos desde/hacia el cliente.
 *
 * Entidad (JPA):
 *   Representa una tabla en la base de datos.
 *
 * Dominio (esta clase):
 *   Representa el concepto real del negocio dentro del sistema.
 *
 * ---------------------------------------------------------
 * ¿Por qué password es nullable?
 * ---------------------------------------------------------
 *
 * Porque no siempre necesitamos exponer o manipular la contraseña.
 * Por ejemplo:
 *  - Al devolver un usuario en una respuesta HTTP,
 *    normalmente no enviamos el password.
 *
 * Kotlin permite usar "?" para indicar que un valor puede ser null.
 */
data class Usuario(

    /**
     * Identificador único del usuario.
     * En un sistema real, normalmente lo genera la base de datos.
     */
    val id: String,

    /**
     * Nombre del usuario.
     * Se define como "var" porque puede actualizarse.
     */
    var nombre: String,

    /**
     * Correo electrónico del usuario.
     * También puede actualizarse.
     */
    var email: String,

    /**
     * Contraseña del usuario.
     * Es opcional (nullable) y no siempre se utiliza.
     */
    var password: String? = null
)
