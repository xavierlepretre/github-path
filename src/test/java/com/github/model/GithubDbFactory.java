package com.github.model;

import com.github.model.autovalue.ContributionImpl;
import com.github.model.autovalue.ContributorIdImpl;
import com.github.model.autovalue.GithubDbImpl;
import com.github.model.autovalue.RepositoryIdImpl;
import org.jetbrains.annotations.NotNull;

public class GithubDbFactory {
    @NotNull
    public static GithubDb createEmpty() {
        return GithubDbImpl.builder().build();
    }

    @NotNull
    public static GithubDb createAlone0Repo() {
        return GithubDbImpl.builder()
                .add(ContributionImpl.create(ContributorIdImpl.create(1)))
                .build();
    }

    @NotNull
    public static GithubDb createAlone1Repo() {
        return GithubDbImpl.builder()
                .add(ContributionImpl.create(ContributorIdImpl.create(1), RepositoryIdImpl.create(2)))
                .build();
    }

    @NotNull
    public static GithubDb create2People1Repo() {
        return GithubDbImpl.builder()
                .add(ContributionImpl.create(ContributorIdImpl.create(1), RepositoryIdImpl.create(2)))
                .add(ContributionImpl.create(ContributorIdImpl.create(3), RepositoryIdImpl.create(2)))
                .build();
    }

    @NotNull
    public static GithubDb create3People1Repo() {
        return GithubDbImpl.builder()
                .add(ContributionImpl.create(ContributorIdImpl.create(1), RepositoryIdImpl.create(2)))
                .add(ContributionImpl.create(ContributorIdImpl.create(3), RepositoryIdImpl.create(2)))
                .add(ContributionImpl.create(ContributorIdImpl.create(5), RepositoryIdImpl.create(2)))
                .build();
    }

    @NotNull
    public static GithubDb create2People2Repo() {
        return GithubDbImpl.builder()
                .add(ContributionImpl.create(ContributorIdImpl.create(1), RepositoryIdImpl.create(2)))
                .add(ContributionImpl.create(ContributorIdImpl.create(1), RepositoryIdImpl.create(4)))
                .add(ContributionImpl.create(ContributorIdImpl.create(3), RepositoryIdImpl.create(2)))
                .add(ContributionImpl.create(ContributorIdImpl.create(3), RepositoryIdImpl.create(4)))
                .build();
    }

    @NotNull
    public static GithubDb create2PeopleSeparate() {
        return GithubDbImpl.builder()
                .add(ContributionImpl.create(ContributorIdImpl.create(1), RepositoryIdImpl.create(2)))
                .add(ContributionImpl.create(ContributorIdImpl.create(3), RepositoryIdImpl.create(4)))
                .build();
    }

    @NotNull
    public static GithubDb create3PeopleInLine() {
        return GithubDbImpl.builder()
                .add(ContributionImpl.create(ContributorIdImpl.create(1), RepositoryIdImpl.create(2)))
                .add(ContributionImpl.create(ContributorIdImpl.create(3), RepositoryIdImpl.create(2)))
                .add(ContributionImpl.create(ContributorIdImpl.create(3), RepositoryIdImpl.create(4)))
                .add(ContributionImpl.create(ContributorIdImpl.create(5), RepositoryIdImpl.create(4)))
                .build();
    }
}