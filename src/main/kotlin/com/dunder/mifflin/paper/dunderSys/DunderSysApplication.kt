package com.dunder.mifflin.paper.dunderSys

import io.github.cdimascio.dotenv.dotenv
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DunderSysApplication

// Comentariooooo
fun main(args: Array<String>) {
	dotenv().entries().forEach {
		System.setProperty(it.key, it.value)
	}
	runApplication<DunderSysApplication>(*args)
}
