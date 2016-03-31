package com.github.model.impl;

import com.github.model.autovalue.ContributionImpl;
import com.github.model.autovalue.ContributorIdImpl;
import com.github.model.autovalue.RepositoryIdImpl;
import org.fest.assertions.data.MapEntry;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static org.fest.assertions.api.Assertions.assertThat;

public class ContributionHashSetTest {
    @Test
    public void testEmpty_returnsEmpty() throws Exception {
        //noinspection MismatchedQueryAndUpdateOfCollection
        ContributionHashSet set = new ContributionHashSet();

        assertThat(set.getContributions()).isEmpty();
        assertThat(set.getContributors()).isEmpty();
    }

    @Test
    public void testOne_returns1ListEach() throws Exception {
        ContributionHashSet set = new ContributionHashSet();
        set.add(ContributionImpl.create(
                ContributorIdImpl.create(1),
                RepositoryIdImpl.create(2)));

        assertThat(set.getContributions()).hasSize(1);
        assertThat(set.getContributions()).contains(MapEntry.entry(
                ContributorIdImpl.create(1),
                Collections.singleton(RepositoryIdImpl.create(2))));
        assertThat(set.getContributors()).hasSize(1);
        assertThat(set.getContributors()).contains(MapEntry.entry(
                RepositoryIdImpl.create(2),
                Collections.singleton(ContributorIdImpl.create(1))));
    }

    @Test
    public void test2Contributors1Repo() throws Exception {
        ContributionHashSet set = new ContributionHashSet();
        set.add(ContributionImpl.create(
                ContributorIdImpl.create(1),
                RepositoryIdImpl.create(2)));
        set.add(ContributionImpl.create(
                ContributorIdImpl.create(3),
                RepositoryIdImpl.create(2)));

        assertThat(set.getContributions()).hasSize(2);
        assertThat(set.getContributions()).contains(MapEntry.entry(
                ContributorIdImpl.create(1),
                Collections.singleton(RepositoryIdImpl.create(2))));
        assertThat(set.getContributions()).contains(MapEntry.entry(
                ContributorIdImpl.create(3),
                Collections.singleton(RepositoryIdImpl.create(2))));
        assertThat(set.getContributors()).hasSize(1);
        assertThat(set.getContributors()).contains(MapEntry.entry(
                RepositoryIdImpl.create(2),
                new HashSet<>(Arrays.asList(
                        ContributorIdImpl.create(1),
                        ContributorIdImpl.create(3)))));
    }

    @Test
    public void test2Contributors2Repo() throws Exception {
        ContributionHashSet set = new ContributionHashSet();
        set.add(ContributionImpl.create(
                ContributorIdImpl.create(1),
                RepositoryIdImpl.create(2)));
        set.add(ContributionImpl.create(
                ContributorIdImpl.create(1),
                RepositoryIdImpl.create(4)));
        set.add(ContributionImpl.create(
                ContributorIdImpl.create(3),
                RepositoryIdImpl.create(2)));
        set.add(ContributionImpl.create(
                ContributorIdImpl.create(3),
                RepositoryIdImpl.create(4)));

        assertThat(set.getContributions()).hasSize(2);
        assertThat(set.getContributions()).contains(MapEntry.entry(
                ContributorIdImpl.create(1),
                new HashSet<>(Arrays.asList(
                        RepositoryIdImpl.create(2),
                        RepositoryIdImpl.create(4)))));
        assertThat(set.getContributions()).contains(MapEntry.entry(
                ContributorIdImpl.create(3),
                new HashSet<>(Arrays.asList(
                        RepositoryIdImpl.create(2),
                        RepositoryIdImpl.create(4)))));
        assertThat(set.getContributors()).hasSize(2);
        assertThat(set.getContributors()).contains(MapEntry.entry(
                RepositoryIdImpl.create(2),
                new HashSet<>(Arrays.asList(
                        ContributorIdImpl.create(1),
                        ContributorIdImpl.create(3)))));
        assertThat(set.getContributors()).contains(MapEntry.entry(
                RepositoryIdImpl.create(4),
                new HashSet<>(Arrays.asList(
                        ContributorIdImpl.create(1),
                        ContributorIdImpl.create(3)))));
    }
}