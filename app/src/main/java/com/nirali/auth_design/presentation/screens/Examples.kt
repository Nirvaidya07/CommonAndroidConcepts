package com.nirali.auth_design.presentation.screens

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun backgroundTask1() {
    println("Main program Started")
    runBlocking {
        coroutineScope {
            val time = measureTimeMillis {
                val job1 = launch {
                    println("Task1 Started")
                    delay(1000)
                    println("Task1 Ended")
                }
                val job2 = launch {
                    println("Task2 Started")
                    delay(500)
                    println("Task2 Ended")
                }
                val task1 = job1.join()
                val task2 = job2.join()

            }
            println("Completed in $time ms")

            println("All tasks ended")
        }
    }
    println("Main program ended")
}