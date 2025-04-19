package com.example.playground.coroutinetest

import kotlin.time.Duration

interface Cache<Key> {
    suspend fun put(key: Key, value:Any, ttl: Duration)
    suspend fun <Value:Any> get(key: Key):Value?
}