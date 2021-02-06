package com.github.azdrachak.aafundamentals.work

import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import java.util.concurrent.TimeUnit

const val UPDATE_PERIODICITY = 8L
const val WORK_NAME = "update_movies_worker"

class WorkRepository {

    private val constraints =
        Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

    val updateWorker =
        PeriodicWorkRequestBuilder<UpdateMoviesWorker>(UPDATE_PERIODICITY, TimeUnit.HOURS)
            .setInitialDelay(UPDATE_PERIODICITY, TimeUnit.HOURS)
            .setConstraints(constraints)
            .build()
}
