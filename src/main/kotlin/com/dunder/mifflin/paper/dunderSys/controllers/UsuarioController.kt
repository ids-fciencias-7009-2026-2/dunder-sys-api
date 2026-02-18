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

@Controller
@RequestMapping("/usuarios")
class UsuarioController {

    val logger: Logger = LoggerFactory.getLogger(UsuarioController::class.java)

    // http://localhost:8080/usuario/me (GET)
    @GetMapping("/me")
    fun retrieveUsuario(): ResponseEntity<Usuario> {
        val usuarioFake = Usuario(id = "3456", nombre = "Luis Gerardo", email = "some-email@gmail.com")
        logger.info("User found in database: $usuarioFake")
        return ResponseEntity.ok(usuarioFake)
    }

    // http://localhost:8080/usuario/register (POST)
    @PostMapping("/register")
    fun agregaUsuario(@RequestBody createUsuarioRequest: CreateUsuarioRequest): ResponseEntity<Usuario> {
        val usuarioParaAgregar = createUsuarioRequest.toUsuario()
        logger.info("Usuario para agregar: $usuarioParaAgregar")
        return ResponseEntity.ok(usuarioParaAgregar)
    }

    //http://localhost:8080/usuario/login (POST)
    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<Any> {
        val usuarioFake = Usuario("un-id", "un nombre", "un email aqui", "Test123.")
        logger.info("try make login with: $loginRequest")

        if (usuarioFake.password == loginRequest.password) {
            logger.info("Login successful")
            return ResponseEntity.ok(Any())
        } else {
            logger.error("Login failed")
            return ResponseEntity.status(401).build() // 401 unauthorized
        }
    }

    //http://localhost:8080/usuario/logout (POST)
    @PostMapping("/logout")
    fun logout(): ResponseEntity<Any> {
        val usuarioFake = Usuario(id = "3456", nombre = "Luis Bernabe", email = "some-email")
        val logoutResponse = LogoutResponse(usuarioFake.id, LocalDateTime.now().toString())

        return ResponseEntity.ok(logoutResponse)
    }

    //http://localhost:8080/usuario (PUT)
    @PutMapping
    fun updateInfoUsuario(@RequestBody updateUsuarioRequest: UpdateUsuarioRequest): ResponseEntity<Any> {
        val usuarioFake = Usuario(id = "3456", nombre = "Luis Bernabe", email = "some-email")
        logger.info("usuario encontrado: $usuarioFake")
        logger.info("Info a actualizar ${updateUsuarioRequest}")

        usuarioFake.password = updateUsuarioRequest.password
        usuarioFake.email = updateUsuarioRequest.email

        return ResponseEntity.ok(usuarioFake)
    }
}