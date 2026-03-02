package com.dunder.mifflin.paper.dunderSys.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "usuarios")
 data class UsuarioEntity(
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
   var email: String,
   var password: String,
   var token: String?,
   var nombre: String,
   @Column(name = "edad")
    var age: Int?,
    var createdAt: LocalDateTime = LocalDateTime.now(),
    var updatedAt: LocalDateTime = LocalDateTime.now(),
    var deletedAt: LocalDateTime? = null
)