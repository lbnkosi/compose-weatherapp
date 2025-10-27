package com.lbnkosi.weatherapp.ui.weatherfeature

import app.cash.turbine.test
import com.lbnkosi.weatherapp.domain.models.openweathermaps.OpenWeatherMapResponse
import com.lbnkosi.weatherapp.domain.usecase.GetWeatherDataUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class WeatherScreenViewModelTest {

    private val testDispatcher = UnconfinedTestDispatcher()
    private lateinit var useCase: GetWeatherDataUseCase
    private lateinit var viewModel: WeatherScreenViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        useCase = mockk(relaxed = true)
        viewModel = WeatherScreenViewModel(useCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `onEvent success - emits Loading then Success`() = runBlocking {
        val lat = -33.9249
        val lon = 18.4241
        val response = OpenWeatherMapResponse(timezone = "Africa/Johannesburg")
        coEvery { useCase.getWeatherData(lat, lon) } returns flowOf(Result.success(response))

        viewModel.state.test {
            skipItems(1)

            viewModel.onEvent(WeatherScreenEvent.GetWeatherScreenData(lat, lon))

            assertTrue(awaitItem() is WeatherScreenState.Loading)

            val success = awaitItem()
            assertTrue(success is WeatherScreenState.Success)
            success as WeatherScreenState.Success
            assertEquals("Africa/Johannesburg", success.weatherData.timezone)

            cancelAndIgnoreRemainingEvents()
        }

        coVerify(exactly = 1) { useCase.getWeatherData(lat, lon) }
    }

    @Test
    fun `onEvent failure - emits Loading then Error`() = runBlocking {
        val lat = 51.5074
        val lon = -0.1278
        coEvery { useCase.getWeatherData(lat, lon) } returns flowOf(Result.failure(Throwable("Network error")))

        viewModel.state.test {
            skipItems(1)

            viewModel.onEvent(WeatherScreenEvent.GetWeatherScreenData(lat, lon))

            assertTrue(awaitItem() is WeatherScreenState.Loading)

            val error = awaitItem()
            assertTrue(error is WeatherScreenState.Error)
            error as WeatherScreenState.Error
            assertEquals("There was an error getting the weather", error.errorMessage)

            cancelAndIgnoreRemainingEvents()
        }

        coVerify(exactly = 1) { useCase.getWeatherData(lat, lon) }
    }
}
