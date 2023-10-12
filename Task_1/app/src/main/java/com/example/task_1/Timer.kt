package com.example.task_1
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay

class Timer:ViewModel() {
    var timer = 0
    var state = false
    fun update(){
        if(state){
            timer++
        }
    }
    fun start() {
        state = true
    }
    fun pause() {
        state = false
    }
    fun reset(){
        state = false
        timer = 0
    }
}