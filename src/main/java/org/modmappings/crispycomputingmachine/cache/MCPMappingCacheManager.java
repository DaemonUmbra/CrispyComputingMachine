package org.modmappings.crispycomputingmachine.cache;

import com.google.common.collect.ImmutableList;
import org.modmappings.crispycomputingmachine.utils.Constants;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@StepScope
public class MCPMappingCacheManager extends AbstractMappingCacheManager
{
    public MCPMappingCacheManager(final DatabaseClient databaseClient) {
        super(databaseClient);
    }

    private UUID getMCPMappingTypeId() {
        return getOrCreateIdForMappingType(
                Constants.MCP_MAPPING_NAME,
                true,
                true,
                Constants.MCP_MAPPING_STATE_IN,
                Constants.MCP_MAPPING_STATE_OUT
        );
    }

    @Override
    protected List<UUID> getMappingTypeIds() {
        return ImmutableList.of(getMCPMappingTypeId());
    }
}
