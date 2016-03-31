package com.github.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Contribution {
    @NotNull
    ContributorId getContributor();

    /**
     * Nullable because a user may have not contributed anything.
     * @return
     */
    @Nullable
    RepositoryId getRepository();
}