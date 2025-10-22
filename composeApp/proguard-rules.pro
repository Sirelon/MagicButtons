# Keep Koin DSL bindings and reflection metadata used at runtime
-keep class org.koin.** { *; }
-dontwarn org.koin.**

# Ensure Kotlin reflection metadata needed by Koin survives shrinking
-keepclassmembers class kotlin.Metadata { *; }

# Keep kotlinx.serialization generated serializers and annotations
-keep class kotlinx.serialization.** { *; }
-keepclassmembers class * {
    @kotlinx.serialization.Serializable *;
}

# Navigation destinations are referenced by name via serialization
-keep class com.sirelon.magicbuttons.** implements kotlinx.serialization.Serializable
