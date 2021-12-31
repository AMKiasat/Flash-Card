package com.example.flashcard.helpers

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

val TIME_FORMAT = "hh:mm:ss"

fun formatTime(date :LocalDateTime): String{

    val formatter = DateTimeFormatter.ofPattern(TIME_FORMAT)
    val formatted = date.format(formatter)

    return formatted
}