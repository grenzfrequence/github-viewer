package com.grenzfrequence.githubdisplayer.di.modules;

import android.util.ArrayMap;

import com.grenzfrequence.githubdisplayer.R;
import com.grenzfrequence.githubdisplayer.common.ErrMsg;
import com.grenzfrequence.githubdisplayer.di.qualifiers.UiErrMsgQualifier;
import com.grenzfrequence.githubdisplayer.global.HttpResponses;

import java.util.Map;

import dagger.Module;
import dagger.Provides;

/**
 * Created by grenzfrequence on 12/03/17.
 */

@Module
public class ErrorMessagesModul {

    @Provides
    @UiErrMsgQualifier
    Map<Integer, ErrMsg> provideRemoteErrMsg() {
        Map<Integer, ErrMsg> errorMsgs = new ArrayMap<>();

        // default
        errorMsgs.put(
                HttpResponses.HTTP_CUSTOM_DEFAULT,
                ErrMsg.create(R.string.error_no_connection, R.drawable.ic_block_black));

        // business case error messages
        errorMsgs.put(
                HttpResponses.HTTP_FORBIDDEN,
                ErrMsg.create(R.string.error_rate_limit_exceeded, R.drawable.ic_error_outline_black));
        errorMsgs.put(
                HttpResponses.HTTP_NOT_FOUND,
                ErrMsg.create(R.string.error_no_repo_found, R.drawable.ic_info_outline_black));

        return errorMsgs;
    }

}
