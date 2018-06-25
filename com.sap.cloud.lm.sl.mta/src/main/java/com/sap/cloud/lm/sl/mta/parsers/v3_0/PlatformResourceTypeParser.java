package com.sap.cloud.lm.sl.mta.parsers.v3_0;

import static com.sap.cloud.lm.sl.mta.handlers.v3_0.Schemas.PTF_RESOURCE_TYPE;

import java.util.Map;

import com.sap.cloud.lm.sl.common.ParsingException;
import com.sap.cloud.lm.sl.mta.model.v3_0.TargetResourceType;
import com.sap.cloud.lm.sl.mta.model.v3_0.TargetResourceType.TargetResourceTypeBuilder;
import com.sap.cloud.lm.sl.mta.schema.MapElement;

public class PlatformResourceTypeParser extends com.sap.cloud.lm.sl.mta.parsers.v2_0.PlatformResourceTypeParser {

    public PlatformResourceTypeParser(Map<String, Object> source) {
        super(PTF_RESOURCE_TYPE, source);
    }

    protected PlatformResourceTypeParser(MapElement schema, Map<String, Object> source) {
        super(schema, source);
    }

    @Override
    public TargetResourceType parse() throws ParsingException {
        TargetResourceTypeBuilder builder = new TargetResourceTypeBuilder();
        builder.setName(getName());
        builder.setParameters(getParameters());
        return builder.build();
    }

}
