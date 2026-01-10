# ------------------------
# General Android rules
# ------------------------
-dontoptimize
-dontwarn android.support.**
-keep class android.support.** { *; }
-keep class androidx.** { *; }

# ------------------------
# Keep Kotlin metadata
# ------------------------
-keep class kotlin.Metadata { *; }

# ------------------------
# Hilt / Dagger
# ------------------------
# Keep Hilt generated classes
-keep class dagger.hilt.** { *; }
-keep class hilt_aggregated_deps.** { *; }
-keep class dagger.hilt.internal.** { *; }

# Keep generated components
-keep class * implements dagger.hilt.internal.GeneratedComponent { *; }

# Keep Hilt modules / entry points
-keep class * extends dagger.hilt.android.HiltAndroidApp { *; }
-keep class * extends dagger.hilt.android.AndroidEntryPoint { *; }

# ------------------------
# Jetpack Compose
# ------------------------
# Keep Compose metadata
-keep class androidx.compose.** { *; }
-keep class kotlin.jvm.internal.** { *; }
-keep class androidx.compose.ui.tooling.** { *; }

# ------------------------
# Kotlin Serialization / Reflection
# ------------------------
-keepclassmembers class kotlin.jvm.internal.** { *; }
-keepclassmembers class kotlin.reflect.jvm.internal.** { *; }

# ------------------------
# AndroidX lifecycle / ViewModel / LiveData
# ------------------------
-keep class androidx.lifecycle.** { *; }
-keep interface androidx.lifecycle.** { *; }

# ------------------------
# Material Icons & Fonts
# ------------------------
-keep class androidx.compose.material.icons.** { *; }
-keep class androidx.compose.ui.text.googlefonts.** { *; }

# ------------------------
# Test libraries (optional, keep for test builds)
# ------------------------
-keep class org.junit.** { *; }
-keep class androidx.test.** { *; }
-keep class com.google.common.truth.** { *; }
-keep class androidx.test.espresso.** { *; }

# ------------------------
# General rules for reflection / serialization
# ------------------------
-keepclassmembers class ** {
    @androidx.annotation.Keep *;
}
-keepclasseswithmembers class * {
    @androidx.annotation.Keep <methods>;
}

# ------------------------
# Don't strip annotations
# ------------------------
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable