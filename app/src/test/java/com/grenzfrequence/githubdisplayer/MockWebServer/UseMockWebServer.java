package com.grenzfrequence.githubdisplayer.MockWebServer;

import android.support.annotation.NonNull;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by grenzfrequence on 14/02/17.
 */

@Target(METHOD)
@Retention(RUNTIME)
public @interface UseMockWebServer {
    @NonNull String setupMethodName() default "";
}
