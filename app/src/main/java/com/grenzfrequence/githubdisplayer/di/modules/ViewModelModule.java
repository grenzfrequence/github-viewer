package com.grenzfrequence.githubdisplayer.di.modules;

import com.grenzfrequence.githubdisplayer.base.IResourceValues;
import com.grenzfrequence.githubdisplayer.base.ResourceValues;

import dagger.Binds;
import dagger.Module;

/**
 * Created by grenzfrequence on 11/03/17.
 */

@Module
public abstract class ViewModelModule {

    @Binds
    abstract IResourceValues resourceValues(ResourceValues textMessages);

}
