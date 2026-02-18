package com.dunder.mifflin.paper.dunderSys.controllers

import com.dunder.mifflin.paper.dunderSys.domain.Usuario
import com.dunder.mifflin.paper.dunderSys.domain.toUsuario
import com.dunder.mifflin.paper.dunderSys.dto.request.CreateUsuarioRequest
import com.dunder.mifflin.paper.dunderSys.dto.request.LoginRequest
import com.dunder.mifflin.paper.dunderSys.dto.request.UpdateUsuarioRequest
import com.dunder.mifflin.paper.dunderSys.dto.response.LogoutResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

/**
 * Controlador encargado de exponer los endpoints REST relacionados
 * con la gestión de usuarios.
 *
 * Esta clase representa la capa más externa del sistema y su función principal
 * es recibir las peticiones HTTP, transformarlas en objetos Kotlin y devolver
 * respuestas HTTP adecuadas.
 *
 * En esta versión inicial, se utilizan datos "fake" para simular
 * el comportamiento del sistema, sin conexión real a base de datos.
 */
@Controller
@RequestMapping("/usuarios") // Prefijo base para todos los endpoints de este controlador
class UsuarioController {

    /**
     * Logger para registrar eventos importantes del flujo de ejecución.
     *
     * Permite imprimir información útil en consola para depuración,
     * auditoría y análisis del comportamiento del sistema.
     */
    val logger: Logger = LoggerFactory.getLogger(UsuarioController::class.java)

    /**
     * Endpoint que devuelve la información del usuario autenticado.
     *
     * En esta versión de prueba se retorna un usuario "fake" con datos simulados.
     *
     * URL:    http://localhost:8080/usuarios/me
     * Metodo: GET
     *
     * @return ResponseEntity con un objeto Usuario y código HTTP 200 (OK).
     */
    @GetMapping("/me")
    fun retrieveUsuario(): ResponseEntity<Usuario> {

        // Simulación de usuario obtenido desde base de datos
        val usuarioFake = Usuario(
            id = "3456",
            nombre = "Luis Gerardo",
            email = "some-email@gmail.com"
        )

        logger.info("User found in database: $usuarioFake")

        // Retorna HTTP 200 junto con el usuario encontrado
        return ResponseEntity.ok(usuarioFake)
    }

    /**
     * Endpoint encargado de registrar un nuevo usuario.
     *
     * Recibe un JSON con los datos necesarios para crear el usuario
     * y los transforma en un objeto de dominio.
     *
     * URL:    http://localhost:8080/usuarios/register
     * Metodo: POST
     *
     * @param createUsuarioRequest DTO que representa el body del request.
     * @return ResponseEntity con el usuario creado y código HTTP 200 (OK).
     */
    @PostMapping("/register")
    fun agregaUsuario(
        @RequestBody createUsuarioRequest: CreateUsuarioRequest
    ): ResponseEntity<Usuario> {

        // Conversión de DTO a objeto de dominio usando una extension function
        val usuarioParaAgregar = createUsuarioRequest.toUsuario()

        logger.info("Usuario para agregar: $usuarioParaAgregar")

        // En esta etapa no se guarda en BD, solo se simula la creación
        return ResponseEntity.ok(usuarioParaAgregar)
    }

    /**
     * Endpoint que simula el proceso de autenticación de un usuario.
     *
     * Recibe correo y contraseña y los compara contra un usuario ficticio.
     *
     * URL:    http://localhost:8080/usuarios/login
     * Metodo: POST
     *
     * @param loginRequest DTO con las credenciales del usuario.
     * @return HTTP 200 si las credenciales son correctas,
     *         HTTP 401 si son incorrectas.
     */
    @PostMapping("/login")
    fun login(
        @RequestBody loginRequest: LoginRequest
    ): ResponseEntity<Any> {

        // Usuario simulado obtenido del sistema
        val usuarioFake = Usuario(
            "un-id",
            "un nombre",
            "un email aqui",
            "Test123."
        )

        logger.info("try make login with: $loginRequest")

        return if (usuarioFake.password == loginRequest.password) {
            logger.info("Login successful")

            // HTTP 200 → autenticación exitosa
            ResponseEntity.ok(Any())

        } else {
            logger.error("Login failed")

            // HTTP 401 → Unauthorized (credenciales inválidas)
            ResponseEntity.status(401).build()
        }
    }

    /**
     * Endpoint que simula el cierre de sesión del usuario.
     *
     * Genera una respuesta con el identificador del usuario
     * y la fecha/hora del logout.
     *
     * URL:    http://localhost:8080/usuarios/logout
     * Metodo: POST
     *
     * @return ResponseEntity con información del logout.
     */
    @PostMapping("/logout")
    fun logout(): ResponseEntity<Any> {

        val usuarioFake = Usuario(
            id = "3456",
            nombre = "Luis Bernabe",
            email = "some-email"
        )

        val logoutResponse = LogoutResponse(
            usuarioFake.id,
            LocalDateTime.now().toString()
        )

        return ResponseEntity.ok(logoutResponse)
    }

    /**
     * Endpoint que simula la actualización de la información del usuario.
     *
     * Permite modificar correo y contraseña.
     *
     * URL:    http://localhost:8080/usuarios
     * Metodo: PUT
     *
     * @param updateUsuarioRequest DTO con los nuevos datos.
     * @return ResponseEntity con el usuario actualizado.
     */
    @PutMapping
    fun updateInfoUsuario(
        @RequestBody updateUsuarioRequest: UpdateUsuarioRequest
    ): ResponseEntity<Any> {

        val usuarioFake = Usuario(
            id = "3456",
            nombre = "Luis Bernabe",
            email = "some-email"
        )

        logger.info("usuario encontrado: $usuarioFake")
        logger.info("Info a actualizar: $updateUsuarioRequest")

        // Simulación de actualización de datos
        usuarioFake.password = updateUsuarioRequest.password
        usuarioFake.email = updateUsuarioRequest.email

        return ResponseEntity.ok(usuarioFake)
    }
}