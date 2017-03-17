package com.grenzfrequence.githubdisplayer.repolist.data;

import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.grenzfrequence.githubdisplayer.webservice.type_adapter.JsonDate;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import org.joda.time.DateTime;

/**
 * Created by grenzfrequence on 01/03/17.
 */

@AutoValue
public abstract class RepoModel implements Parcelable, Comparable<RepoModel> {

    @NonNull
    public static Builder builder() {
        return new AutoValue_RepoModel.Builder()
                .repoName("No repo fetched");
    }

    @Json(name = "owner")
    @NonNull
    public abstract OwnerModel repoOwner();

    @Json(name = "id")
    @NonNull
    public abstract Integer repoId();

    @Json(name = "name")
    @NonNull
    public abstract String repoName();

    @Json(name = "description")
    @Nullable
    public abstract String repoDescription();

    @Json(name = "updated_at")
    @NonNull
    @JsonDate
    public abstract DateTime repoUpdateDate();

    @NonNull
    public static JsonAdapter<RepoModel> jsonAdapter(Moshi moshi) {
        return new AutoValue_RepoModel.MoshiJsonAdapter(moshi);
    }

    @AutoValue.Builder
    public static abstract class Builder {

        @NonNull
        public abstract Builder repoOwner(@NonNull OwnerModel repoOwner);

        @NonNull
        public abstract Builder repoId(@NonNull Integer id);

        @NonNull
        public abstract Builder repoName(@NonNull String repoName);

        @NonNull
        public abstract Builder repoDescription(@Nullable String description);

        @NonNull
        public abstract Builder repoUpdateDate(@JsonDate @NonNull DateTime updateDate);

        @NonNull
        public abstract RepoModel build();
    }

    @Override
    public int compareTo(@NonNull RepoModel other) {
        return repoUpdateDate().compareTo(other.repoUpdateDate()) * -1;
    }
}
