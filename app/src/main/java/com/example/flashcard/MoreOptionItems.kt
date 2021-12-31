package com.example.flashcard

sealed class MoreOptionItems(var icon: Int, var title: String) { /* TODO: add a var for the 2 todo things below */
    object Delete : MoreOptionItems(R.drawable.ic_delete, "Home") /* TODO: add what delete does */
    object Sync : MoreOptionItems( R.drawable.ic_sync, "Sync")/* TODO: add what sync does */
}
