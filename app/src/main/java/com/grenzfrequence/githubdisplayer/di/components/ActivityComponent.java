package com.grenzfrequence.githubdisplayer.di.components;

import com.grenzfrequence.githubdisplayer.base.IResourceValues;
import com.grenzfrequence.githubdisplayer.common.ErrMsg;
import com.grenzfrequence.githubdisplayer.di.modules.ActivityModule;
import com.grenzfrequence.githubdisplayer.di.modules.ErrorMessagesModul;
import com.grenzfrequence.githubdisplayer.di.modules.ViewModelModule;
import com.grenzfrequence.githubdisplayer.di.qualifiers.UiErrMsgQualifier;
import com.grenzfrequence.githubdisplayer.di.scopes.ActivityScope;
import com.grenzfrequence.githubdisplayer.repolist.ui.RepoListActivity;

import java.util.Map;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by grenzfrequence on 09/03/17.
 */

@Component(
        modules = {ActivityModule.class, ViewModelModule.class, ErrorMessagesModul.class},
        dependencies = AppComponent.class)
@ActivityScope
public interface ActivityComponent {

    void inject(RepoListActivity activity);

    Retrofit retrofit();

    IResourceValues resourceValues();

    @UiErrMsgQualifier
    Map<Integer, ErrMsg> errMessages();
}
