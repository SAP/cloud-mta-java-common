package com.sap.cloud.lm.sl.mta.model.v3_0;

import static com.sap.cloud.lm.sl.common.util.CommonUtil.getOrDefault;

import java.util.Collections;

public class PlatformResourceType extends com.sap.cloud.lm.sl.mta.model.v2_0.PlatformResourceType {

    protected PlatformResourceType() {

    }

    public static class Builder extends com.sap.cloud.lm.sl.mta.model.v2_0.PlatformResourceType.Builder {

        @Override
        public PlatformResourceType build() {
            PlatformResourceType result = new PlatformResourceType();
            result.setName(name);
            result.setResourceManager(resourceManager);
            result.setParameters(getOrDefault(parameters, Collections.<String, Object> emptyMap()));
            return result;
        }

    }

}
