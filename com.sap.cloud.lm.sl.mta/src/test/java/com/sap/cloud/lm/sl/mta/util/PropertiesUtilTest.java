package com.sap.cloud.lm.sl.mta.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.sap.cloud.lm.sl.common.util.JsonUtil;
import com.sap.cloud.lm.sl.common.util.TestUtil;
import com.sap.cloud.lm.sl.common.util.Tester;
import com.sap.cloud.lm.sl.common.util.Tester.Expectation;

public class PropertiesUtilTest {

    private final Tester tester = Tester.forClass(getClass());

    public static Stream<Arguments> mergeTest() {
        return Stream.of(
// @formatter:off
            // (0) Merge normal properties maps
            Arguments.of("deployment-properties-01.json", "extension-properties-01.json", new Expectation(Expectation.Type.JSON, "merged-properties-01.json")),
            // (1) No changes in the extension => merged properties should be the same
            Arguments.of("deployment-properties-02.json", "extension-properties-02.json", new Expectation(Expectation.Type.JSON, "merged-properties-02.json")),
            // (2) Merging of nested maps
            Arguments.of("deployment-properties-03.json", "extension-properties-03.json", new Expectation(Expectation.Type.JSON, "merged-properties-03.json")),
            // (3) Scalar parameter cannot be overwritten by a structured parameter
            Arguments.of("deployment-properties-04.json", "extension-properties-04.json", new Expectation(Expectation.Type.EXCEPTION, "")),
            // (4) Structured parameter cannot be overwritten by a scalar parameter
            Arguments.of("deployment-properties-05.json", "extension-properties-05.json", new Expectation(Expectation.Type.EXCEPTION, ""))
// @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource
    public void mergeTest(String deploymentDescriptorPath, String extensionDescriptorPath, Expectation expectation) {
        Map<String, Object> deploymentDescriptorProperties = JsonUtil
            .fromJson(TestUtil.getResourceAsString(deploymentDescriptorPath, getClass()), Map.class);
        Map<String, Object> extensionDescriptorProperties = JsonUtil
            .fromJson(TestUtil.getResourceAsString(extensionDescriptorPath, getClass()), Map.class);

        tester.test(() -> PropertiesUtil.mergeExtensionProperties(deploymentDescriptorProperties, extensionDescriptorProperties),
            expectation);
    }

    public static Stream<Arguments> testGetPluralOrSingular() {
        return Stream.of(
// @formatter:off
            Arguments.of("host-1", null, new Expectation(Expectation.Type.STRING, Arrays.asList("host-1").toString())),
            Arguments.of(null, Arrays.asList("host-1", "host-2"), new Expectation(Expectation.Type.STRING, Arrays.asList("host-1", "host-2").toString())),
            Arguments.of("host-1", Arrays.asList("host-2", "host-3"), new Expectation(Expectation.Type.STRING, Arrays.asList("host-2", "host-3").toString()))
// @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource
    public void testGetPluralOrSingular(String singular, List<String> plural, Expectation expectation) {
        String singularKey = "host";
        String pluralKey = "hosts";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(singularKey, singular);
        parameters.put(pluralKey, plural);
        List<Map<String, Object>> parametersList = Arrays.asList(parameters);

        tester.test(() -> PropertiesUtil.getPluralOrSingular(parametersList, pluralKey, singularKey), expectation);

    }
}