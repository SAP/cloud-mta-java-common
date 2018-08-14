package com.sap.cloud.lm.sl.mta.model.v2_0;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;

import com.sap.cloud.lm.sl.common.util.ListUtil;
import com.sap.cloud.lm.sl.common.util.MapUtil;
import com.sap.cloud.lm.sl.mta.model.ElementContext;
import com.sap.cloud.lm.sl.mta.model.ParametersContainer;
import com.sap.cloud.lm.sl.mta.model.Visitor;
import com.sap.cloud.lm.sl.mta.parsers.v2_0.ExtensionModuleParser;
import com.sap.cloud.lm.sl.mta.util.YamlElement;

public class ExtensionModule extends com.sap.cloud.lm.sl.mta.model.v1_0.ExtensionModule implements ParametersContainer {

    @YamlElement(ExtensionModuleParser.PARAMETERS)
    private Map<String, Object> parameters;
    @YamlElement(ExtensionModuleParser.REQUIRES)
    private List<ExtensionRequiredDependency> requiredDependencies2_0;
    @YamlElement(ExtensionModuleParser.PROVIDES)
    private List<ExtensionProvidedDependency> providedDependencies2_0;

    protected ExtensionModule() {

    }

    @Override
    public Map<String, Object> getParameters() {
        return MapUtil.unmodifiable(parameters);
    }

    public List<ExtensionRequiredDependency> getRequiredDependencies2_0() {
        return ListUtil.upcastUnmodifiable(getRequiredDependencies());
    }

    protected List<? extends ExtensionRequiredDependency> getRequiredDependencies() {
        return requiredDependencies2_0;
    }

    public List<ExtensionProvidedDependency> getProvidedDependencies2_0() {
        return ListUtil.upcastUnmodifiable(getProvidedDependencies());
    }

    @Override
    protected List<? extends ExtensionProvidedDependency> getProvidedDependencies() {
        return providedDependencies2_0;
    }

    @Override
    public void setParameters(Map<String, Object> parameters) {
        this.parameters = new LinkedHashMap<>(parameters);
    }

    public void setRequiredDependencies2_0(List<ExtensionRequiredDependency> requiredDependencies) {
        setRequiredDependencies(requiredDependencies);
    }

    protected void setRequiredDependencies(
        List<? extends com.sap.cloud.lm.sl.mta.model.v2_0.ExtensionRequiredDependency> requiredDependencies) {
        this.requiredDependencies2_0 = ListUtil.cast(requiredDependencies);
    }

    public void setProvidedDependencies2_0(List<ExtensionProvidedDependency> providedDependencies) {
        setProvidedDependencies(providedDependencies);
    }

    @Override
    protected void setProvidedDependencies(
        List<? extends com.sap.cloud.lm.sl.mta.model.v1_0.ExtensionProvidedDependency> providedDependencies) {
        this.providedDependencies2_0 = ListUtil.cast(providedDependencies);
    }

    @Override
    public void accept(ElementContext context, Visitor visitor) {
        super.accept(context, visitor);
        for (ExtensionRequiredDependency requiredDependency : getRequiredDependencies()) {
            requiredDependency.accept(new ElementContext(requiredDependency, context), visitor);
        }
    }

    public static class Builder extends com.sap.cloud.lm.sl.mta.model.v1_0.ExtensionModule.Builder {

        protected Map<String, Object> parameters;
        protected List<ExtensionRequiredDependency> requiredDependencies2_0;
        protected List<ExtensionProvidedDependency> providedDependencies2_0;

        @Override
        public ExtensionModule build() {
            ExtensionModule result = new ExtensionModule();
            result.setName(name);
            result.setProvidedDependencies2_0(ObjectUtils.defaultIfNull(providedDependencies2_0, Collections.<ExtensionProvidedDependency> emptyList()));
            result.setRequiredDependencies2_0(ObjectUtils.defaultIfNull(requiredDependencies2_0, Collections.<ExtensionRequiredDependency> emptyList()));
            result.setProperties(ObjectUtils.defaultIfNull(properties, Collections.<String, Object> emptyMap()));
            result.setParameters(ObjectUtils.defaultIfNull(parameters, Collections.<String, Object> emptyMap()));
            return result;
        }

        public void setParameters(Map<String, Object> parameters) {
            this.parameters = parameters;
        }

        public void setProvidedDependencies2_0(List<ExtensionProvidedDependency> providedDependencies) {
            setProvidedDependencies(providedDependencies);
        }

        @Override
        protected void setProvidedDependencies(
            List<? extends com.sap.cloud.lm.sl.mta.model.v1_0.ExtensionProvidedDependency> providedDependencies) {
            this.providedDependencies2_0 = ListUtil.cast(providedDependencies);
        }

        public void setRequiredDependencies2_0(List<ExtensionRequiredDependency> requiredDependencies) {
            setRequiredDependencies(requiredDependencies);
        }

        protected void setRequiredDependencies(
            List<? extends com.sap.cloud.lm.sl.mta.model.v2_0.ExtensionRequiredDependency> requiredDependencies) {
            this.requiredDependencies2_0 = ListUtil.cast(requiredDependencies);
        }

    }

}
