package com.github.model;

import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Set;

public interface ContributionSet extends Set<Contribution> {
    @NotNull
    Map<ContributorId, Set<RepositoryId>> getContributions();

    @NotNull
    Map<RepositoryId, Set<ContributorId>> getContributors();
}