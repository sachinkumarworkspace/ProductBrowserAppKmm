# Summary of the business requirements.

  Mobile Product Catalog Prototype
 
     A. Product list screen
         1. View a list of products showing name, price, and thumbnail.
         2. Search products by keyword (integrate API-based search).
         3. Filter products by category.
 
     B. Product detail screen
         1. Tap a product to view detailed information including title, description, brand, price, and rating.
 
     C. Unit Test 
         GetProductsUseCaseTest

# Project architecture overview.
    1. Single Source of Truth pattern with Network + Caching using Proxy Pattern, 
       so the database is the single source of truth, and fetching from network when the cache is empty
    2. MVI Architecture: Unidirectional data flow
    3. Dependency Injection using Koin framework
    4. Search products by keyword (API-based search) with debounced user keypad typing (300ms) feature

    Clean Architecture
     Reactive Flow
     StateFlow used for UI state
     stateIn(WhileSubscribed(5000))
     Unidirectional data flow
     Debounced search


# Instructions on how to build and run the app on Android and iOS.

  Android:
   1. Open the project in Android Studio.
   2. Let Gradle sync complete.
   3. Start an Android Emulator or connect a physical device.
   4. Select the composeApp run configuration.
   5. Click Run button

  IOS:
   1. Open iosApp/iosApp.xcworkspace in Xcode
   2. Select an iOS Simulator
   3. Click Run button in Xcode

# Trade-offs or assumptions made during development.
 1. Cant test IOS App as using Windows System.
