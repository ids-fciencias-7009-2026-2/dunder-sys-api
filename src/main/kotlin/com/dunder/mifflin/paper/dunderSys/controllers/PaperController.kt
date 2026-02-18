package com.dunder.mifflin.paper.dunderSys.controllers

import com.dunder.mifflin.paper.dunderSys.domain.Paper
import com.dunder.mifflin.paper.dunderSys.dto.request.CreatePaperRequest
import com.dunder.mifflin.paper.dunderSys.dto.request.UpdatePaperRequest
import jakarta.websocket.server.PathParam
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/v1/papers")
class PaperController {
    val logger: Logger = LoggerFactory.getLogger(PaperController::class.java)

    @GetMapping("/default")
    fun retrievePaper(): ResponseEntity<Paper> {
        val newPaper = Paper("PPR-3452", "PPR-STNDR", "Carta", 297, 210, "Escritura")
        return ResponseEntity.ok(newPaper)
    }


    @PostMapping
    fun createPaper(@RequestBody createPaperRequest: CreatePaperRequest): ResponseEntity<Any> {
        logger.info("Creating PAPER: $createPaperRequest")
        logger.warn("Storing in  my database....")
        return ResponseEntity.ok(Any())
    }

    @PutMapping("/{id}")
    fun updatePaper(@RequestBody updatePaperRequest: UpdatePaperRequest, @PathVariable id: String): ResponseEntity<Any> {
        logger.warn("Searching by id: $id")
        val fakePaper = Paper("PPR-3452", "PPR-STNDR", "Carta", 297, 210, "Escritura")
        logger.info("The paper found is $fakePaper")
        logger.info("Updating PAPER: $updatePaperRequest")

        fakePaper.width = updatePaperRequest.width
        fakePaper.height = updatePaperRequest.height

        logger.info("Paper updated: $fakePaper")
        return ResponseEntity.ok(Any())
    }


}