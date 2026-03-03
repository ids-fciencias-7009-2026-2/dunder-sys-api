package com.dunder.mifflin.paper.dunderSys.services

import com.dunder.mifflin.paper.dunderSys.domain.Usuario
import com.dunder.mifflin.paper.dunderSys.domain.toUsuario
import com.dunder.mifflin.paper.dunderSys.entities.toUsuarioEntity
import com.dunder.mifflin.paper.dunderSys.repositories.UsuarioRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UsuarioService {
    val logger = LoggerFactory.getLogger(UsuarioService::class.java)

    @Autowired
    lateinit var usuarioRepository: UsuarioRepository

    fun searchAllUsuarios(): List<Usuario> {
        val allUsers = usuarioRepository.findAll()
        logger.info("all users: ${allUsers}")
        val usuarios = allUsers.map { usuarioEntity -> usuarioEntity.toUsuario() }
        return usuarios
    }


    fun addNewUsuario(usuario: Usuario): Usuario {
        val usuarioEntity = usuario.toUsuarioEntity()
        usuarioRepository.save(usuarioEntity)
        usuario.password = "****"
        return usuario
    }
}