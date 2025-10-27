package com.lbnkosi.weatherapp.domain.repository

import com.lbnkosi.weatherapp.domain.models.openweathermaps.OpenWeatherMapResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class WeatherDataRepoTest {

    // Fake implementation for testing
    private class FakeWeatherDataRepo(
        private val shouldSucceed: Boolean
    ) : WeatherDataRepo {
        override suspend fun getWeatherData(lat: Double, lon: Double): Flow<Result<OpenWeatherMapResponse?>> {
            return flow {
                if (shouldSucceed) {
                    emit(Result.success(OpenWeatherMapResponse(timezone = "Test/Success")))
                } else {
                    emit(Result.failure(Exception("Failed to fetch weather data")))
                }
            }
        }
    }

    @Test
    fun `getWeatherData emits success when request succeeds`() = runTest {
        val repo = FakeWeatherDataRepo(shouldSucceed = true)

        val result = repo.getWeatherData(-26.2041, 28.0473).first()

        assertTrue(result.isSuccess)
        assertEquals("Test/Success", result.getOrNull()?.timezone)
    }

    @Test
    fun `getWeatherData emits failure when request fails`() = runTest {
        val repo = FakeWeatherDataRepo(shouldSucceed = false)

        val result = repo.getWeatherData(-33.9249, 18.4241).first()

        assertTrue(result.isFailure)
        assertEquals("Failed to fetch weather data", result.exceptionOrNull()?.message)
    }
}
