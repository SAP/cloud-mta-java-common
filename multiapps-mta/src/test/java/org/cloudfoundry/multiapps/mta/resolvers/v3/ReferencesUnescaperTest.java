package org.cloudfoundry.multiapps.mta.resolvers.v3;

import java.util.stream.Stream;

import org.cloudfoundry.multiapps.common.util.Tester.Expectation;
import org.junit.jupiter.params.provider.Arguments;

public class ReferencesUnescaperTest extends org.cloudfoundry.multiapps.mta.resolvers.v2.ReferencesUnescaperTest {

    public static Stream<Arguments> testUnescaping() {
        return Stream.of(
// @formatter:off
            Arguments.of("mtad-with-escaped-placeholders.yaml", new Expectation(Expectation.Type.JSON, "result-from-unescaping-placeholders.json")),
            Arguments.of("mtad-with-escaped-references.yaml", new Expectation(Expectation.Type.JSON, "result-from-unescaping-references.json"))
// @formatter:on
        );
    }

}
