package com.forItrum.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import static com.forItrum.config.SpringConfigForTests.TEST_PROFILE;

@Configuration
@Profile(TEST_PROFILE)
@ComponentScan(basePackages = "com.appWithDocker")
@PropertySource(value = "classpath:/application-test.properties")
public class SpringConfigForTests {
    public static final String TEST_PROFILE = "test";
}