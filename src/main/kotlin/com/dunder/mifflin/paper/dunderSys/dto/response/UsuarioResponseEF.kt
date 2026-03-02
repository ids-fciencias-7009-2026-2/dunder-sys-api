package com.dunder.mifflin.paper.dunderSys.dto.response

import com.dunder.mifflin.paper.dunderSys.domain.Usuario

fun Usuario.toUsuarioResponse(): UsuarioResponse {
    return UsuarioResponse(id = this.id?: "N/A", nombre = this.nombre, email = this.email, edad = this.edad)
}