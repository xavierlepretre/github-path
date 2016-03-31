package com.github.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public interface GithubDb {
    @Nullable
    Set<RepositoryId> getRepositories(@NotNull ContributorId contributor);

    @Nullable
    Set<ContributorId> getContributors(@NotNull RepositoryId repository);
}