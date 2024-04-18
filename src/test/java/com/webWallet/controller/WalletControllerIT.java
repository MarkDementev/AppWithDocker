package com.webWallet.controller;

import com.webWallet.config.SpringConfigForTests;
import com.webWallet.dto.WalletDTO;
import com.webWallet.model.Wallet;
import com.webWallet.repository.WalletRepository;
import com.webWallet.type.OperationType;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static com.webWallet.config.SpringConfigForTests.TEST_PROFILE;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles(TEST_PROFILE)
@SpringBootTest(classes = SpringConfigForTests.class)
@AutoConfigureMockMvc
public class WalletControllerIT {
    public static final String TEST_UUID_STRING = "8d1208fc-f401-496c-9cb8-483fef121234";
    public static final Integer BASE_TEST_AMOUNT_VALUE = 1000;
    public static final Integer TEST_AMOUNT_DIFF_VALUE = 500;
    private static final ObjectMapper MAPPER = new ObjectMapper().findAndRegisterModules();
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WalletRepository walletRepository;
    private final WalletDTO testWalletDepositDTO = new WalletDTO(
            UUID.fromString(TEST_UUID_STRING),
            OperationType.DEPOSIT,
            TEST_AMOUNT_DIFF_VALUE
    );
    private final WalletDTO testWalletWithdrawDTO = new WalletDTO(
            UUID.fromString(TEST_UUID_STRING),
            OperationType.WITHDRAW,
            TEST_AMOUNT_DIFF_VALUE
    );

    @AfterEach
    public void returnWalletDefaultAmount() {
        Wallet wallet = walletRepository.findById(UUID.fromString(TEST_UUID_STRING)).orElseThrow();
        wallet.setAmount(BASE_TEST_AMOUNT_VALUE);
        walletRepository.save(wallet);
    }

    @Test
    public void getWalletIT() throws Exception {
        final var response = mockMvc.perform(
                        get("/api/v1/wallets/{WALLET_UUID}", UUID.fromString(TEST_UUID_STRING))
                )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();
        final Integer amountFromResponse = MAPPER.readValue(response.getContentAsString(), new TypeReference<>() {
        });

        assertEquals(BASE_TEST_AMOUNT_VALUE, amountFromResponse);
    }

    @Test
    public void depositOrWithdrawWalletAmountIT() throws Exception {
        PostRequestsThread depositThread = new PostRequestsThread(testWalletDepositDTO);
        PostRequestsThread withdrawThread = new PostRequestsThread(testWalletWithdrawDTO);

        depositThread.start();
        depositThread.join();
        withdrawThread.start();
        withdrawThread.join();

        assertEquals(BASE_TEST_AMOUNT_VALUE,
                walletRepository.findById(UUID.fromString(TEST_UUID_STRING)).orElseThrow().getAmount());
    }

    class PostRequestsThread extends Thread {
        final WalletDTO walletDTO;

        public PostRequestsThread(WalletDTO walletDTO) {
            this.walletDTO = walletDTO;
        }

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                try {
                    mockMvc.perform(
                            post("/api/v1/wallet")
                                    .content(MAPPER.writeValueAsString(walletDTO))
                                    .contentType(APPLICATION_JSON)
                    );
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
