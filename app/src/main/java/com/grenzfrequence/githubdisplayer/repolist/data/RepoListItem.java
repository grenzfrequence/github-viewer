package com.grenzfrequence.githubdisplayer.repolist.data;

import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

/**
 * Created by grenzfrequence on 01/03/17.
 */

@AutoValue
public abstract class RepoListItem implements Parcelable {

    public static Builder builder() {
        return new AutoValue_RepoListItem.Builder()
                .repoName("No repo fetched");
    }

    @Json(name = "name")
    public abstract String repoName();

    public static JsonAdapter<RepoListItem> jsonAdapter(Moshi moshi) {
        return new AutoValue_RepoListItem.MoshiJsonAdapter(moshi);
    }

    @AutoValue.Builder
    public static abstract class Builder {

        @NonNull
        public abstract Builder repoName(String repoName);

        @NonNull
        public abstract RepoListItem build();
    }

}
