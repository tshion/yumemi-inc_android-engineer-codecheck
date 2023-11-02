# https://developer.android.com/guide/navigation/use-graph/pass-data#proguard_considerations
-keepnames class * extends android.os.Parcelable

# Log
# https://www.jssec.org/dl/android_securecoding_20220829/4_using_technology_in_a_safe_way.html#system-out-err
-assumenosideeffects class android.util.Log {
    public static int d(...);
    public static int i(...);
    public static int v(...);
}
