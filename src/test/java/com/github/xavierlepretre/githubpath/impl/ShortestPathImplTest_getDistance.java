package com.github.xavierlepretre.githubpath.impl;

import com.github.model.GithubDbFactory;
import com.github.model.autovalue.ContributorIdImpl;
import com.github.xavierlepretre.githubpath.ShortestPath;
import org.junit.Test;

import java.util.Optional;

import static org.fest.assertions.api.Assertions.assertThat;

public class ShortestPathImplTest_getDistance {
    @Test(expected = IllegalArgumentException.class, timeout = 1000)
    public void testFromNotFound_throws() throws Exception {
        ShortestPath shortestPath = new ShortestPathImpl(GithubDbFactory.createAlone1Repo());
        shortestPath.getDistance(ContributorIdImpl.create(3), ContributorIdImpl.create(1));
    }

    @Test(expected = IllegalArgumentException.class, timeout = 1000)
    public void testToNotFound_throws() throws Exception {
        ShortestPath shortestPath = new ShortestPathImpl(GithubDbFactory.createAlone1Repo());
        shortestPath.getDistance(ContributorIdImpl.create(1), ContributorIdImpl.create(3));
    }

    @Test(timeout = 1000)
    public void testAloneNoRepo_returns0() throws Exception {
        ShortestPath shortestPath = new ShortestPathImpl(GithubDbFactory.createAlone0Repo());
        Optional<Integer> distance =shortestPath.getDistance(
                ContributorIdImpl.create(1),
                ContributorIdImpl.create(1));
        assertThat(distance.isPresent()).isTrue();
        //noinspection OptionalGetWithoutIsPresent
        assertThat(distance.get()).isEqualTo(0);
    }

    @Test(timeout = 1000)
    public void testAlone1Repo_returns0() throws Exception {
        ShortestPath shortestPath = new ShortestPathImpl(GithubDbFactory.createAlone1Repo());
        Optional<Integer> distance =shortestPath.getDistance(
                ContributorIdImpl.create(1),
                ContributorIdImpl.create(1));
        assertThat(distance.isPresent()).isTrue();
        //noinspection OptionalGetWithoutIsPresent
        assertThat(distance.get()).isEqualTo(0);
    }

    @Test(timeout = 1000)
    public void test2People1Repo_returns1() throws Exception {
        ShortestPath shortestPath = new ShortestPathImpl(GithubDbFactory.create2People1Repo());
        Optional<Integer> distance =shortestPath.getDistance(
                ContributorIdImpl.create(1),
                ContributorIdImpl.create(3));
        assertThat(distance.isPresent()).isTrue();
        //noinspection OptionalGetWithoutIsPresent
        assertThat(distance.get()).isEqualTo(1);
    }

    @Test(timeout = 1000)
    public void test2People2Repo_returns1() throws Exception {
        ShortestPath shortestPath = new ShortestPathImpl(GithubDbFactory.create2People2Repo());
        Optional<Integer> distance =shortestPath.getDistance(
                ContributorIdImpl.create(1),
                ContributorIdImpl.create(3));
        assertThat(distance.isPresent()).isTrue();
        //noinspection OptionalGetWithoutIsPresent
        assertThat(distance.get()).isEqualTo(1);
    }

    @Test(timeout = 1000)
    public void test3People1Repo_returns1() throws Exception {
        ShortestPath shortestPath = new ShortestPathImpl(GithubDbFactory.create3People1Repo());
        Optional<Integer> distance =shortestPath.getDistance(
                ContributorIdImpl.create(1),
                ContributorIdImpl.create(5));
        assertThat(distance.isPresent()).isTrue();
        //noinspection OptionalGetWithoutIsPresent
        assertThat(distance.get()).isEqualTo(1);
    }

    @Test(timeout = 1000)
    public void test2PeopleSeparate_returnsEmpty() throws Exception {
        ShortestPath shortestPath = new ShortestPathImpl(GithubDbFactory.create2PeopleSeparate());
        Optional<Integer> distance =shortestPath.getDistance(
                ContributorIdImpl.create(1),
                ContributorIdImpl.create(3));
        assertThat(distance.isPresent()).isFalse();
    }

    @Test(timeout = 1000)
    public void test3PeopleInLine_returns2() throws Exception {
        ShortestPath shortestPath = new ShortestPathImpl(GithubDbFactory.create3PeopleInLine());
        Optional<Integer> distance =shortestPath.getDistance(
                ContributorIdImpl.create(1),
                ContributorIdImpl.create(5));
        assertThat(distance.isPresent()).isTrue();
        //noinspection OptionalGetWithoutIsPresent
        assertThat(distance.get()).isEqualTo(2);
    }
}