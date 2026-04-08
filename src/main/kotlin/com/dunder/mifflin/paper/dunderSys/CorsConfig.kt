package com.dunder.mifflin.paper.dunderSys

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class CorsConfig : WebMvcConfigurer {

    private val logger = LoggerFactory.getLogger(CorsConfig::class.java)

    override fun addCorsMappings(registry: CorsRegistry) {
        logger.info("CORS configurado para http://localhost:5173")

        registry.addMapping("/**")
            .allowedOrigins("http://localhost:5173")
            .allowedMethods("GET", "POST", "PUT", "DELETE")
            .allowedHeaders("*")
    }
}