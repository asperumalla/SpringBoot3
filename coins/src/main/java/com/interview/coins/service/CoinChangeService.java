package com.interview.coins.service;

import com.interview.coins.dao.Coins;
import com.interview.coins.dao.CoinsChangeInfo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

public interface CoinChangeService {

    List<Coins> addCoins(List<Coins> coins);
    List<Coins> getAllCoinsCount();
    CoinsChangeInfo getChangeByBill(int bill);
    List<Coins> updateDenomination(List<Coins> coins);
}
