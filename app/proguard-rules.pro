# Keep Hilt internals (prevents R8 from optimizing them away)
-keep class dagger.hilt.internal.** { *; }
-keep class dagger.hilt.android.internal.managers.ViewComponentManager { *; }
-keep class dagger.hilt.android.internal.lifecycle.HiltViewModelFactory { *; }
-keep class *ViewModel_HiltModules* { *; }

# Keep OkHttp internal classes used by SSE or Retrofit
-keep class okhttp3.internal.** { *; }