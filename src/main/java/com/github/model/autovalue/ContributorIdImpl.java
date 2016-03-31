package com.github.model.autovalue;

import com.github.model.ContributorId;
import com.google.auto.value.AutoValue;
import org.jetbrains.annotations.NotNull;

@AutoValue
abstract public class ContributorIdImpl implements ContributorId {
    @NotNull public static ContributorIdImpl create(int id)
    {
        return new AutoValue_ContributorIdImpl(id);
    }
}