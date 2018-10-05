package com.sap.cloud.lm.sl.mta.serialization.v2;

import java.util.Arrays;
import java.util.Map;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.sap.cloud.lm.sl.mta.parsers.v2.DeploymentDescriptorParser;
import com.sap.cloud.lm.sl.mta.parsers.v2.ExtensionDescriptorParser;

@RunWith(value = Parameterized.class)
public class DescriptorSerializationTest extends com.sap.cloud.lm.sl.mta.serialization.v1.DescriptorSerializationTest {

    @Parameters
    public static Iterable<Object[]> getParameters() {
        return Arrays.asList(new Object[][] {
// @formatter:off
            // (0) Valid deployment and extension descriptors:
            {
                "mtad-00.yaml", "R:serialized-descriptor-00.json", "extension-descriptor-00.mtaext", "R:serialized-extension-00.json"
            }
            // @formatter:on
        });
    }

    public DescriptorSerializationTest(String deploymentDescriptorLocation, String serializedDescriptorLocation,
        String extensionDescriptorLocation, String serializedExtensionLocation) {
        super(deploymentDescriptorLocation, serializedDescriptorLocation, extensionDescriptorLocation, serializedExtensionLocation);
    }

    @Override
    protected DeploymentDescriptorParser getDescriptorParser(Map<String, Object> yamlMap) {
        return new DeploymentDescriptorParser(yamlMap);
    }

    @Override
    protected ExtensionDescriptorParser getExtensionDescriptorParser(Map<String, Object> yamlMap) {
        return new ExtensionDescriptorParser(yamlMap);
    }

}
