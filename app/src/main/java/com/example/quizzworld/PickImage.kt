package com.example.quizzworld

object PickImage {
    val image= arrayOf(
        R.drawable.icon_1,
        R.drawable.icon_2,
        R.drawable.icon_3,
        R.drawable.icon_4,
        R.drawable.icon_5,
        R.drawable.icon_6,
        R.drawable.icon_7,
        R.drawable.icon_8,
        R.drawable.icon_9,
        R.drawable.icon_10,
    )
    var currentImg=0
    fun getImg():Int{
        currentImg=(currentImg+1)% image.size
        return image[currentImg]
    }
}