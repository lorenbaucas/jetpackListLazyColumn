package com.example.jetpacklistlazycolumn.ui.theme

sealed class ScreenChange(
    val screen:String
) {
    object home : ScreenChange("home")
    object chars : ScreenChange("chars/{name}") {
        fun changeto(name: String) = "chars/$name"
    }
}