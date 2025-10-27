package com.lbnkosi.weatherapp.domain.usecase

import app.cash.turbine.test
import com.lbnkosi.weatherapp.domain.models.openweathermaps.OpenWeatherMapResponse
import com.lbnkosi.weatherapp.domain.repository.WeatherDataRepo
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetWeatherDataUseCaseTest {

    private val repo: WeatherDataRepo = mockk()
    private val useCase = GetWeatherDataUseCase(repo)

    @Test
    fun `delegates to repo and emits success result`() = runTest {
        val lat = -26.2041
        val lon = 28.0473
        val response = OpenWeatherMapResponse(timezone = "Africa/Johannesburg")

        coEvery { repo.getWeatherData(lat, lon) } returns flowOf(Result.success(response))

        useCase.getWeatherData(lat, lon).test {
            val item = awaitItem()
            assertTrue(item.isSuccess)
            assertEquals("Africa/Johannesburg", item.getOrNull()?.timezone)
            awaitComplete()
        }

        coVerify(exactly = 1) { repo.getWeatherData(lat, lon) }
    }

    @Test
    fun `delegates to repo and emits failure result`() = runTest {
        val lat = 51.5074
        val lon = -0.1278
        val error = RuntimeException("Network error")

        coEvery { repo.getWeatherData(lat, lon) } returns flowOf(Result.failure(error))

        useCase.getWeatherData(lat, lon).test {
            val item = awaitItem()
            assertTrue(item.isFailure)
            assertEquals("Network error", item.exceptionOrNull()?.message)
            awaitComplete()
        }

        coVerify(exactly = 1) { repo.getWeatherData(lat, lon) }
    }
}
