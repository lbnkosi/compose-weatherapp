package com.lbnkosi.weatherapp.data.datasource

import com.lbnkosi.weatherapp.data.service.ApiUrl
import com.lbnkosi.weatherapp.data.service.OpenWeatherMapApiService
import com.lbnkosi.weatherapp.domain.models.openweathermaps.OpenWeatherMapResponse
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.await

class WeatherDataSourceTest {

    private lateinit var apiService: OpenWeatherMapApiService
    private lateinit var dataSource: WeatherDataSource

    @Before
    fun setUp() {
        apiService = mockk()
        dataSource = WeatherDataSource(apiService)
    }

    @Test
    fun `getWeatherData returns success when api call succeeds`() = runTest {
        // Given
        val lat = -26.2041
        val lon = 28.0473
        val expectedResponse = OpenWeatherMapResponse(timezone = "Africa/Johannesburg")

        val mockCall = mockk<Call<OpenWeatherMapResponse>>()
        coEvery { apiService.getWeatherData(lat, lon, ApiUrl.APP_ID) } returns mockCall
        coEvery { mockCall.await() } returns expectedResponse

        val result = dataSource.getWeatherData(lat, lon).first()

        assertTrue(result.isSuccess)
        assertEquals("Africa/Johannesburg", result.getOrNull()?.timezone)
        coVerify(exactly = 1) { apiService.getWeatherData(lat, lon, ApiUrl.APP_ID) }
    }

    @Test
    fun `getWeatherData returns failure when api call throws`() = runTest {
        val lat = 51.5074
        val lon = -0.1278

        coEvery { apiService.getWeatherData(lat, lon, ApiUrl.APP_ID) } throws RuntimeException("Network error")

        val result = dataSource.getWeatherData(lat, lon).first()

        assertTrue(result.isFailure)
        assertEquals("Network error", result.exceptionOrNull()?.message)
        coVerify(exactly = 1) { apiService.getWeatherData(lat, lon, ApiUrl.APP_ID) }
    }
}
