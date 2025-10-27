# 🌦️ Compose Weather App

**Compose Weather App** is a modern Android application that displays current weather information using the [OpenWeatherMap API](https://openweathermap.org/).
Built entirely with **Jetpack Compose**, it demonstrates clean architecture, reactive data handling, and testable design patterns following Google’s recommended Android development best practices.

---

## 📱 Screenshots

| Request Location                              | Weather Display                            | Error State                            | Error State                            | Error State                            | 
| --------------------------------------------- | ------------------------------------------ | -------------------------------------- | -------------------------------------- | -------------------------------------- |
| ![Request Location](https://github.com/lbnkosi/compose-weatherapp/blob/main/Screenshot_20251026_195408.png) | ![Permission Screen](https://github.com/lbnkosi/compose-weatherapp/blob/main/Screenshot_20251026_195420.png) | ![Loading Screen](https://github.com/lbnkosi/compose-weatherapp/blob/main/Screenshot_20251026_194745.png) | ![Weather Screen](https://github.com/lbnkosi/compose-weatherapp/blob/main/Screenshot_20251026_194655.png) | ![Error Screen](https://github.com/lbnkosi/compose-weatherapp/blob/main/Screenshot_20251026_194635.png) | 

---

## 🏗️ Architecture

The app follows **Clean Architecture** combined with **MVVM (Model–View–ViewModel)** to ensure the codebase is **maintainable, testable, and extensible**.

**Key layers:**

* **UI Layer (Presentation):** Jetpack Compose for declarative, reactive UIs.
* **Domain Layer:** Business logic encapsulated in use cases and entities.
* **Data Layer:** Repository pattern handling data sources (remote APIs).

---

## 🧩 Notable Libraries & Technologies

| Category                 | Library                                                                                    | Purpose                               |
| ------------------------ | ------------------------------------------------------------------------------------------ | ------------------------------------- |
| **Dependency Injection** | [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)           | Manage dependencies across layers     |
| **Networking**           | [Retrofit](https://square.github.io/retrofit/), [OkHttp](https://square.github.io/okhttp/) | REST API calls and HTTP client        |
| **Serialization**        | [Gson](https://github.com/google/gson)                                                     | JSON parsing                          |
| **Image Loading**        | [Coil](https://coil-kt.github.io/coil/)                                                    | Lightweight image loading for Compose |
| **Testing**              | [MockK](https://mockk.io/)                                                                 | Mocking framework for Kotlin          |
| **Coroutines & Flow**    | [Kotlinx Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)                 | Asynchronous programming              |

---

## 🚀 How to Run

1. Clone the repository:

   ```bash
   git clone https://github.com/lbnkosi/compose-weatherapp.git
   ```

2. Open the project in **Android Studio Koala or later**.

3. Add your OpenWeatherMap **APP ID** in:

   ```
   app/src/main/java/.../data/service/ApiUrl.kt
   ```

4. Build and run the app on an emulator or physical device.

> 💡 *Alternatively, you can download the latest APK [here](#) and install it directly.*

---

## 🧠 Developer Notes

This project was intentionally kept **simple and focused** due to time constraints.
However, several enhancements were considered during design to demonstrate future scalability.

### 🧩 Future Improvements

* **Offline Mode:**
  Introduce a local cache using **Room Database** to persist 7-day forecasts for offline viewing.
* **Dark Mode Support:**
  Implement theme switching for improved accessibility.
* **Hourly & Weekly Forecasts:**
  Extend API calls to display detailed forecasts.
* **Location Enhancements:**
  Allow users to manually search for cities in addition to GPS-based detection.

---

## 🧪 Testing

Unit tests cover:

* ViewModel logic
* Use cases
* Repository and data source interactions
* Network response handling

Run all tests with:

```bash
./gradlew test
```

---

## 👨‍💻 Author

**Lebogang Nkosi**
Android Engineer • Kotlin Enthusiast
📧 [[YourEmail@example.com](mailto:nkosilebogang95@gmail.com)] • 🌐 [LinkedIn]([https://www.linkedin.com/in/yourprofile](https://www.linkedin.com/in/lebogangnkosi/))
