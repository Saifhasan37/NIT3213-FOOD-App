# FOOD Android App

A modern Android application built with MVVM architecture, Hilt dependency injection, and Navigation Component.

## Features

- **Login Screen**: Authenticate with username/password (pre-filled with Saif/8089637)
- **Dashboard**: Display list of food dishes in a RecyclerView
- **Details Screen**: Show complete dish information including description

## Architecture

- **MVVM Pattern**: Clean separation of concerns with ViewModels
- **Repository Pattern**: Centralized data access layer
- **Hilt DI**: Dependency injection for better testability
- **Navigation Component**: Type-safe navigation between screens
- **DataStore**: Modern data persistence for user preferences
- **Retrofit + OkHttp**: Network communication with logging
- **Coroutines**: Asynchronous programming with Flow

## API Endpoints

- **Base URL**: `https://nit3213api.onrender.com/`
- **Login**: `POST /sydney/auth`
- **Dashboard**: `GET /dashboard/{keypass}`

## Credentials

- **Username**: Saif
- **Password**: 8089637
- **Expected Keypass**: food

## Build & Run

1. Open the project in Android Studio
2. Sync Gradle files
3. Run the app on an emulator or device

### Requirements

- Android Studio Arctic Fox or later
- minSdk: 24
- targetSdk: 34
- compileSdk: 34

## Project Structure

```
app/src/main/java/com/example/food/
├── data/
│   ├── local/          # DataStore implementation
│   ├── remote/          # API service and DTOs
│   └── repo/            # Repository implementation
├── domain/
│   ├── model/           # Domain models
│   └── mapper/          # Data mapping
├── ui/
│   ├── login/           # Login screen
│   ├── dashboard/       # Dashboard screen
│   └── details/         # Details screen
├── di/                   # Hilt modules
└── util/                 # Utility classes
```

## Testing

The project includes basic unit tests:

- `DishMapperTest`: Tests data mapping functionality
- `LoginViewModelTest`: Tests login flow happy path

Run tests with:
```bash
./gradlew test
```

## Dependencies

- **UI**: Material Design 3, Navigation Component, RecyclerView
- **Architecture**: ViewModel, LiveData, Coroutines
- **Network**: Retrofit, OkHttp, Gson
- **DI**: Hilt
- **Storage**: DataStore Preferences
- **Testing**: JUnit, MockK, Coroutines Test

## App Flow

1. **Login** → Enter credentials → Save keypass to DataStore → Navigate to Dashboard
2. **Dashboard** → Fetch dishes from API → Display in RecyclerView → Tap to navigate to Details
3. **Details** → Show all dish information → Back navigation to Dashboard

## Permissions

- `INTERNET`: Required for API communication

