###
### Firabase Crashlytics
###
-keepattributes SourceFile,LineNumberTable        # Keep file names and line numbers.
-keep public class * extends java.lang.Exception  # Optional: Keep custom exceptions.



###
### Log
### https://www.jssec.org/dl/android_securecoding_20220829/4_using_technology_in_a_safe_way.html#system-out-err
###
-assumenosideeffects class android.util.Log {
    public static int d(...);
    public static int i(...);
    public static int v(...);
}



###
### OkHttp
### https://square.github.io/okhttp/features/r8_proguard/
###
# JSR 305 annotations are for embedding nullability information.
-dontwarn javax.annotation.**

# A resource is loaded with a relative path so the package of this class must be preserved.
-adaptresourcefilenames okhttp3/internal/publicsuffix/PublicSuffixDatabase.gz

# Animal Sniffer compileOnly dependency to ensure APIs are compatible with older versions of Java.
-dontwarn org.codehaus.mojo.animal_sniffer.*

# OkHttp platform used only on JVM and when Conscrypt and other security providers are available.
-dontwarn okhttp3.internal.platform.**
-dontwarn org.conscrypt.**
-dontwarn org.bouncycastle.**
-dontwarn org.openjsse.**
