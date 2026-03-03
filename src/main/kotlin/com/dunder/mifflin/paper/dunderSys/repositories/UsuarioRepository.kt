package com.dunder.mifflin.paper.dunderSys.repositories

import com.dunder.mifflin.paper.dunderSys.entities.UsuarioEntity
import org.springframework.data.repository.CrudRepository

interface UsuarioRepository : CrudRepository<UsuarioEntity, Int>{
}