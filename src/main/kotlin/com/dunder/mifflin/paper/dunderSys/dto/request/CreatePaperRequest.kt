package com.dunder.mifflin.paper.dunderSys.dto.request

data class CreatePaperRequest(
    var code: String = "PPR",
    var format: String,
    var height: Int = 210, // valor default
    var width: Int = 296, // valor default
    var usageType: String = "Escritura"
)