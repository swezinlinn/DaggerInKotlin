package com.example.dagger2.domain.scheduler

import io.reactivex.Scheduler

interface PostExecutionThread {
    val scheduler : Scheduler
}