package com.webWallet.config;

import org.springframework.test.context.ActiveProfiles;

import org.testcontainers.containers.PostgreSQLContainer;

import static com.webWallet.config.SpringConfigForTests.TEST_PROFILE;

@ActiveProfiles(TEST_PROFILE)
public class WalletPostgresqlContainer extends PostgreSQLContainer<WalletPostgresqlContainer> {
    private static final String IMAGE_VERSION = "postgres:16.2";
    private static WalletPostgresqlContainer container;

    private WalletPostgresqlContainer() {
        super(IMAGE_VERSION);
    }

    public static WalletPostgresqlContainer getInstance() {
        if (container == null) {
            container = new WalletPostgresqlContainer();
        }
        return container;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("DB_URL", container.getJdbcUrl());
        System.setProperty("DB_USERNAME", container.getUsername());
        System.setProperty("DB_PASSWORD", container.getPassword());
    }

    @Override
    public void stop() {
    }
}
