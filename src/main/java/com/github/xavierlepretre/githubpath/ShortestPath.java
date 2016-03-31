package com.github.xavierlepretre.githubpath;

import com.github.model.ContributorId;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface ShortestPath {
    @NotNull
    Optional<Integer> getDistance(
            @NotNull ContributorId from,
            @NotNull ContributorId to);
}