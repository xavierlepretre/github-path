package com.github.xavierlepretre.githubpath.impl;

import com.github.model.ContributorId;
import com.github.model.GithubDb;
import com.github.model.RepositoryId;
import com.github.xavierlepretre.githubpath.ShortestPath;
import com.github.xavierlepretre.githubpath.VisitedContributorId;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class ShortestPathImpl implements ShortestPath {
    @NotNull
    private final GithubDb db;

    public ShortestPathImpl(@NotNull GithubDb db) {
        this.db = db;
    }

    @NotNull
    @Override
    public Optional<Integer> getDistance(@NotNull ContributorId from, @NotNull ContributorId to) {
        if (db.getRepositories(from) == null) {
            throw new IllegalArgumentException("Contributor " + from.getId() + " not found");
        }
        if (db.getRepositories(to) == null) {
            throw new IllegalArgumentException("Contributor " + to.getId() + " not found");
        }
        if (from.equals(to)) {
            return Optional.of(0);
        }
        Set<VisitedContributorId> alreadyVisited = new HashSet<>();
        Queue<VisitedContributorId> remainingToVisit = new ArrayDeque<>();
        remainingToVisit.add(VisitedContributorIdImpl.create(from, 0));

        VisitedContributorId current;
        while(!remainingToVisit.isEmpty()) {
            current = remainingToVisit.remove();
            alreadyVisited.add(current);
            remainingToVisit.addAll(getNewNeighbours(current, alreadyVisited));
            if (current.getContributor().equals(to)) {
                return Optional.of(current.getDistance());
            }
        }
        return Optional.empty();
    }

    @NotNull Set<VisitedContributorId> getNewNeighbours(
            @NotNull VisitedContributorId contributorId,
            @NotNull Set<VisitedContributorId> alreadyVisited) {
        Set<VisitedContributorId> newNeighbours = new HashSet<>();
        Set<RepositoryId> contributed = db.getRepositories(contributorId.getContributor());
        if (contributed == null) {
            throw new NullPointerException("You should check whether the contributor has repositories");
        }
        Set<VisitedContributorId> neighbourSet = new HashSet<>();
        for (RepositoryId repository : contributed) {
            neighbourSet = VisitedContributorIdImpl.create(
                    db.getContributors(repository),
                    contributorId.getDistance() + 1);
            if (neighbourSet == null) {
                throw new NullPointerException("Strange, at this stage, we should be confident there are neighbours");
            }
            for (VisitedContributorId neighbour : neighbourSet) {
                if (!neighbour.equals(contributorId) && !alreadyVisited.contains(neighbour)) {
                    newNeighbours.add(neighbour);
                }
            }
        }
        return newNeighbours;
    }
}