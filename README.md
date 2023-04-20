# MyNewsApp

This application is made to learn Kotlin with MVVM Architecture Pattern along with latest coding
standard.

## Technology Used

- Kotlin
- MVVM Architecture Pattern
- Offline First App
- Jetpack Component
- Coroutine
- Flow
- Dagger
- StateFlow
- Retrofit
- Pagination
- Room
- ViewBinding
- UnitTest
- Instrumented Test

## Key Features

- News based on TopHeadlines, Sources, Countries and Languages
- Offline News
- Pagination with Paging library
- Instant Search by Flow operator
    - Debounce
    - Filter
    - distinctUntilChanged
    - flatMapLatest
- UnitTest
    - Mockito
    - Turbine
- Instrumented Test
    - Espresso

## Dependency Used

-Dependency Injection

```
implementation "com.google.dagger:dagger:2.42"
kapt 'com.google.dagger:dagger-compiler:2.42'
```

- Lifecycle Aware Component

```
implementation 'android.arch.lifecycle:extensions:1.1.1'
implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1'
implementation 'androidx.lifecycle:lifecycle-runtime-ktx:2.5.1'
```

- Local Database

```
implementation "androidx.room:room-runtime:2.2.3"
kapt "androidx.room:room-compiler:2.2.3"
implementation "androidx.room:room-ktx:2.4.3"
```

- Networking

```
implementation 'com.squareup.retrofit2:retrofit:2.9.0'
implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
```

- Image Loader

```
implementation 'com.github.bumptech.glide:glide:4.13.0'
```

- Paging

```
implementation "androidx.paging:paging-runtime:3.1.1"
```

- WebView Browser

```
implementation 'androidx.browser:browser:1.4.0'
```

- Unit Test

```
testImplementation 'junit:junit:4.12'
testImplementation "org.mockito:mockito-core:3.4.6"
testImplementation 'androidx.arch.core:core-testing:2.1.0'
testImplementation 'org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.1'
testImplementation 'app.cash.turbine:turbine:0.11.0'
testImplementation 'org.mockito:mockito-inline:2.13.0'
```

- Instrumented Test

```
androidTestImplementation 'androidx.test.ext:junit:1.1.3'
androidTestImplementation 'androidx.test.espresso:espresso-core:3.5.0'
androidTestImplementation "androidx.test.espresso:espresso-contrib:3.4.0"
androidTestImplementation "androidx.test.espresso:espresso-intents:3.4.0"
androidTestImplementation "org.mockito:mockito-core:4.9.0"
androidTestImplementation 'org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.1'
androidTestImplementation "org.mockito:mockito-core:4.9.0"
kaptAndroidTest "com.google.dagger:dagger-compiler:2.42"
```

## Product Folder Structure

```
├── MyApplication.kt
├── data
│   ├── local
│   ├── model
│   ├── paging
│   └── repository
├── di
│   ├── api
│   ├── component
│   ├── module
│   ├── qualifiers.kt
│   └── scopes.kt
├── ui
│   ├── MainActivity.kt
│   ├── base
│   ├── countries
│   ├── languages
│   ├── search
│   ├── sources
│   ├── topHeadlines
└── utils
    ├── aliases.kt
    ├── AppConstant.kt
    ├── DispatcherProvider.kt
    ├── extensions.kt
    ├── Resource.kt
    ├── Status.kt
    └── Utils.kt

```



