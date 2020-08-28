package org.modmappings.crispycomputingmachine.processors.base.parsing.simple;

import org.jetbrains.annotations.NotNull;
import org.modmappings.crispycomputingmachine.model.mappings.ExternalMapping;

import java.util.Map;
import java.util.Set;

@FunctionalInterface
public interface IParametersPostProcessor
{
    void apply(@NotNull final String releaseName, @NotNull final Set<ExternalMapping> classes, @NotNull final Set<ExternalMapping> methods, @NotNull final Set<ExternalMapping> parameters);

    IParametersPostProcessor NOOP = (releaseName, classes, methods, parameters) -> {}; //Noop
}
