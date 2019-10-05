package com.sap.cloud.lm.sl.mta.handlers;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import com.sap.cloud.lm.sl.common.ContentException;
import com.sap.cloud.lm.sl.mta.message.Messages;
import com.sap.cloud.lm.sl.mta.model.DeploymentDescriptor;
import com.sap.cloud.lm.sl.mta.model.Descriptor;
import com.sap.cloud.lm.sl.mta.model.ExtensionDescriptor;
import com.sap.cloud.lm.sl.mta.model.Version;
import org.apache.commons.collections4.ListUtils;

public class SchemaVersionDetector {

    public Version detect(DeploymentDescriptor deploymentDescriptor, List<ExtensionDescriptor> extensionDescriptorChain)
        throws ContentException {
        validateSchemaVersionCompatibility(deploymentDescriptor, extensionDescriptorChain);
        return getMax(deploymentDescriptor, extensionDescriptorChain);
    }

    private void validateSchemaVersionCompatibility(DeploymentDescriptor deploymentDescriptor,
                                                    List<ExtensionDescriptor> extensionDescriptorChain) {
        List<ExtensionDescriptor> incompatibleExtensionDescriptors = getIncompatibleExtensionDescriptors(deploymentDescriptor,
                                                                                                         extensionDescriptorChain);
        if (!incompatibleExtensionDescriptors.isEmpty()) {
            throw new ContentException(Messages.EXTENSION_DESCRIPTORS_MUST_HAVE_THE_SAME_MAJOR_SCHEMA_VERSION_AS_THE_DEPLOYMENT_DESCRIPTOR_BUT_0_DO_NOT,
                                       String.join(",", Descriptor.getIds(incompatibleExtensionDescriptors)));
        }
    }

    private List<ExtensionDescriptor> getIncompatibleExtensionDescriptors(DeploymentDescriptor deploymentDescriptor,
                                                                          List<ExtensionDescriptor> extensionDescriptors) {
        return ListUtils.select(extensionDescriptors, extensionDescriptor -> !areCompatible(deploymentDescriptor, extensionDescriptor));
    }

    private boolean areCompatible(DeploymentDescriptor deploymentDescriptor, ExtensionDescriptor extensionDescriptor) {
        return areCompatible(deploymentDescriptor.getSchemaVersion(), extensionDescriptor.getSchemaVersion());
    }

    private boolean areCompatible(String version1, String version2) {
        Version parsedVersion1 = Version.parseVersion(version1);
        Version parsedVersion2 = Version.parseVersion(version2);
        return parsedVersion1.getMajor() == parsedVersion2.getMajor();
    }

    private Version getMax(DeploymentDescriptor deploymentDescriptor, List<ExtensionDescriptor> extensionDescriptors) {
        SortedSet<Version> allVersions = getVersions(deploymentDescriptor, extensionDescriptors);
        return allVersions.last();
    }

    private SortedSet<Version> getVersions(DeploymentDescriptor deploymentDescriptor, List<ExtensionDescriptor> extensionDescriptors) {
        SortedSet<Version> version = new TreeSet<>();
        version.add(Version.parseVersion(deploymentDescriptor.getSchemaVersion()));
        for (ExtensionDescriptor extensionDescriptor : extensionDescriptors) {
            version.add(Version.parseVersion(extensionDescriptor.getSchemaVersion()));
        }
        return version;
    }

}
