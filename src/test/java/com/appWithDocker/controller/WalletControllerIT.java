package com.forItrum.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.forItrum.config.SpringConfigForTests;
import com.forItrum.dto.WalletDTO;
import com.forItrum.model.Wallet;
import com.forItrum.repository.WalletRepository;
import com.forItrum.type.OperationType;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static com.forItrum.config.SpringConfigForTests.TEST_PROFILE;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = SpringConfigForTests.class)
@ActiveProfiles(TEST_PROFILE)
@AutoConfigureMockMvc
public class WalletControllerIT {
    @Test
    void contextLoads() {
    }
//    public static final String TEST_UUID_STRING = "8d1208fc-f401-496c-9cb8-483fef121234";
//    public static final Integer BASE_TEST_AMOUNT_VALUE = 1000;
//    public static final Integer CONCURRENCY_TEST_AMOUNT_VALUE = 900;
//    private static final ObjectMapper MAPPER = new ObjectMapper().findAndRegisterModules();
//    private final WalletDTO testWalletDepositDTO = new WalletDTO(
//            UUID.fromString(TEST_UUID_STRING),
//            OperationType.DEPOSIT,
//            CONCURRENCY_TEST_AMOUNT_VALUE
//    );
//    private final WalletDTO testWalletWithdrawDTO = new WalletDTO(
//            UUID.fromString(TEST_UUID_STRING),
//            OperationType.WITHDRAW,
//            CONCURRENCY_TEST_AMOUNT_VALUE
//    );
//    private final WalletDTO testDTO = new WalletDTO(
//            UUID.fromString(TEST_UUID_STRING),
//            OperationType.DEPOSIT,
//            1500
//    );
//    @Autowired
//    private MockMvc mockMvc;
//    @Autowired
//    private WalletRepository walletRepository;
//
////    @BeforeEach
////    public void prepareRepository() {
////        walletRepository.save(new Wallet(UUID.fromString(TEST_UUID_STRING), BASE_TEST_AMOUNT_VALUE));
////    }
//
////    @AfterEach
////    public void clearRepository() {
////        walletRepository.deleteAll();
////    }
//
//    @Test
//    public void getWalletIT() throws Exception {
//        final var response = mockMvc.perform(
//                        get("/api/v1/wallets/{WALLET_UUID}", UUID.fromString(TEST_UUID_STRING))
//                )
//                .andExpect(status().isOk())
//                .andReturn()
//                .getResponse();
//        final Integer amountFromResponse = MAPPER.readValue(response.getContentAsString(), new TypeReference<>() {
//        });
//
//        assertEquals(BASE_TEST_AMOUNT_VALUE, amountFromResponse);
//    }
//
//    @Test
//    public void depositOrWithdrawWalletAmountIT() {
//        class threadRunnable implements Runnable  {
//            final WalletDTO walletDTO;
//
//            public threadRunnable(WalletDTO walletDTO) {
//                this.walletDTO = walletDTO;
//            }
//
//            @Override
//            public void run() {
//                for (int i = 0; i < 500; i++) {
//                    try {
//                        mockMvc.perform(
//                                post("/api/v1/wallet")
//                                        .content(MAPPER.writeValueAsString(walletDTO))
//                                        .contentType(APPLICATION_JSON)
//                        );
//                    } catch (Exception e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//            }
//        }
////        Thread depositThread = new Thread(new threadRunnable(testWalletDepositDTO));
//        Thread depositThread = new Thread(new threadRunnable(testDTO));
//        Thread withdrawThread = new Thread(new threadRunnable(testWalletWithdrawDTO));
//
//        depositThread.start();
//        withdrawThread.start();
//
//        assertEquals(BASE_TEST_AMOUNT_VALUE,
//                walletRepository.findById(UUID.fromString(TEST_UUID_STRING)).orElseThrow().getAmount());
//    }
}
