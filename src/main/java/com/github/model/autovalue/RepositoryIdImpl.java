package com.github.model.autovalue;

import com.github.model.RepositoryId;
import com.google.auto.value.AutoValue;
import org.jetbrains.annotations.NotNull;

@AutoValue
abstract public class RepositoryIdImpl implements RepositoryId {
    @NotNull
    public static RepositoryId create(int id)
    {
        return new AutoValue_RepositoryIdImpl(id);
    }
}