package com.example.quizzworld

object PickColor {
    val colours= arrayOf(
        "#3EB9DF",
        "#3685BC",
        "#D36280",
        "#E44F55",
        "#FA8056",
        "#818BCA",
        "#7D659F",
        "#51BAB3",
        "#4FB66C",
        "#E3AD17",
        "#627991",
        "#EF8EAD",
        "#B5BFC6"
    )
    var currInd=0
    fun getcolor():String{
        currInd=(currInd+1)% colours.size
        return  colours[currInd]
    }
}