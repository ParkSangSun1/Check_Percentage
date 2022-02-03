package com.pss.domain.model

data class DomainScore(
    val man : String,
    val woman : String,
    val percentage : Int,
    val date : String
){
    constructor() : this("오류","오류",0,"오류")
}