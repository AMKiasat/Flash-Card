package com.example.flashcard

sealed class MoreOptionItems(var icon: Int, var title: String) { /* TODO: add a var for the 2 todo things below */
    object Delete : MoreOptionItems(R.drawable.ic_delete, "Delete category") /* TODO: add what delete does */
    object Download : MoreOptionItems( R.drawable.ic_download, "Download")
    object Upload : MoreOptionItems( R.drawable.ic_upload, "Upload")
    object Sync : MoreOptionItems( R.drawable.ic_sync, "Sync")
}
