-keep,includedescriptorclasses class net.sqlcipher.** { *; }
-keep,includedescriptorclasses interface net.sqlcipher.** { *; }
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

##---------------Begin: proguard configuration for Gson ----------
-keepattributes Signature
-keepattributes *Annotation*
-dontwarn sun.misc.**
-keep class com.google.gson.stream.** { *; }
-keep class com.google.gson.examples.android.model.** { <fields>; }
-keep class * extends com.google.gson.TypeAdapter
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer
-keepclassmembers,allowobfuscation class * {
    @com.google.gson.annotations.SerializedName <fields>;
}

# Keep the NewsResponse class and its members
-keep class com.adisastrawan.core.data.source.remote.response.NewsResponse {
    public <init>();
    *;
}

##---------------Begin: proguard configuration for Retrofit ----------
-keepattributes Signature, InnerClasses, EnclosingMethod
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
-dontwarn javax.annotation.**
-dontwarn kotlin.Unit
-dontwarn retrofit2.KotlinExtensions
-dontwarn retrofit2.KotlinExtensions$*
-if interface * { @retrofit2.http.* <methods>; }
-keep,allowobfuscation interface <1>
-dontwarn kotlinx.**
# Keep generic signature of Call, Response (R8 full mode strips signatures from non-kept items).
 -keep,allowobfuscation,allowshrinking interface retrofit2.Call
 -keep,allowobfuscation,allowshrinking class retrofit2.Response

 # With R8 full mode generic signatures are stripped for classes that are not
 # kept. Suspend functions are wrapped in continuations where the type argument
 # is used.
 -keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation
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

# Keep the CoreModuleKt file and its contents
-keep class com.adisastrawan.core.di.CoreModuleKt {
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

# Keep the OnAdapterItemClickListener class and its members
-keep class com.adisastrawan.core.utils.OnAdapterItemClickListener {
    *;
}
