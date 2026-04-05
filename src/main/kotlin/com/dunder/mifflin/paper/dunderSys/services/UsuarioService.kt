package com.dunder.mifflin.paper.dunderSys.services

import com.dunder.mifflin.paper.dunderSys.domain.Usuario
import com.dunder.mifflin.paper.dunderSys.domain.toUsuario
import com.dunder.mifflin.paper.dunderSys.repositories.UsuarioRepository
import com.dunder.mifflin.paper.dunderSys.repositories.toUsuarioEntity
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UsuarioService {
    val logger = LoggerFactory.getLogger(UsuarioService::class.java)

    @Autowired
    lateinit var usuarioRepository: UsuarioRepository


    fun searchAllUsuarios(): List<Usuario> {
        val allUsers = usuarioRepository.findAll()

        logger.info("all users: ${allUsers.toString()}")
        return allUsers.map { usuarioEntity -> usuarioEntity.toUsuario() }
    }

    fun addNewUsuario(usuario: Usuario): Usuario {
        val usuarioEntity = usuario.toUsuarioEntity()
        usuarioRepository.save(usuarioEntity)
        usuario.password = "****"
        return usuario
    }

    fun login(email: String, password: String): Usuario? {
        val usuarioEntity = usuarioRepository
            .findUserByPasswordAndEmail(email, password)

        if (usuarioEntity != null) {
            val token = tokenGenerator()
            usuarioEntity.token = token
            usuarioRepository.save(usuarioEntity)
        }
        return usuarioEntity?.toUsuario()
    }

    fun findByToken(token: String): Usuario? {
        val usuarioLogged = usuarioRepository.findByToken(token)
        logger.info("Usuario exists: ${usuarioLogged.toString()}")
        return usuarioLogged?.toUsuario()
    }

    fun isUserValid(token: String): Boolean {
        val usuarioLogged = usuarioRepository.findByToken(token)
        logger.info("Usuario exists: ${usuarioLogged.toString()}")
        return usuarioLogged != null
    }

    fun tokenGenerator(): String {
        val token = UUID.randomUUID().toString()
        return token
    }
}