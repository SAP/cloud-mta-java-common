package com.sap.cloud.lm.sl.mta.model.v3_1;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;

import com.sap.cloud.lm.sl.common.util.ListUtil;
import com.sap.cloud.lm.sl.mta.parsers.v3_1.ExtensionDescriptorParser;
import com.sap.cloud.lm.sl.mta.util.YamlElement;
import com.sap.cloud.lm.sl.mta.util.YamlElementOrder;

@YamlElementOrder({ "id", "description", "parentId", "provider", "schemaVersion", "parameters", "modules3_1", "resources3_1" })
public class ExtensionDescriptor extends com.sap.cloud.lm.sl.mta.model.v3_0.ExtensionDescriptor {

    @YamlElement(ExtensionDescriptorParser.MODULES)
    private List<ExtensionModule> modules3_1;
    @YamlElement(ExtensionDescriptorParser.RESOURCES)
    private List<ExtensionResource> resources3_1;

    protected ExtensionDescriptor() {

    }

    public List<ExtensionModule> getModules3_1() {
        return ListUtil.upcastUnmodifiable(getModules());
    }

    @Override
    public List<? extends ExtensionModule> getModules() {
        return modules3_1;
    }

    public List<ExtensionResource> getResources3_1() {
        return ListUtil.upcastUnmodifiable(getResources());
    }

    @Override
    public List<? extends ExtensionResource> getResources() {
        return resources3_1;
    }

    public void setModules3_1(List<ExtensionModule> modules) {
        setModules(modules);
    }

    @Override
    protected void setModules(List<? extends com.sap.cloud.lm.sl.mta.model.v1_0.ExtensionModule> modules) {
        this.modules3_1 = ListUtil.cast(modules);
    }

    public void setResources3_1(List<ExtensionResource> resources) {
        setResources(resources);
    }

    @Override
    protected void setResources(List<? extends com.sap.cloud.lm.sl.mta.model.v1_0.ExtensionResource> resources) {
        this.resources3_1 = ListUtil.cast(resources);
    }

    public static class Builder extends com.sap.cloud.lm.sl.mta.model.v3_0.ExtensionDescriptor.Builder {

        protected List<ExtensionModule> modules3_1;
        protected List<ExtensionResource> resources3_1;

        @Override
        public ExtensionDescriptor build() {
            ExtensionDescriptor result = new ExtensionDescriptor();
            result.setId(id);
            result.setDescription(description);
            result.setParentId(parentId);
            result.setProvider(provider);
            result.setSchemaVersion(schemaVersion);
            result.setResources3_1(ObjectUtils.defaultIfNull(resources3_1, Collections.<ExtensionResource> emptyList()));
            result.setDeployTargets(ObjectUtils.defaultIfNull(deployTargets, Collections.<String> emptyList()));
            result.setParameters(ObjectUtils.defaultIfNull(parameters, Collections.<String, Object> emptyMap()));
            result.setModules3_1(ObjectUtils.defaultIfNull(modules3_1, Collections.<ExtensionModule> emptyList()));
            return result;
        }

        public void setModules3_1(List<ExtensionModule> modules) {
            setModules(modules);
        }

        @Override
        protected void setModules(List<? extends com.sap.cloud.lm.sl.mta.model.v1_0.ExtensionModule> modules) {
            this.modules3_1 = ListUtil.cast(modules);
        }

        public void setResources3_1(List<ExtensionResource> resources) {
            setResources(resources);
        }

        @Override
        protected void setResources(List<? extends com.sap.cloud.lm.sl.mta.model.v1_0.ExtensionResource> resources) {
            this.resources3_1 = ListUtil.cast(resources);
        }

    }

}
