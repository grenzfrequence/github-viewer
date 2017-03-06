package com.grenzfrequence.githubdisplayer.di;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import dagger.MapKey;

/**
 * Created by grenzfrequence on 25/01/17.
 */

@MapKey @Target(ElementType.METHOD) @Retention(RetentionPolicy.RUNTIME)
public @interface SubcomponentKey {
    Class<?> value();
}
