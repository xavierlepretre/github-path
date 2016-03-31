package com.github.model.impl;

import com.github.model.Contribution;
import com.github.model.ContributionSet;
import com.github.model.ContributorId;
import com.github.model.RepositoryId;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class ContributionHashSet extends HashSet<Contribution>
        implements ContributionSet {
    public ContributionHashSet() {
        super();
    }

    public ContributionHashSet(Collection<Contribution> c) {
        super(c);
    }

    public ContributionHashSet(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public ContributionHashSet(int initialCapacity) {
        super(initialCapacity);
    }

    @NotNull
    @Override
    public Map<ContributorId, Set<RepositoryId>> getContributions() {
        Map<ContributorId, Set<RepositoryId>> contributions = new HashMap<>();
        for (Contribution contribution : this) {
            if (!contributions.containsKey(contribution.getContributor())) {
                contributions.put(contribution.getContributor(), new HashSet<>());
            }
            if (contribution.getRepository() != null) {
                contributions.get(contribution.getContributor()).add(contribution.getRepository());
            }
        }
        return contributions;
    }

    @NotNull
    @Override
    public Map<RepositoryId, Set<ContributorId>> getContributors() {
        Map<RepositoryId, Set<ContributorId>> contributors = new HashMap<>();
        for (Contribution contribution : this) {
            if (contribution.getRepository() != null) {
                if (!contributors.containsKey(contribution.getRepository())) {
                    contributors.put(contribution.getRepository(), new HashSet<>());
                }
                contributors.get(contribution.getRepository()).add(contribution.getContributor());
            }
        }

        return contributors;
    }
}