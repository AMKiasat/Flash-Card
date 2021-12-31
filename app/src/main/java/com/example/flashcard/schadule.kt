package com.example.flashcard

import android.app.Application
import android.content.Context
import androidx.work.*
import com.example.flashcard.localDatabase.WordViewModel
import java.util.concurrent.TimeUnit



class UploadWorker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {
    override fun doWork(): Result {
        val channelId = "MakeitEasy"
        val notificationId = 0
        // Do the work here--in this case, upload the images.
        checkALlWordsToNotify(applicationContext)
        simpleNotificationWithTapAction(
            context = applicationContext,
            channelId,
            notificationId,
            "Flash Card Remember",
            "your ",
        )

        // Indicate whether the work finished successfully with the Result
        return Result.success()
    }


    fun checkALlWordsToNotify(context: Context) {
        val wordViewModel = WordViewModel(context as Application)
        val channelId = "MakeitEasy"
        var notificationId = 0
        val liveWords = wordViewModel.getWordsToNotify()



        liveWords.value?.forEach { wordCard ->

            createNotificationChannel(channelId, context)
            simpleNotification(
                context = context,
                channelId,
                notificationId,
                "Remember",
                wordCard.word,
            )
            notificationId += 1

        }
    }

}


fun build_task(appContext: Context) {
    PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS
    val uploadWorkRequest: WorkRequest =
        PeriodicWorkRequestBuilder<UploadWorker>(5, TimeUnit.SECONDS)
            .build()
    WorkManager
        .getInstance(appContext)
        .enqueue(uploadWorkRequest)
}