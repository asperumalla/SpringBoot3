package com.interview.coins.service.util;

import com.interview.coins.dao.Coins;
import com.interview.coins.dao.CoinsChangeInfo;

import java.util.*;
import java.util.stream.Collectors;

public class CoinChangeUtil {


    public static CoinsChangeInfo getChangeByBill(int bill, List<Coins> coins) {

        return coinsChangeRequestByBill(bill,coins);

    }

    public static void main(String[] args) {

        List<Coins> coins = Arrays.asList( new Coins("1",1),
        new Coins("6",2),
        new Coins("3",5));
        coinsChangeRequestByBill(7,coins);
    }

    private static CoinsChangeInfo coinsChangeRequestByBill(int bill, List<Coins> coins) {

        /*
        Calculate all the number of coins required for each bill amount
         */

        Map<String,Integer> coinsCount = coins.stream().collect(Collectors.toMap( k -> k.getDenomination(), v-> v.getCoinsCount() ));

        List< CoinsChangeInfo> table = new ArrayList<>();
        CoinsChangeInfo changeForZero = new CoinsChangeInfo(Collections.emptyList(), 0);
        table.add(0,changeForZero);

        for(int i =1 ; i<= bill; i++){
                table.add(i, minimumCoinsFromPreviousBill(table,i,coinsCount) );
        }
        return  table.get(bill) ;
    }

    private static CoinsChangeInfo minimumCoinsFromPreviousBill(List<CoinsChangeInfo> table, int i, Map<String,Integer> coins) {
        int min = Integer.MAX_VALUE;
        CoinsChangeInfo returnObj = new CoinsChangeInfo(Collections.emptyList(), min);
        int denomination = 0;

         for(String coin: coins.keySet()){
            int coinDenomination = Integer.valueOf(coin);

             if( (i - coinDenomination) > -1  &&
                     null != table.get(i - coinDenomination)
             ){


                 Optional<Coins> obj = table.get(i - coinDenomination).getCoins().stream().filter(c -> c.getDenomination().equals(coin)).findFirst();
                 boolean checkCoinsAvailability = obj.isPresent() ? obj.get().getCoinsCount() < coins.get(coin) : true;

                 if(min > table.get(i - coinDenomination).getTotalCoins()
                    && checkCoinsAvailability
                 ){
                     min = table.get(i - coinDenomination).getTotalCoins();
                     denomination = coinDenomination;
                     returnObj.setTotalCoins(min);
                     returnObj.setCoins(  new ArrayList<>(table.get(i - coinDenomination).getCoins() ) );
                 }
             }
         }

        if(min !=  Integer.MAX_VALUE){
            String strDenomination = String.valueOf(denomination);
            Optional<Coins> coinTbeupdated = returnObj.getCoins().stream().filter(coin -> coin.getDenomination().equals(strDenomination)).findFirst();

            if(coinTbeupdated.isPresent()){
                returnObj.getCoins().remove(coinTbeupdated.get());
                Coins coin = new Coins( coinTbeupdated.get().getDenomination(), coinTbeupdated.get().getCoinsCount()+1);
                returnObj.getCoins().add(coin);
                returnObj.setTotalCoins(  returnObj.getTotalCoins()+1 );
            }else{
                returnObj.setTotalCoins( min + 1);
                List<Coins> denominationCoins = returnObj.getCoins().isEmpty()? new ArrayList<>(): returnObj.getCoins();
                denominationCoins.add( new Coins(strDenomination,1) );
                returnObj.setCoins( denominationCoins );
            }

            return returnObj;
        }else return null;
    }

}
