package com.github.xavierlepretre.githubpath.impl;

import com.github.model.GithubDbFactory;
import com.github.xavierlepretre.githubpath.VisitedContributorId;
import com.github.model.autovalue.ContributorIdImpl;
import org.junit.Test;

import java.util.*;

import static org.fest.assertions.api.Assertions.assertThat;

public class ShortestPathImplTest_getNewNeighbours {
    @Test(expected = NullPointerException.class)
    public void testNotKnown_throws() throws Exception {
        ShortestPathImpl shortestPath = new ShortestPathImpl(GithubDbFactory.createEmpty());
        shortestPath.getNewNeighbours(
                VisitedContributorIdImpl.create(ContributorIdImpl.create(1), 0), new HashSet<>());
    }

    @Test
    public void testAlone_returnsEmpty() throws Exception {
        ShortestPathImpl shortestPath = new ShortestPathImpl(GithubDbFactory.createAlone0Repo());
        assertThat(shortestPath.getNewNeighbours(
                VisitedContributorIdImpl.create(ContributorIdImpl.create(1), 0),
                new HashSet<>()))
                .isEmpty();
    }

    @Test
    public void test1Neighbour0Visited_returns1WithDistance1() throws Exception {
        ShortestPathImpl shortestPath = new ShortestPathImpl(GithubDbFactory.create2People1Repo());
        Set<VisitedContributorId> newNeighboursSet = shortestPath.getNewNeighbours(
                VisitedContributorIdImpl.create(ContributorIdImpl.create(1), 0),
                new HashSet<>());
        VisitedContributorId expected = VisitedContributorIdImpl.create(ContributorIdImpl.create(3), 1);

        assertThat(newNeighboursSet).containsOnly(expected);
        assertThat(newNeighboursSet.iterator().next().hasSameFieldsAs(expected)).isTrue();
    }

    @Test
    public void test1Neighbour0Visited_returns1WithDistance2() throws Exception {
        ShortestPathImpl shortestPath = new ShortestPathImpl(GithubDbFactory.create2People1Repo());
        Set<VisitedContributorId> newNeighboursSet = shortestPath.getNewNeighbours(
                VisitedContributorIdImpl.create(ContributorIdImpl.create(1), 1),
                new HashSet<>());
        VisitedContributorId expected = VisitedContributorIdImpl.create(ContributorIdImpl.create(3), 2);

        assertThat(newNeighboursSet).containsOnly(expected);
        assertThat(newNeighboursSet.iterator().next().hasSameFieldsAs(expected)).isTrue();
    }

    @Test
    public void test1Neighbour1Visited_returns0() throws Exception {
        ShortestPathImpl shortestPath = new ShortestPathImpl(GithubDbFactory.create2People1Repo());
        assertThat(shortestPath.getNewNeighbours(
                VisitedContributorIdImpl.create(ContributorIdImpl.create(1), 0),
                Collections.singleton(VisitedContributorIdImpl.create(ContributorIdImpl.create(3), 1))))
                .isEmpty();
    }

    @Test
    public void test2Neighbours0Visited_returns2WithDistance1() throws Exception {
        ShortestPathImpl shortestPath = new ShortestPathImpl(GithubDbFactory.create3People1Repo());
        Set<VisitedContributorId> newNeighboursSet = shortestPath.getNewNeighbours(
                VisitedContributorIdImpl.create(ContributorIdImpl.create(1), 0),
                new HashSet<>());
        VisitedContributorIdImpl expected3 = VisitedContributorIdImpl.create(ContributorIdImpl.create(3), 1);
        VisitedContributorIdImpl expected5 = VisitedContributorIdImpl.create(ContributorIdImpl.create(5), 1);

        assertThat(newNeighboursSet).containsOnly(expected3, expected5);
        for (VisitedContributorId visited : newNeighboursSet) {
            assertThat(visited.getDistance()).isEqualTo(1);
        }
    }

    @Test
    public void test2Neighbours0Visited_returns2WithDistance2() throws Exception {
        ShortestPathImpl shortestPath = new ShortestPathImpl(GithubDbFactory.create3People1Repo());
        Set<VisitedContributorId> newNeighboursSet = shortestPath.getNewNeighbours(
                VisitedContributorIdImpl.create(ContributorIdImpl.create(1), 1),
                new HashSet<>());
        VisitedContributorIdImpl expected3 = VisitedContributorIdImpl.create(ContributorIdImpl.create(3), 2);
        VisitedContributorIdImpl expected5 = VisitedContributorIdImpl.create(ContributorIdImpl.create(5), 2);

        assertThat(newNeighboursSet).containsOnly(expected3, expected5);
        for (VisitedContributorId visited : newNeighboursSet) {
            assertThat(visited.getDistance()).isEqualTo(2);
        }
    }

    @Test
    public void test2Neighbours1Visited_returns1WithDistance1() throws Exception {
        ShortestPathImpl shortestPath = new ShortestPathImpl(GithubDbFactory.create3People1Repo());
        Set<VisitedContributorId> newNeighboursSet = shortestPath.getNewNeighbours(
                VisitedContributorIdImpl.create(ContributorIdImpl.create(1), 0),
                Collections.singleton(VisitedContributorIdImpl.create(ContributorIdImpl.create(3), 1)));
        VisitedContributorId expected = VisitedContributorIdImpl.create(ContributorIdImpl.create(5), 1);

        assertThat(newNeighboursSet).containsOnly(expected);
        assertThat(newNeighboursSet.iterator().next().hasSameFieldsAs(expected));
    }

    @Test
    public void test2Neighbours1Visited_returns1WithDistance2() throws Exception {
        ShortestPathImpl shortestPath = new ShortestPathImpl(GithubDbFactory.create3People1Repo());
        Set<VisitedContributorId> newNeighboursSet = shortestPath.getNewNeighbours(
                VisitedContributorIdImpl.create(ContributorIdImpl.create(1), 1),
                Collections.singleton(VisitedContributorIdImpl.create(ContributorIdImpl.create(3), 2)));
        VisitedContributorId expected = VisitedContributorIdImpl.create(ContributorIdImpl.create(5), 2);

        assertThat(newNeighboursSet).containsOnly(expected);
        assertThat(newNeighboursSet.iterator().next().hasSameFieldsAs(expected));
    }

    @Test
    public void test2Neighbours2Visited_returns0() throws Exception {
        ShortestPathImpl shortestPath = new ShortestPathImpl(GithubDbFactory.create3People1Repo());
        assertThat(shortestPath.getNewNeighbours(
                VisitedContributorIdImpl.create(ContributorIdImpl.create(1), 0),
                new HashSet<>(Arrays.<VisitedContributorId>asList(
                        VisitedContributorIdImpl.create(ContributorIdImpl.create(3), 1),
                        VisitedContributorIdImpl.create(ContributorIdImpl.create(5), 1)))))
                .isEmpty();
    }
}