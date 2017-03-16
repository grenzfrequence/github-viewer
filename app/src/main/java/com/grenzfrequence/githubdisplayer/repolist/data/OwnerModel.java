package com.grenzfrequence.githubdisplayer.repolist.data;

import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

/**
 * Created by grenzfrequence on 15/03/17.
 */

@AutoValue
public abstract class OwnerModel implements Parcelable {

    @NonNull
    public static Builder builder() {
        return new AutoValue_OwnerModel.Builder();
    }

    @NonNull
    public abstract Integer id();

    @NonNull
    @Json(name = "login")
    public abstract String name();

    @Nullable
    @Json(name = "avatar_url")
    public abstract String avatarLink();

    @NonNull
    public static JsonAdapter<OwnerModel> jsonAdapter(Moshi moshi) {
        return new AutoValue_OwnerModel.MoshiJsonAdapter(moshi);
    }

    @AutoValue.Builder
    public static abstract class Builder {

        @NonNull
        public abstract Builder id(Integer id);

        @NonNull
        public abstract Builder name(String name);

        @Nullable
        public abstract Builder avatarLink(String link);

        @NonNull
        public abstract OwnerModel build();
    }

}
