#Common
-keepattributes Signature
-keepattributes Exceptions
-keepattributes *Annotation*
#Keep all model classes
-keep class com.r2apps.base.model.**{*;}
#Retrofit2
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
#OKHTTP3
-keep class com.squareup.okhttp3.** {*;}
-keep interface com.squareup.okhttp3.** { *; }
-keep class okio.***{*;}
-dontwarn com.squareup.okhttp.**
-dontwarn okio.***

#RxJava, RxAndroid, RxMath and RxBinding
# rxjava
-keep class rx.schedulers.Schedulers {
    public static <methods>;
}
-keep class rx.schedulers.ImmediateScheduler {
    public <methods>;
}
-keep class rx.schedulers.TestScheduler {
    public <methods>;
}
-keep class rx.schedulers.Schedulers {
    public static ** test();
}
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    long producerNode;
    long consumerNode;
}
#GSON
-keep class sun.misc.Unsafe { *; }