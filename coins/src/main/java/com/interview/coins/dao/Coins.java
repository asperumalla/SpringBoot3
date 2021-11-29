package com.interview.coins.dao;

import javax.persistence.*;

@Entity( name = "COINS")
public class Coins {

    public Coins(String type, int coinsCount) {
        this.denomination = type;
        this.coinsCount = coinsCount;
    }

    public Coins(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "Denomination", unique = true)
    private String denomination;

    @Column(name = "COINS_COUNT")
    private int coinsCount;

    public String getDenomination() {
        return denomination;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public int getCoinsCount() {
        return coinsCount;
    }

    public void setCoinsCount(int coinsCount) {
        this.coinsCount = coinsCount;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Coins{");
        sb.append("id=").append(id);
        sb.append(", denomination='").append(denomination).append('\'');
        sb.append(", coinsCount=").append(coinsCount);
        sb.append('}');
        return sb.toString();
    }
}
