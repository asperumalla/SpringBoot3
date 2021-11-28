package com.interview.coins.service.impl;

import com.interview.coins.dao.Coins;
import com.interview.coins.dao.CoinsChangeInfo;
import com.interview.coins.exception.ApplicationException;
import com.interview.coins.repository.CoinChangeRepository;
import com.interview.coins.service.CoinChangeService;
import com.interview.coins.service.util.CoinChangeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CoinChangeServiceImpl implements CoinChangeService {

    @Autowired
    public CoinChangeRepository repository;


    @Override
    public List<Coins> addCoins(List<Coins> coins) {
        Iterable<Coins> saved = repository.saveAll(coins);
        return getList(saved);
    }

    @Override
    public List<Coins> getAllCoinsCount() {
        Iterable<Coins> allCoins = repository.findAll();
        return getList( allCoins);
    }

    @Override
    public CoinsChangeInfo getChangeByBill(int bill) {
        List<Coins> coins = getAllCoinsCount();
        CoinsChangeInfo info = CoinChangeUtil.getChangeByBill( bill, coins );

        Optional.ofNullable(info).orElseThrow(() -> new ApplicationException(
                String.format( "NOT enough denominations available for %s",bill), HttpStatus.BAD_REQUEST   ));

        List<Coins> toSaveList = coins.stream().filter(cd -> isTypePresent(cd.getDenomination(), info.getCoins())).map(c ->{
            c.setCoinsCount(c.getCoinsCount() - getCountByType(c.getDenomination(), info.getCoins()));
            return c;
        }).collect(Collectors.toList());

        repository.saveAll(toSaveList);

        if( toSaveList.isEmpty())
            Optional.ofNullable(toSaveList).filter( s -> !s.isEmpty() ).
                    orElseThrow(() -> new ApplicationException(
                            String.format( "Change NOT available for bill %s",bill), HttpStatus.BAD_REQUEST   ));

        return info;
    }

    private boolean isTypePresent(String type, List<Coins> coins) {
        return coins.stream().anyMatch( c -> c.getDenomination().equals( type));
    }


    private int getCountByType(String type, List<Coins> coins) {
        Optional<Coins> coin = coins.stream().filter(c -> c.getDenomination().equals(type)).findFirst();
        return coin.isPresent()? coin.get().getCoinsCount() : 0;

    }

    private List<Coins> getList(Iterable<Coins> all) {
        List<Coins> allCoins = new ArrayList<>();
        all.forEach(allCoins::add);
        return allCoins;
    }
}
