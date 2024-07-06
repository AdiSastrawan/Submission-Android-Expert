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

# Keep the Favorite class and its members
-keep class com.adisastrawan.core.domain.model.Favorite {
    *;
}

# Keep the News class and its members
-keep class com.adisastrawan.core.domain.model.News {
    *;
}

# Keep the INewsRepository interface and its members
-keep interface com.adisastrawan.core.domain.repository.INewsRepository {
    *;
}

# Keep the DetailInteractor class and its members
-keep class com.adisastrawan.core.domain.usecase.DetailInteractor {
    *;
}

# Keep the DetailUseCase class and its members
-keep class com.adisastrawan.core.domain.usecase.DetailUseCase {
    *;
}

# Keep the FavoriteInteractor class and its members
-keep class com.adisastrawan.core.domain.usecase.FavoriteInteractor {
    *;
}

# Keep the FavoriteUseCase class and its members
-keep class com.adisastrawan.core.domain.usecase.FavoriteUseCase {
    *;
}

# Keep the NewsInteractor class and its members
-keep class com.adisastrawan.core.domain.usecase.NewsInteractor {
    *;
}

# Keep the NewsUseCase class and its members
-keep class com.adisastrawan.core.domain.usecase.NewsUseCase {
    *;
}

# Keep the NewsListAdapter class and its members
-keep class com.adisastrawan.core.ui.NewsListAdapter {
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

-keep class com.adisastrawan.core.data.source.remote.response.NewsResponse {
    public <init>();
    *;
}

# Keep the ArticlesItem
-keep class com.adisastrawan.core.data.source.remote.response.ArticlesItem {
    public <init>();
    *;
}
#Keep the Source
-keep class com.adisastrawan.core.data.source.remote.response.Source{
    *;
}

 # With R8 full mode generic signatures are stripped for classes that are not
 # kept. Suspend functions are wrapped in continuations where the type argument
 # is used.
 -keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation