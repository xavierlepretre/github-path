package com.github.xavierlepretre.githubpath;

import com.github.model.ContributorId;
import org.jetbrains.annotations.NotNull;

public interface VisitedContributorId {
    @NotNull
    ContributorId getContributor();

    int getDistance();

    boolean hasSameFieldsAs(@NotNull VisitedContributorId visitedContributorId);

    /**
     * It should only compare on the {@link ContributorId}.
     * @param o
     * @return
     */
    boolean equals(Object o);

    /**
     * It should only use the {@link ContributorId}.
     * @return
     */
    int hashCode();
}