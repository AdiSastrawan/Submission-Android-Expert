-keep class net.sqlcipher.** { *; }
-keep class org.spatialite.** { *; }
-keep class net.sqlcipher.database.** { *; }
# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-keep class com.adisastrawan.androidexpertproject.di.AppModuleKt {
    *;
}

##---------------Begin: proguard configuration for Glide ----------
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep class * extends com.bumptech.glide.module.AppGlideModule {
<init>(...);
}
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
**[] $VALUES;
public *;
}
-keep class com.bumptech.glide.load.data.ParcelFileDescriptorRewinder$InternalRewinder {
*** rewind();
   }
# Keep the Resource sealed class and its inner classes
-keep public class com.adisastrawan.core.data.Resource
-keep public class com.adisastrawan.core.data.Resource$Success
-keep public class com.adisastrawan.core.data.Resource$Error
-keep public class com.adisastrawan.core.data.Resource$Loading

# Keep members of the Resource class and its nested classes
-keepclassmembers class com.adisastrawan.core.data.Resource {
    *;
}
-keepclassmembers class com.adisastrawan.core.data.Resource$Success {
    *;
}
-keepclassmembers class com.adisastrawan.core.data.Resource$Error {
    *;
}
-keepclassmembers class com.adisastrawan.core.data.Resource$Loading {
    *;
}

