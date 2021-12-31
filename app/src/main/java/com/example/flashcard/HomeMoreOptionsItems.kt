package com.example.flashcard

sealed class HomeMoreOptionItems(var icon: Int, var title: String) { /* TODO: add a var for the 2 todo things below */
    object Settings : MoreOptionItems(R.drawable.ic_settings, "Settings") /* TODO: add what delete does */
    object ContactUs : MoreOptionItems( R.drawable.ic_contact_us, "Contact Us")/* TODO: add what sync does */
}

