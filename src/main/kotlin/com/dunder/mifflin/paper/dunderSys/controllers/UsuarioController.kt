package com.dunder.mifflin.paper.dunderSys.controllers

import com.dunder.mifflin.paper.dunderSys.domain.Usuario
import com.dunder.mifflin.paper.dunderSys.domain.toUsuario
import com.dunder.mifflin.paper.dunderSys.dto.request.CreateUsuarioRequest
import com.dunder.mifflin.paper.dunderSys.dto.request.LoginRequest
import com.dunder.mifflin.paper.dunderSys.dto.request.UpdateUsuarioRequest
import com.dunder.mifflin.paper.dunderSys.dto.response.LoginResponse
import com.dunder.mifflin.paper.dunderSys.dto.response.LogoutResponse
import com.dunder.mifflin.paper.dunderSys.services.UsuarioService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import java.security.MessageDigest
import java.time.LocalDateTime
import java.util.UUID

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

    @Autowired
    lateinit var usuarioService: UsuarioService

    /**
     * stateless
     */


    /**
     * Logger para registrar eventos importantes del flujo de ejecución.
     *
     * Permite imprimir información útil en consola para depuración,
     * auditoría y análisis del comportamiento del sistema.
     */
    val logger: Logger = LoggerFactory.getLogger(UsuarioController::class.java)

    val activeTokens = mutableSetOf<String>()

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
    fun retrieveUsuario(
        @RequestHeader("Authorization", required = false) token: String?
    ): ResponseEntity<Usuario> {

        logger.info("Token recibido: $token")
        // Busca en la BD el usuario donde el usuario.id = token

        val userFound = usuarioService.findByToken(token.orEmpty())
        if (token == null || (userFound == null)) {
            return ResponseEntity.status(401).build()
        }

        logger.info("User found in service: $userFound")

        // Retorna HTTP 200 junto con el usuario encontrado
        return ResponseEntity.ok(userFound)
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

        val password = hashPassword(createUsuarioRequest.password)
        usuarioParaAgregar.password = password

        usuarioService.addNewUsuario(usuarioParaAgregar)

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

        val passwordHash = hashPassword(loginRequest.password)
        logger.info("password from request: $passwordHash")

        val userFound = usuarioService.login(loginRequest.email, passwordHash)

        logger.info("try make login with: $loginRequest")
        logger.info("user password: ${userFound?.password}")

        return if (userFound != null) {
            ResponseEntity.ok(LoginResponse(userFound.token.orEmpty()))
        } else {
            ResponseEntity.status(401).build()
        }
    }

    fun hashPassword(password: String): String {
        val bytes = MessageDigest
            .getInstance("SHA-256")
            .digest(password.toByteArray())
        return bytes.joinToString("") { "%02x".format(it) }
    }

    fun tokenGenerator(): String {
        val token = UUID.randomUUID().toString()
        return token
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
    fun logout(
        @RequestHeader("Authorization") token: String
    ): ResponseEntity<Any> {

        activeTokens.remove(token)

        val usuarioFake = Usuario(
            id = "3456",
            nombre = "Luis Bernabe",
            email = "some-email",
            edad = 29
        )

        val logoutResponse = LogoutResponse(
            usuarioFake.id ?: "N/A",
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
            email = "some-email",
            edad = 29
        )

        logger.info("usuario encontrado: $usuarioFake")
        logger.info("Info a actualizar: $updateUsuarioRequest")

        // Simulación de actualización de datos
        usuarioFake.password = updateUsuarioRequest.password
        usuarioFake.email = updateUsuarioRequest.email

        return ResponseEntity.ok(usuarioFake)
    }


    /**
     * Endpoint que simula la obtención de un usuario específico
     * a partir de su identificador.
     *
     * Este método ejemplifica el uso de @PathVariable para capturar
     * valores directamente desde la URL.
     *
     * URL:    http://localhost:8080/usuarios/{id}
     * Metodo: GET
     *
     * Ejemplo:
     * GET /usuarios/123
     *
     * En este caso, el valor "123" será asignado automáticamente
     * al parámetro 'id'.
     *
     * @param token Header Authorization recibido en la petición.
     *              En una implementación real se validaría para
     *              verificar autenticación.
     *
     * @param id Identificador del usuario recibido desde la URL.
     *
     * @param updateUsuarioRequest (Uso didáctico)
     *              Se incluye para mostrar que Spring puede mapear
     *              múltiples fuentes de datos, aunque en un diseño
     *              REST correcto un método GET no debería recibir body.
     *
     * @return ResponseEntity con un mensaje que incluye el id recibido
     *         y código HTTP 200 (OK).
     */
    @GetMapping("/{id}")
    fun getUsuarioById(
        @RequestHeader("Authorization") token: String?,
        @PathVariable id: String,
        @RequestBody updateUsuarioRequest: UpdateUsuarioRequest
    ): ResponseEntity<String> {

        /*if (token==null||!activeTokens.contains(token)) {
            return ResponseEntity.status(401).build()
        }*/

        return ResponseEntity.ok("Usuario con id: $id")
    }


    /**
     * Endpoint que simula la búsqueda de usuarios utilizando
     * múltiples filtros enviados como parámetros en la URL.
     *
     * Este método introduce el uso de @RequestParam para capturar
     * query parameters.
     *
     * URL:    http://localhost:8080/usuarios/buscar
     * Metodo: GET
     *
     * Ejemplo:
     * GET /usuarios/buscar?email=test@gmail.com&cp=56530&edad=25&estado=MX
     *
     * Spring asigna automáticamente cada parámetro al argumento
     * correspondiente y realiza conversión de tipos cuando es necesario.
     *
     * @param token Header Authorization (simulado).
     *              En un sistema real se validaría antes de permitir la búsqueda.
     *
     * @param email Correo electrónico utilizado como filtro.
     * @param cp Código postal utilizado como filtro.
     * @param edad Edad del usuario (Spring convierte automáticamente el valor a Int).
     * @param estado Estado o entidad federativa como criterio de búsqueda.
     *
     * @return ResponseEntity con un mensaje que contiene los filtros recibidos
     *         y código HTTP 200 (OK).
     */
    @GetMapping("/buscar")
    fun buscarUsuario(
        @RequestHeader("Authorization") token: String?,
        @RequestParam email: String,
        @RequestParam cp: String,
        @RequestParam edad: Int,
        @RequestParam estado: String
    ): ResponseEntity<String> {
        return ResponseEntity.ok("Buscando usuario con email: $email - $cp - $edad - $estado")
    }


    @GetMapping("/all")
    fun retrieveAllUsuarios(): ResponseEntity<List<Usuario>> {
        val allUsers = usuarioService.searchAllUsuarios()
        return ResponseEntity.ok(allUsers)
    }
}