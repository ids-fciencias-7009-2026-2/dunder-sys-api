package com.dunder.mifflin.paper.dunderSys.controllers

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/usuario")
class UsuarioController {

    val logger: Logger = LoggerFactory.getLogger(UsuarioController::class.java)


    //http://gobierno.cdmx.mx/usuario/default
    @GetMapping("/default") // http://localhost:8080/usuario/default (GET)
    fun retrieveUsuario() : ResponseEntity<Usuario> {
        val usuarioFake = Usuario(id = 3456L, nombre = "Luis Gerardo", email = "some-email@gmail.com")
        return ResponseEntity.ok(usuarioFake)
    }
}


data class Usuario(val id: Long, val nombre: String, val email: String)