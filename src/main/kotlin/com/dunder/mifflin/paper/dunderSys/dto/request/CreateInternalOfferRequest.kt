package com.dunder.mifflin.paper.dunderSys.dto.request

data class CreateInternalOfferRequest(
    val paperType: String,
    val discount: Int,
    val extraNotes: String,
    val to: String
)
