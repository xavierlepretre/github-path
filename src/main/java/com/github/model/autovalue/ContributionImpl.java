package com.github.model.autovalue;

import com.github.model.Contribution;
import com.github.model.ContributorId;
import com.github.model.RepositoryId;
import com.google.auto.value.AutoValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@AutoValue
abstract public class ContributionImpl implements Contribution {
    @NotNull public static ContributionImpl create(
            @NotNull ContributorId contributor) {
        return create(contributor, null);
    }
    @NotNull public static ContributionImpl create(
            @NotNull ContributorId contributor,
            @Nullable RepositoryId repository) {
        return new AutoValue_ContributionImpl(contributor, repository);
    }
}