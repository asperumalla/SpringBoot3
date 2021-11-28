package com.interview.coins.config.service;

import com.interview.coins.service.CoinChangeService;
import com.interview.coins.service.impl.CoinChangeServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public CoinChangeService getCoinChangeService(){
        return new CoinChangeServiceImpl();
    }
}
