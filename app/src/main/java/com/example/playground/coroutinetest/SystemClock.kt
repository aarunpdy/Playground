package com.example.playground.coroutinetest

object SystemClock : Clock {

    override fun now() = System.currentTimeMillis()

}