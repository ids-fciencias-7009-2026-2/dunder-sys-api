package com.dunder.mifflin.paper.dunderSys.domain

data class Paper(
    var id: String,
    var code: String,
    var format: String,
    var height: Int,
    var width: Int,
    var usageType: String
)
