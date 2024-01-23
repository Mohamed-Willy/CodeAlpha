package com.example.task_2

interface EventScheduler {
    fun scedule(item: EventItem)
    fun cancel(item: EventItem)
}