package com.sap.cloud.lm.sl.mta.model.v3_1;

import static com.sap.cloud.lm.sl.common.util.CommonUtil.getOrDefault;

import java.util.Collections;

import com.sap.cloud.lm.sl.mta.model.Metadata;
import com.sap.cloud.lm.sl.mta.model.ParametersWithMetadataContainer;
import com.sap.cloud.lm.sl.mta.model.PropertiesWithMetadataContainer;
import com.sap.cloud.lm.sl.mta.parsers.v3_1.RequiredDependencyParser;
import com.sap.cloud.lm.sl.mta.util.MetadataConverter;
import com.sap.cloud.lm.sl.mta.util.YamlAdapter;
import com.sap.cloud.lm.sl.mta.util.YamlElement;

public class RequiredDependency extends com.sap.cloud.lm.sl.mta.model.v3_0.RequiredDependency
    implements ParametersWithMetadataContainer, PropertiesWithMetadataContainer {

    @YamlElement(RequiredDependencyParser.PROPERTIES_METADATA)
    @YamlAdapter(MetadataConverter.class)
    private Metadata propertiesMetadata;
    @YamlElement(RequiredDependencyParser.PARAMETERS_METADATA)
    @YamlAdapter(MetadataConverter.class)
    private Metadata parametersMetadata;

    protected RequiredDependency() {
    }

    @Override
    public void setPropertiesMetadata(Metadata propertiesMetadata) {
        this.propertiesMetadata = propertiesMetadata;
    }

    @Override
    public Metadata getPropertiesMetadata() {
        return propertiesMetadata;
    }

    @Override
    public void setParametersMetadata(Metadata parametersMetadata) {
        this.parametersMetadata = parametersMetadata;
    }

    @Override
    public Metadata getParametersMetadata() {
        return parametersMetadata;
    }

    @Override
    public RequiredDependency copyOf() {
        Builder result = new Builder();
        result.setName(getName());
        result.setGroup(getGroup());
        result.setList(getList());
        result.setProperties(getProperties());
        result.setParameters(getParameters());
        result.setPropertiesMetadata(getPropertiesMetadata());
        result.setParametersMetadata(getParametersMetadata());
        return result.build();
    }

    public static class Builder extends com.sap.cloud.lm.sl.mta.model.v3_0.RequiredDependency.Builder {

        private Metadata propertiesMetadata;
        private Metadata parametersMetadata;

        @Override
        public RequiredDependency build() {
            RequiredDependency result = new RequiredDependency();
            result.setName(name);
            result.setGroup(group);
            result.setList(list);
            result.setProperties(getOrDefault(properties, Collections.<String, Object> emptyMap()));
            result.setParameters(getOrDefault(parameters, Collections.<String, Object> emptyMap()));
            result.setPropertiesMetadata(propertiesMetadata);
            result.setParametersMetadata(parametersMetadata);
            return result;
        }

        public void setPropertiesMetadata(Metadata propertiesMetadata) {
            this.propertiesMetadata = propertiesMetadata;
        }

        public void setParametersMetadata(Metadata parametersMetadata) {
            this.parametersMetadata = parametersMetadata;
        }

    }

}
