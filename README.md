# Check_Percentage μ΄λ¦ κΆν©

## π― Use stack & skill
- Kotlin 100%
- Firebase RTDB
- Firestore
- MVVM
- Clean Architecture
- ACC
- Dagger Hilt
- DataBinding
- BindingAdapter

<br>

## π App tructure
#### App module(Di) -> Presentation module(View, ViewModel) -> Domain module(Repository, Usecase) -> Data module(Repository, Mapper, Remote)

### <br>

```
|ββ app
|   βββ di
|       βββ App.kt
|       βββ DataSourceModule.kt
|       βββ FirebaseModule.kt
|       βββ NetworkModule.kt
|       βββ RepositoryModule.kt
|
βββ presentation
β   βββ adapter
β   β   βββ ScoreBindingAdapter.kt
β   β   βββ ScoreRecyclerViewAdapter.kt
β   βββ base
β   β   βββ BaseActivity.kt
β   β   βββ BaseFragment.kt
β   βββ view
β   β   βββ MainActivity.kt
β   β   βββ MainFragment.kt
β   β   βββ ManNameFragment.kt
β   β   βββ ResultFragment.kt
β   β   βββ SplashActivity.kt
β   β   βββ WomanNameFragment.kt
β   βββ viewmodel
β   β   βββ MainViewModel.kt
β   β   βββ SplashViewModel.kt
β   βββ widget
β       βββ extension
β       β   βββ ActivityExtension.kt
β       β   βββ FragmentExtension.kt
β       βββ utils
β           βββ SingleLiveEvent.kt
β
βββ domain
β   βββ model
β   β   βββ DomainLoveResponse.kt
β   β   βββ DomainScore.kt
β   βββ repository
β   β   βββ MainRepository.kt
β   β   βββ SplashRepository.kt
β   βββ usecase
β   β   βββ CheckAppVersionUseCase.kt
β   β   βββ CheckLoveCalculatorUseCase.kt
β   β   βββ GetScoreUseCase.kt
β   β   βββ GetStatisticsUseCase.kt
β   β   βββ SetScoreUseCase.kt
β   β   βββ SetStatisticsUseCase.kt
β   βββ utils
β       βββ ErrorType.kt
β       βββ RemoteErrorEmitter.kt
β       βββ ScreenState.kt
β
βββ data
β   βββ mapper
β   β   βββ MainMapper.kt
β   βββ remote
β   β   βββ api
β   β   β   βββ LoveCalculatorApi.kt
β   β   βββ model
β   β       βββ DataLoveResponse.kt
β   β       βββ DataScore.kt
β   βββ repository
β       βββ remote
β       β   βββ datasource
β       β   β   βββ MainDataSource.kt
β       β   β   βββ SplashDataSource.kt
β       β   βββ datasourceimpl
β       β       βββ MainDataSourceImpl.kt
β       β       βββ SplashDataSourceImpl.kt
β       βββ MainRepositoryImpl.kt
β       βββ SplashRepositoryImpl.kt
β   
βββ buildSrc
    βββ Dependency.kt
```
<br>

## π Contributors
#### ParkSangSun1

<br>

## ποΈ Demonstration
![κΆν©](https://user-images.githubusercontent.com/67040465/152307727-13eb4426-a60f-4a58-8e45-e4d04cf2687e.gif)


## π¨ Other than that
#### μ€λ₯λ λ²κ·Έκ° λ°κ²¬λλ©΄ μ΄μ λ£μ΄μ£Όμλ©΄ κ°μ¬νκ² μ΅λλ€
#### If you find an error or bug, I would appreciate it if you could put in an issue 
