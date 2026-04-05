package com.dunder.mifflin.paper.dunderSys.repositories

import com.dunder.mifflin.paper.dunderSys.entities.UsuarioEntity
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface UsuarioRepository: CrudRepository<UsuarioEntity, Int> {
    @Query("select u from UsuarioEntity u where u.token = :token")
    fun findByToken(token: String): UsuarioEntity?

    @Query("select u from UsuarioEntity u where u.email = :email and u.password = :password")
    fun findUserByPasswordAndEmail(email: String, password: String): UsuarioEntity?

    @Modifying
    @Transactional
    @Query("update UsuarioEntity u set u.token = :token where u.id = :id")
    fun updateTokenById(id: Int, token: String?)
}