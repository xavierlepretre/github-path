package com.github.model.autovalue;

import com.github.model.*;
import com.github.model.impl.ContributionHashSet;
import com.google.auto.value.AutoValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

@AutoValue
abstract public class GithubDbImpl implements GithubDb {
    @NotNull
    abstract Map<ContributorId, Set<RepositoryId>> getContributions();

    @NotNull
    abstract Map<RepositoryId, Set<ContributorId>> getContributors();

    @NotNull
    public static GithubDbImpl create(
            @NotNull Map<ContributorId, Set<RepositoryId>> contributions,
            @NotNull Map<RepositoryId, Set<ContributorId>> contributors) {
        return new AutoValue_GithubDbImpl(
                Collections.unmodifiableMap(contributions),
                Collections.unmodifiableMap(contributors));
    }

    @Nullable
    public Set<RepositoryId> getRepositories(@NotNull ContributorId contributor) {
        Set<RepositoryId> repositories = getContributions().get(contributor);
        return repositories == null ? null : Collections.unmodifiableSet(repositories);
    }

    @Nullable
    public Set<ContributorId> getContributors(@NotNull RepositoryId repository) {
        Set<ContributorId> contributors = getContributors().get(repository);
        return contributors == null ? null : Collections.unmodifiableSet(contributors);
    }

    @NotNull
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        @NotNull
        private final ContributionSet contributions;

        public Builder() {
            this(new ContributionHashSet());
        }

        public Builder(@NotNull ContributionSet contributions) {
            this.contributions = contributions;
        }

        @NotNull
        public Builder add(@NotNull Contribution contribution) {
            contributions.add(contribution);
            return this;
        }

        @NotNull
        public GithubDbImpl build() {
            return GithubDbImpl.create(
                    contributions.getContributions(),
                    contributions.getContributors());
        }
    }
}