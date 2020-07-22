package com.sap.cloud.lm.sl.mta.resolvers.v3;

import static com.sap.cloud.lm.sl.mta.util.ValidatorUtil.getPrefixedName;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.sap.cloud.lm.sl.common.ContentException;
import com.sap.cloud.lm.sl.mta.handlers.v3.DescriptorHandler;
import com.sap.cloud.lm.sl.mta.model.DeploymentDescriptor;
import com.sap.cloud.lm.sl.mta.model.RequiredDependency;
import com.sap.cloud.lm.sl.mta.model.Resource;
import com.sap.cloud.lm.sl.mta.resolvers.Resolver;
import com.sap.cloud.lm.sl.mta.resolvers.ResolverBuilder;
import com.sap.cloud.lm.sl.mta.resolvers.v2.RequiredDependencyReferenceResolver;

public class ResourceReferenceResolver implements Resolver<Resource> {

    protected final DeploymentDescriptor descriptor;
    protected final Resource resource;
    protected final String prefix;
    protected final ResolverBuilder resourcesPropertiesResolverBuilder;
    protected final ResolverBuilder requiredDependenciesPropertiesResolverBuilder;

    public ResourceReferenceResolver(DeploymentDescriptor descriptor, Resource resource, String prefix,
                                     ResolverBuilder resourcesPropertiesResolverBuilder,
                                     ResolverBuilder requiredDependenciesPropertiesResolverBuilder) {
        this.descriptor = descriptor;
        this.resource = resource;
        this.resourcesPropertiesResolverBuilder = resourcesPropertiesResolverBuilder;
        this.requiredDependenciesPropertiesResolverBuilder = requiredDependenciesPropertiesResolverBuilder;
        this.prefix = getPrefixedName(prefix, resource.getName());
    }

    @Override
    public Resource resolve() throws ContentException {
        resource.setProperties(getResolvedProperties());
        resource.setParameters(getResolvedParameters());
        resource.setRequiredDependencies(getResolvedDependencies());
        return resource;
    }

    private List<RequiredDependency> getResolvedDependencies() {
        return resource.getRequiredDependencies()
                       .stream()
                       .map(this::resolveRequiredDependency)
                       .collect(Collectors.toList());
    }

    protected RequiredDependency resolveRequiredDependency(RequiredDependency dependency) {
        RequiredDependencyReferenceResolver resolver = createRequiredDependencyResolver(dependency);
        return resolver.resolve();
    }

    protected RequiredDependencyReferenceResolver createRequiredDependencyResolver(RequiredDependency requiredDependency) {
        return new RequiredDependencyReferenceResolver(descriptor,
                                                       resource,
                                                       requiredDependency,
                                                       prefix,
                                                       new DescriptorHandler(),
                                                       requiredDependenciesPropertiesResolverBuilder);
    }

    private Map<String, Object> getResolvedProperties() {
        return createResourcePropertiesReferenceResolver(resource.getProperties()).resolve();
    }

    private Map<String, Object> getResolvedParameters() {
        return createResourcePropertiesReferenceResolver(resource.getParameters()).resolve();
    }

    protected ResourcePropertiesReferenceResolver createResourcePropertiesReferenceResolver(Map<String, Object> properties) {
        return new ResourcePropertiesReferenceResolver(descriptor, resource, properties, prefix, resourcesPropertiesResolverBuilder);
    }

}
