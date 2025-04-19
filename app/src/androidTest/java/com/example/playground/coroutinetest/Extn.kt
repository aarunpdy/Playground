package com.example.playground.coroutinetest

import kotlinx.coroutines.test.TestScope

fun TestScope.createTestClock() = TestClock(testScheduler)