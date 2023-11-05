# https://developer.android.com/guide/navigation/use-graph/pass-data#proguard_considerations
-keepnames class * extends android.os.Parcelable

# Firabase Crashlytics
-keepattributes SourceFile,LineNumberTable        # Keep file names and line numbers.
-keep public class * extends java.lang.Exception  # Optional: Keep custom exceptions.

# Log
# https://www.jssec.org/dl/android_securecoding_20220829/4_using_technology_in_a_safe_way.html#system-out-err
-assumenosideeffects class android.util.Log {
    public static int d(...);
    public static int i(...);
    public static int v(...);
}
