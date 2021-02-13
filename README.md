# Pokedex

The App uses a set of Android Jetpack libraries plus Retrofit to dispay data from REST API. The App uses Kotlin.

### Prerequisites

The project has all required dependencies in the gradle files. 
Add the Project to Android Studio or Intelij and build.All the required dependencies will be downloaded and installed.

## Architecture

The project uses MVVM architecture pattern.

## Libraries 

* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel/) - Manage UI related data in a lifecycle conscious way and act as a channel between use cases and ui
* [ViewBinding](https://developer.android.com/topic/libraries/data-binding) - support library that allows binding of UI components in layouts to data sources,binds character details and search results to UI
* [Navigation Component](https://developer.android.com/guide/navigation/navigation-getting-started) - Android Jetpack's Navigation component helps in implementing
navigation between fragments
* [Dagger Hilt](https://developer.android.com/jetpack/androidx/releases/hilt) - For Dependency Injection.
* [Paging 3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview?hl=in) - Allow pagination of the Data.
* [Retrofit](https://square.github.io/retrofit/) - To access the Rest Api
* [Kotlin Flow](https://developer.android.com/kotlin/flow) - To access data sequentially
* [Datastore](https://developer.android.com/topic/libraries/architecture/datastore) - To store data in key value pairs, in this case to store boolean if the disclaimer dialog is shown or not

## Screenshots
|<img src="screenshots/home.jpg" width=200/>|<img src="screenshots/stats.jpg" width=200/>|
|:----:|:----:|

## Demo
<img src="demo/gif.gif" width=400/>
