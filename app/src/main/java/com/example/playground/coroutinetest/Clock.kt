package com.example.playground.coroutinetest

interface Clock {

    /**
     * The number of milliseconds from the Unix epoch `1970-01-01T00:00:00Z`.
     */
    fun now(): Long
}