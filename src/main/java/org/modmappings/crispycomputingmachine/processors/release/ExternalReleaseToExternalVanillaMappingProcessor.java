package org.modmappings.crispycomputingmachine.processors.release;

import org.modmappings.crispycomputingmachine.model.mappings.ExternalClass;
import org.modmappings.crispycomputingmachine.model.mappings.ExternalMappableType;
import org.modmappings.crispycomputingmachine.model.mappings.ExternalRelease;
import org.modmappings.crispycomputingmachine.model.mappings.ExternalVanillaMapping;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExternalReleaseToExternalVanillaMappingProcessor implements ItemProcessor<ExternalRelease, List<ExternalVanillaMapping>> {

    @Override
    public List<ExternalVanillaMapping> process(final ExternalRelease item) throws Exception {
        return item.getClasses().stream().flatMap(externalClass -> {
            final List<ExternalVanillaMapping> mappingsForVersion = new ArrayList<>();

            String parentClassOutputMapping = null;
            if(externalClass.getOutput().contains("$"))
                parentClassOutputMapping = externalClass.getOutput().substring(0, externalClass.getOutput().lastIndexOf("$"));

            mappingsForVersion.add(
                    new ExternalVanillaMapping(
                            externalClass.getInput(),
                            externalClass.getOutput(),
                            ExternalMappableType.CLASS,
                            item.getName(),
                            item.getReleasedOn(),
                            parentClassOutputMapping,
                            null,
                            null,
                            externalClass.getVisibility(),
                            externalClass.isStatic(),
                            null,
                            null,
                            externalClass.getSuperClasses().stream().map(ExternalClass::getOutput).collect(Collectors.toList())
                    )
            );

            externalClass.getMethods().stream().map(externalMethod -> new ExternalVanillaMapping(
                    externalMethod.getInput(),
                    externalMethod.getOutput(),
                    ExternalMappableType.METHOD,
                    item.getName(),
                    item.getReleasedOn(),
                    externalClass.getOutput(),
                    null,
                    null,
                    externalMethod.getVisibility(),
                    externalMethod.isStatic(),
                    null,
                    externalMethod.getSignature(),
                    new ArrayList<>())) //TODO: Insert the override data from here.
                .forEach(mappingsForVersion::add);

            externalClass.getFields().stream().map(externalField -> new ExternalVanillaMapping(
                    externalField.getInput(),
                    externalField.getOutput(),
                    ExternalMappableType.FIELD,
                    item.getName(),
                    item.getReleasedOn(),
                    externalClass.getOutput(),
                    null,
                    null,
                    externalField.getVisibility(),
                    externalField.isStatic(),
                    externalField.getType(),
                    null,
                    new ArrayList<>()))
            .forEach(mappingsForVersion::add);

            return mappingsForVersion.stream();
        }).collect(Collectors.toList());
    }
}
