package com.example.quizzworld

data class Quiz(
    var id:String="",
    var title:String="",
    var questions:MutableMap<String,Questions> = mutableMapOf()
)