package com.github.xavierlepretre.githubpath.impl;

import com.github.model.autovalue.ContributorIdImpl;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class VisitedContributorIdImplTest {
    @Test
    public void testEquals() throws Exception {
        EqualsVerifier.forClass(VisitedContributorIdImpl.class)
                .withIgnoredFields("distance")
                .verify();
    }

    @Test
    public void testHasSameFields_same() throws Exception {
        assertThat(
                VisitedContributorIdImpl.create(
                        ContributorIdImpl.create(1),
                        1)
                        .hasSameFieldsAs(
                                VisitedContributorIdImpl.create(
                                        ContributorIdImpl.create(1),
                                        1)))
                .isTrue();
    }

    @Test
    public void testHasSameFields_differContributor() throws Exception {
        assertThat(
                VisitedContributorIdImpl.create(
                        ContributorIdImpl.create(1),
                        1)
                        .hasSameFieldsAs(
                                VisitedContributorIdImpl.create(
                                        ContributorIdImpl.create(2),
                                        1)))
                .isFalse();
    }

    @Test
    public void testHasSameFields_differDistance() throws Exception {
        assertThat(
                VisitedContributorIdImpl.create(
                        ContributorIdImpl.create(1),
                        1)
                        .hasSameFieldsAs(
                                VisitedContributorIdImpl.create(
                                        ContributorIdImpl.create(1),
                                        2)))
                .isFalse();
    }

    @Test
    public void testHasSameFields_differBoth() throws Exception {
        assertThat(
                VisitedContributorIdImpl.create(
                        ContributorIdImpl.create(1),
                        1)
                        .hasSameFieldsAs(
                                VisitedContributorIdImpl.create(
                                        ContributorIdImpl.create(2),
                                        2)))
                .isFalse();
    }
}