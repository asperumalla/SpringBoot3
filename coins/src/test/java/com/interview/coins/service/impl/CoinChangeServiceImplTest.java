package com.interview.coins.service.impl;

import com.interview.coins.dao.Coins;
import com.interview.coins.repository.CoinChangeRepository;
import com.interview.coins.service.CoinChangeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class CoinChangeServiceImplTest {

    @Mock
    private CoinChangeRepository repository;

    @InjectMocks
    private CoinChangeService service = new CoinChangeServiceImpl();

    @Test
    @DisplayName("test all coins counts")
    void testAllCoinsCounts() {
        List<Coins> list = Collections.emptyList();
        when(repository.findAll()).thenReturn(list);
        assertThat( service.getAllCoinsCount().isEmpty() ).isEqualTo(true);
    }

}