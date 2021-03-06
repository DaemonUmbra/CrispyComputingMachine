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
public class VanillaAndExternalMappingCacheManager extends AbstractMappingCacheManager {

    public VanillaAndExternalMappingCacheManager(final DatabaseClient databaseClient) {
        super(databaseClient);
    }

    private UUID getOfficialMappingTypeId()
    {
        return getOrCreateIdForMappingType(
                Constants.OFFICIAL_MAPPING_NAME,
                false,
                false,
                Constants.OFFICIAL_MAPPING_STATE_IN,
                Constants.OFFICIAL_MAPPING_STATE_OUT
        );
    }

    private UUID getExternalMappingTypeId()
    {
        return getOrCreateIdForMappingType(
                Constants.EXTERNAL_MAPPING_NAME,
                true,
                false,
                Constants.EXTERNAL_MAPPING_STATE_IN,
                Constants.EXTERNAL_MAPPING_STATE_OUT
        );
    }

    @Override
    protected List<UUID> getMappingTypeIds() {
        return ImmutableList.of(getOfficialMappingTypeId(), getExternalMappingTypeId());
    }
}
