package org.modmappings.crispycomputingmachine.cache;

import org.modmappings.mmms.repository.model.mapping.mappable.MappableTypeDMO;

import java.util.UUID;

public class MappingCacheEntry {

    private String output;
    private UUID mappableId;
    private UUID versionedMappableId;
    private MappableTypeDMO mappableType;
    private String parentClassOutput;
    private String parentMethodOutput;
    private UUID gameVersionedId;
    private String gameVersionName;
    private String type;
    private String descriptor;

    public MappingCacheEntry(final String output, final UUID mappableId, final UUID versionedMappableId, final MappableTypeDMO mappableType, final String parentClassOutput, final String parentMethodOutput, final UUID gameVersionedId, final String gameVersionName, final String type, final String descriptor) {
        this.output = output;
        this.mappableId = mappableId;
        this.versionedMappableId = versionedMappableId;
        this.mappableType = mappableType;
        this.parentClassOutput = parentClassOutput;
        this.parentMethodOutput = parentMethodOutput;
        this.gameVersionedId = gameVersionedId;
        this.gameVersionName = gameVersionName;
        this.type = type;
        this.descriptor = descriptor;
    }

    public String getOutput() {
        return output;
    }

    public UUID getMappableId() {
        return mappableId;
    }

    public UUID getVersionedMappableId() {
        return versionedMappableId;
    }

    public MappableTypeDMO getMappableType() {
        return mappableType;
    }

    public String getParentClassOutput() {
        return parentClassOutput;
    }

    public String getParentMethodOutput() {
        return parentMethodOutput;
    }

    public UUID getGameVersionedId() {
        return gameVersionedId;
    }

    public String getGameVersionName() {
        return gameVersionName;
    }

    public String getType() {
        return type;
    }

    public String getDescriptor() {
        return descriptor;
    }
}
