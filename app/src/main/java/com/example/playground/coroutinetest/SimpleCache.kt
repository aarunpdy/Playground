package com.example.playground.coroutinetest

import kotlinx.coroutines.sync.Mutex
import kotlin.time.Duration


class SimpleCache<Key>(private val clock: Clock = SystemClock) : Cache<Key> {
    private val mutex = Mutex()
    private val entries = mutableMapOf<Key, CacheEntry<*>>()
    override suspend fun put(key: Key, value: Any, ttl: Duration) {
        val expiryTime = clock.now() + ttl.inWholeMilliseconds
        entries[key] = CacheEntry(value, expiryTime)
    }

    override suspend fun <Value : Any> get(key: Key): Value? {
        val entry = entries[key]
        if (entry != null && clock.now() < entry.expiryTime) {
            @Suppress("UNCHECKED_CAST")
            return entry.value as Value
        }

        invalidateKey(key)
        return null
    }

    private fun invalidateKey(key: Key) {
        entries.remove(key)
    }

    private data class CacheEntry<Value>(val value: Value, val expiryTime: Long)
}