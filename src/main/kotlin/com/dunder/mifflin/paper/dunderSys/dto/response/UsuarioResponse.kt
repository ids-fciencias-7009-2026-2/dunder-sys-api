package com.dunder.mifflin.paper.dunderSys.dto.response

data class UsuarioResponse(
    val id: String,
    var nombre: String,
    var email: String,
    var edad: Int?
)