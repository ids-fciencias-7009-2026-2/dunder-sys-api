package com.dunder.mifflin.paper.dunderSys.entities

import com.dunder.mifflin.paper.dunderSys.domain.Usuario

fun Usuario.toUsuarioEntity(): UsuarioEntity {
    return UsuarioEntity(
        email = this.email,
        password = this.password ?: "",
        nombre = this.nombre,
        age = this.edad,
        token = ""
    )
}