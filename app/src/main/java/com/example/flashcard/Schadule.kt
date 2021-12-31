package com.example.flashcard

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import androidx.work.*
import com.example.flashcard.localDatabase.FlashCardDatabase
import com.example.flashcard.localDatabase.WordCardRepository
import java.util.concurrent.TimeUnit

fun checkALlWordsToNotify(context: Context) {

    val wordCardRepository =
        WordCardRepository(FlashCardDatabase.getInstance(context = context).wordCardDao())
    val liveWords = wordCardRepository.getAll()
    Log.d("checkALlWordsToNotify", "checkALlWordsToNotify: ${liveWords}")


    val channelId = "MakeitEasy"
    var notificationId = 0
    val myBitmap = BitmapFactory.decodeResource(context.resources, R.drawable.splashimage)


    liveWords.forEach { wordCard ->

        createNotificationChannel(channelId, context)
        largeTextWithBigIconNotification(
            context = context,
            channelId,
            notificationId,
            "Flash Card Remember",
            wordCard.word,myBitmap
        )
        notificationId += 1

    }
}


class UploadWorker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {
    override fun doWork(): Result {
        // Do the work here--in this case, upload the images.
        checkALlWordsToNotify(applicationContext)


        // Indicate whether the work finished successfully with the Result
        return Result.success()
    }


}


fun build_task(appContext: Context) {
    PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS
    val uploadWorkRequest: WorkRequest =
        PeriodicWorkRequestBuilder<UploadWorker>(30, TimeUnit.MINUTES)
            .build()
    WorkManager
        .getInstance(appContext)
        .enqueue(uploadWorkRequest)
}