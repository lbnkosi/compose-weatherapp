package com.lbnkosi.weatherapp.data.network

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.concurrent.atomic.AtomicInteger

private class TestRemoteRepository : RemoteRepository() {
    suspend fun <T> run(call: suspend () -> T) = apiCall(call)
}

class RemoteRepositoryTest {

    private val repo = TestRemoteRepository()

    @Test
    fun `apiCall emits success when block completes`() = runTest {
        val value = "OK"
        val counter = AtomicInteger(0)
        val flow = repo.run {
            counter.incrementAndGet()
            // Simulate work
            delay(10)
            value
        }

        val result = flow.first()

        assertEquals(1, counter.get())
        assertTrue(result.isSuccess)
        assertEquals(value, result.getOrNull())
    }

    @Test
    fun `apiCall emits failure when block throws`() = runTest {
        val error = IllegalStateException("boom")
        val counter = AtomicInteger(0)
        val flow = repo.run<String> {
            counter.incrementAndGet()
            throw error
        }

        val result = flow.first()

        assertEquals(1, counter.get())
        assertTrue(result.isFailure)
        assertEquals("boom", result.exceptionOrNull()?.message)
    }
}
