package com.dunder.mifflin.paper.dunderSys.domain

import com.dunder.mifflin.paper.dunderSys.dto.request.CreateUsuarioRequest
import java.util.UUID

fun CreateUsuarioRequest.toUsuario(): Usuario {
    val id = "id-random-" + UUID.randomUUID().toString() // Generamos un id aleatorio
    return Usuario(id, this.nombre, this.email)
}