package com.interview.coins.dao;

import java.util.List;

public class CoinsChangeInfo {

    private List<Coins> coins;
    private int totalCoins;

    public CoinsChangeInfo(List<Coins> coins, int totalCoins) {
        this.coins = coins;
        this.totalCoins = totalCoins;
    }

    public List<Coins> getCoins() {
        return coins;
    }

    public int getTotalCoins() {
        return totalCoins;
    }

    public void setCoins(List<Coins> coins) {
        this.coins = coins;
    }

    public void setTotalCoins(int totalCoins) {
        this.totalCoins = totalCoins;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CoinsChangeInfo{");
        sb.append("coins=").append(coins);
        sb.append(", totalCoins=").append(totalCoins);
        sb.append('}');
        return sb.toString();
    }
}
