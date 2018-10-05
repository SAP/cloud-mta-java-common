package com.sap.cloud.lm.sl.mta.mergers.v1;

import com.sap.cloud.lm.sl.common.util.MapUtil;
import com.sap.cloud.lm.sl.mta.handlers.v1.DescriptorHandler;
import com.sap.cloud.lm.sl.mta.model.ElementContext;
import com.sap.cloud.lm.sl.mta.model.Visitor;
import com.sap.cloud.lm.sl.mta.model.v1.DeploymentDescriptor;
import com.sap.cloud.lm.sl.mta.model.v1.Module;
import com.sap.cloud.lm.sl.mta.model.v1.Resource;
import com.sap.cloud.lm.sl.mta.model.v1.Target;
import com.sap.cloud.lm.sl.mta.model.v1.TargetModuleType;
import com.sap.cloud.lm.sl.mta.model.v1.TargetResourceType;

public class TargetMerger extends Visitor {

    protected final Target target;
    protected final DescriptorHandler handler;

    public TargetMerger(Target target, DescriptorHandler handler) {
        this.target = target;
        this.handler = handler;
    }

    public void mergeInto(DeploymentDescriptor descriptor) {
        descriptor.accept(this);
    }

    @Override
    public void visit(ElementContext context, Module module) {
        TargetModuleType moduleType = handler.findTargetModuleType(target, module.getType());
        if (moduleType == null) {
            return;
        }
        module.setProperties(MapUtil.merge(moduleType.getProperties(), module.getProperties()));
    }

    @Override
    public void visit(ElementContext context, DeploymentDescriptor descriptor) {
        descriptor.setProperties(MapUtil.merge(target.getProperties(), descriptor.getProperties()));
    }

    @Override
    public void visit(ElementContext context, Resource resource) {
        if (resource.getType() == null) {
            return;
        }
        TargetResourceType resourceType = handler.findTargetResourceType(target, resource.getType());
        if (resourceType == null) {
            return;
        }
        resource.setProperties(MapUtil.merge(resourceType.getProperties(), resource.getProperties()));
    }

}
