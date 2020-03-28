package org.modmappings.crispycomputingmachine.processors.mcpconfig;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ConfigurationMCPConfigMappingMinecraftVersionFilter implements ItemProcessor<String, String> {

    @Value("${importer.versions:}")
    String[] versionsToImport;

    @Override
    public String process(final String item) throws Exception {
        final String gameVersion = item.split("-")[0];

        if (versionsToImport == null || versionsToImport.length == 0)
            return item;

        return Arrays.stream(versionsToImport).anyMatch(v -> v.equals(gameVersion)) ? item : null;
    }
}