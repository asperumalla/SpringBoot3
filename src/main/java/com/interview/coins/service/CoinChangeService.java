package com.interview.coins.service;

import com.interview.coins.dao.Coins;
import com.interview.coins.dao.CoinsChangeInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CoinChangeService {

    List<Coins> addCoins(List<Coins> coins);
    List<Coins> getAllCoinsCount();
    CoinsChangeInfo getChangeByBill(int bill);
}
