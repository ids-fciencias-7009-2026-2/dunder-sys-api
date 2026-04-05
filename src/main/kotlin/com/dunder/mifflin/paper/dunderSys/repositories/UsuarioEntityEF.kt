package com.dunder.mifflin.paper.dunderSys.repositories

import com.dunder.mifflin.paper.dunderSys.domain.Usuario
import com.dunder.mifflin.paper.dunderSys.entities.UsuarioEntity

fun Usuario.toUsuarioEntity(): UsuarioEntity {
    return UsuarioEntity(
        email = this.email,
        password = this.password ?: "",
        nombre = this.nombre,
        age = this.edad,
        token = ""
    )
}