package com.dunder.mifflin.paper.dunderSys.domain

data class Usuario(val id: String, var nombre: String, var email: String,var password: String?= null)