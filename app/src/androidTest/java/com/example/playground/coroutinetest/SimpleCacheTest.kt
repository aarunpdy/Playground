package com.example.playground.coroutinetest

import android.util.Log
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.time.Duration.Companion.minutes

/***
 * Article link @see [https://proandroiddev.com/write-testable-time-dependent-coroutine-code-in-kotlin-avoid-system-currenttimemillis-fb9b7eb1ddf9]
 */
class SimpleCacheTest {

    @Test
    fun cache_returns_expected_values_based_on_ttl() = runTest {
        val key = "my_key"
        val cache = SimpleCache<String>(createTestClock())

        // At the start, the cache should be empty.
        assertNull(cache.get<String>(key))

        // Add a value with a ttl of 5 minutes.
        cache.put(key, "My Value", 5.minutes)

        // Wait for 2 minutes and verify the cached value is still present.
        delay(2.minutes)
        assertNotNull(cache.get<String>(key))

        // Wait for another 4 minutes and verify the cached value is invalidated.
        delay(4.minutes)
        assertNull(cache.get<String>(key))
    }

    @Test
    fun delay_ignore_check() =
        runTest {
            val start = System.currentTimeMillis()
            delay(5.minutes)
            val end = System.currentTimeMillis()
            Log.i("DELAY TEST", (end - start).toString())
            assert((end - start) < 2) // 2 ms
            print(end - start)  // This will effectively print 0.
        }
}