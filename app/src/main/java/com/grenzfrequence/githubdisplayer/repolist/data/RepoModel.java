package com.grenzfrequence.githubdisplayer.repolist.data;

import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

/**
 * Created by grenzfrequence on 01/03/17.
 */

@AutoValue
public abstract class RepoModel implements Parcelable {

    @NonNull
    public static Builder builder() {
        return new AutoValue_RepoModel.Builder()
                .repoName("No repo fetched");
    }

    @Json(name = "owner")
    public abstract OwnerModel repoOwner();

    @Json(name = "id")
    public abstract Integer repoId();

    @Json(name = "name")
    public abstract String repoName();

    @Json(name = "description")
    @Nullable
    public abstract String repoDescription();

    @NonNull
    public static JsonAdapter<RepoModel> jsonAdapter(Moshi moshi) {
        return new AutoValue_RepoModel.MoshiJsonAdapter(moshi);
    }

    @AutoValue.Builder
    public static abstract class Builder {

        @NonNull
        public abstract Builder repoOwner(OwnerModel repoOwner);

        @NonNull
        public abstract Builder repoId(Integer id);


        @NonNull
        public abstract Builder repoName(String repoName);

        @NonNull
        public abstract Builder repoDescription(String description);


        @NonNull
        public abstract RepoModel build();
    }

}
