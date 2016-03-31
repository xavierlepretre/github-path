package com.github.xavierlepretre.githubpath.impl;

import com.github.model.ContributorId;
import com.github.xavierlepretre.githubpath.VisitedContributorId;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Set;

final public class VisitedContributorIdImpl implements VisitedContributorId {
    @NotNull
    private final ContributorId contributor;
    private final int distance;

    private VisitedContributorIdImpl(@NotNull ContributorId contributor, int distance) {
        this.contributor = contributor;
        this.distance = distance;
    }

    @NotNull
    public static VisitedContributorIdImpl create(
            @NotNull ContributorId contributor,
            int distance) {
        return new VisitedContributorIdImpl(contributor, distance);
    }

    @Nullable
    public static Set<VisitedContributorId> create(
            @Nullable Set<ContributorId> contributors,
            int distance) {
        if (contributors == null) {
            return null;
        }
        Set<VisitedContributorId> set = new HashSet<>();
        for (ContributorId contributor : contributors) {
            set.add(new VisitedContributorIdImpl(contributor, distance));
        }
        return set;
    }

    @Override
    @NotNull
    public ContributorId getContributor() {
        return contributor;
    }

    @Override
    public int getDistance() {
        return distance;
    }

    @Override
    public int hashCode() {
        return contributor.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(VisitedContributorId.class.isAssignableFrom(obj.getClass()))) {
            return false;
        }
        return contributor.equals(((VisitedContributorId) obj).getContributor());
    }

    @Override
    public boolean hasSameFieldsAs(@NotNull VisitedContributorId visitedContributorId) {
        return visitedContributorId.getContributor().equals(contributor)
                && visitedContributorId.getDistance() == distance;
    }
}