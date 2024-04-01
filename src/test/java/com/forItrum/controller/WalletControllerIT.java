package com.forItrum.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.forItrum.config.SpringConfigForTests;
import com.forItrum.model.Wallet;
import com.forItrum.repository.WalletRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static com.forItrum.config.SpringConfigForTests.TEST_PROFILE;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = SpringConfigForTests.class, webEnvironment = RANDOM_PORT)
@ActiveProfiles(TEST_PROFILE)
@AutoConfigureMockMvc
public class WalletControllerIT {
    public static final String TEST_UUID_STRING = "8d1208fc-f401-496c-9cb8-483fef121234";
    public static final Integer TEST_AMOUNT_VALUE = 1000;
    private static final ObjectMapper MAPPER = new ObjectMapper().findAndRegisterModules();
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WalletRepository walletRepository;

    @AfterEach
    public void clearRepository() {
        walletRepository.deleteAll();
    }

    @Test
    public void getWalletIT() throws Exception {
        walletRepository.save(new Wallet(UUID.fromString(TEST_UUID_STRING), TEST_AMOUNT_VALUE));

        final var response = mockMvc.perform(
                        get("/api/v1/wallets/{WALLET_UUID}", UUID.fromString(TEST_UUID_STRING))
                )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();
        final Integer amountFromResponse = MAPPER.readValue(response.getContentAsString(), new TypeReference<>() {
        });

        assertEquals(TEST_AMOUNT_VALUE, amountFromResponse);
    }
}
