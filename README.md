
# Articles App
This repository contains an Android app built with a modular approach, following the principles of Clean Architecture and MVVM design pattern. The app utilizes various technologies and frameworks to provide a robust and efficient development environment.

## This project uses
- [Kotlin](https://developer.android.com/kotlin): is the primary programming language used
  for developing the app.
- [Retrofit](http://square.github.io/retrofit/): is used for making HTTP requests and
  handling API communication.
- [OkHttp](https://square.github.io/okhttp/): is used as the HTTP client for efficient
  network operations.
- [Gson](https://github.com/google/gson): is used for JSON serialization and deserialization.
- [Pluto](https://github.com/androidPluto/pluto): is used for managing and observing data
  changes in the app.
- [Dependency injection with Hilt](https://developer.android.com/training/dependency-injection/hilt-android):
  is used for dependency injection, providing a clean and maintainable architecture.
- [Coil](https://coil-kt.github.io/coil/): is used for efficient image loading and caching.
- [Coroutines](https://developer.android.com/kotlin/coroutines): are used for managing
  asynchronous programming and background tasks.
- [Flow](https://developer.android.com/kotlin/flow): is used for handling reactive streams and
  asynchronous data flow.
- [Clean Architecture](https://betterprogramming.pub/the-real-clean-architecture-in-android-part-1-s-o-l-i-d-6a661b103451):
  The app follows the principles of Clean Architecture, promoting separation
  of concerns and maintainability.
- [MVVM](https://www.digitalocean.com/community/tutorials/android-mvvm-design-pattern) : The app
  follows the MVVM (Model-View-ViewModel) architectural pattern for organizing and
  managing app components.
- [Mockito](https://github.com/mockito/mockito): is used for mocking dependencies during
  unit testing.
- [Mockk](https://mockk.io/): A mocking library for creating test doubles in unit tests and
  instrumentation tests.
- [Espresso](https://developer.android.com/training/testing/espresso): is used for UI
  testing and automation.
- [Ktlint](https://gorillalogic.com/blog/automate-ktlint-checks-with-git-hooks-avoiding-code-style-violations-in-code-reviews/):
  is used for enforcing Kotlin code style and formatting.
- [Detekt](https://www.kodeco.com/24470020-integrating-detekt-in-the-workflow): is used for
  static code analysis and detecting code smells.
  
## Installation
To install the app, follow these steps:

1. Clone the repository to your local machine using the following command:
```
git clone git@github.com:YouSsef3Ssam/articles.git
```
2. Open the project in Android Studio.
3. Build the project to fetch the required dependencies.

## Running the Application
To run the Android app on an emulator or physical device:

1. Connect your Android device to your development machine or launch an emulator.
2. In Android Studio, select the desired product flavor from the Build Variants tab (usually located on the left side).
3. Click on the Run button or use the shortcut Shift + F10.
4. Select the target device/emulator from the list.

The app will be installed and launched on the selected device/emulator.

## Running the Application - Command Line
To run the Android app from the command line using a single command:

1. Open a terminal and navigate to the root directory of the project.
2. Ensure that you have the Android SDK and necessary build tools installed.
3. Run the following command to build and install the app on a connected device or emulator:
```
./gradlew install<Flavor><BuildType> & adb shell am start -n com.your.app.package/com.your.app.package.MainActivity
```
Replace <Flavor> with the desired product flavor (e.g., Staging, Production) and <BuildType> with the desired build type (e.g., Debug, Release).

ex.
```
./gradlew installStagingDebug & adb shell am start -n com.youssef.articles/com.youssef.articles.MainActivity
```
  
## Running UI Tests
To run the ui tests for the Android app from the command line:

1. Open a terminal and navigate to the root directory of the project.
2. Run the following command to execute all the unit tests:
```
./gradlew test app:connectedStagingDebugAndroidTest
```
  
## Running Unit Tests
To run the Unit tests for the Android app from the command line:

1. Open a terminal and navigate to the root directory of the project.
2. Run the following command to execute all the unit tests:
```
./gradlew test
```
  
## Running Unit Tests with coverage report
<img width="1440" alt="how-to-run-coverage-report" src="https://github.com/YouSsef3Ssam/articles/assets/32605029/5103dac7-abcc-483d-a16a-a441e380cc97">

  
## Screenshots
![1](https://github.com/YouSsef3Ssam/articles/assets/32605029/fd7f552e-2e62-4969-b960-bcd60cb51ef2)
![2](https://github.com/YouSsef3Ssam/articles/assets/32605029/e5856f6a-9cdf-4502-a394-42eff8fdb868)
