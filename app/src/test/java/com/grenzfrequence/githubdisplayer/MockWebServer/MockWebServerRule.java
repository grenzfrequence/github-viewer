package com.grenzfrequence.githubdisplayer.MockWebServer;

import com.grenzfrequence.githubdisplayer.utils.UrlReference;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.lang.reflect.Method;

import okhttp3.mockwebserver.MockWebServer;

/**
 * Created by grenzfrequence on 14/02/17.
 */

public class MockWebServerRule implements TestRule {

    Object       testClassInstance;
    UrlReference urlReference;

    public MockWebServerRule(Object testClassInstance, UrlReference urlReference) {
        this.testClassInstance = testClassInstance;
        this.urlReference = urlReference;
    }

    @Override
    public Statement apply(final Statement base, final Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                UseMockWebServer useMockWebServer =
                        description.getAnnotation(UseMockWebServer.class);
                if (useMockWebServer == null) {
                    base.evaluate();
                    return;
                }
                String setupMethodName = useMockWebServer.setupMethodName();
                if (setupMethodName.isEmpty()) {
                    base.evaluate();
                    return;
                }
                Method setupMethod = testClassInstance
                        .getClass()
                        .getDeclaredMethod(setupMethodName, MockWebServer.class);
                MockWebServer mockWebServer = new MockWebServer();
                mockWebServer.start();
                urlReference.setUrl(mockWebServer.url("/"));
                try {
                    setupMethod.invoke(testClassInstance, mockWebServer);
                    base.evaluate();
                } finally {
                    mockWebServer.shutdown();
                }
            }
        };
    }
}
