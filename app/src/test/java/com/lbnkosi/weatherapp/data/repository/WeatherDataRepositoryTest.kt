package com.lbnkosi.weatherapp.data.repository

import app.cash.turbine.test
import com.lbnkosi.weatherapp.data.datasource.WeatherDataSource
import com.lbnkosi.weatherapp.domain.models.openweathermaps.OpenWeatherMapResponse
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

class WeatherDataRepositoryTest {

    private val dataSource: WeatherDataSource = mockk()
    private val repository = WeatherDataRepository(dataSource)

    @Test
    fun `getWeatherData passes through success result`() = runTest {
        val lat = -26.2041
        val lon = 28.0473
        val payload = OpenWeatherMapResponse(timezone = "Africa/Johannesburg")
        coEvery { dataSource.getWeatherData(lat, lon) } returns flowOf(Result.success(payload))

        repository.getWeatherData(lat, lon).test {
            val item = awaitItem()
            assertTrue(item.isSuccess)
            assertEquals("Africa/Johannesburg", item.getOrNull()?.timezone)
            awaitComplete()
        }

        coVerify(exactly = 1) { dataSource.getWeatherData(lat, lon) }
    }

    @Test
    fun `getWeatherData passes through failure result`() = runTest {
        val lat = 51.5074
        val lon = -0.1278
        val error = RuntimeException("Network error")
        coEvery { dataSource.getWeatherData(lat, lon) } returns flowOf(Result.failure(error))

        repository.getWeatherData(lat, lon).test {
            val item = awaitItem()
            assertTrue(item.isFailure)
            assertEquals("Network error", item.exceptionOrNull()?.message)
            awaitComplete()
        }

        coVerify(exactly = 1) { dataSource.getWeatherData(lat, lon) }
    }
}
