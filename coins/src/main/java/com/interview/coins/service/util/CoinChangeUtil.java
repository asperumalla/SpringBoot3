package com.interview.coins.service.util;

import com.interview.coins.dao.Coins;
import com.interview.coins.dao.CoinsChangeInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class CoinChangeUtil {


    static Logger logger = LoggerFactory.getLogger(CoinChangeUtil.class);
    public static CoinsChangeInfo getChangeByBill(int bill, List<Coins> coins) {

        return coinsChangeRequestByBill(bill,coins);

    }

/*    public static void main(String[] args) {

        List<Coins> coins = Arrays.asList( new Coins("0.01",100),
        new Coins("0.50",100),
        new Coins("0.20",5));
        coinsChangeRequestByBill(7,coins);
        String s = String.format("%.0f",(100*0.5));
        String ss = String.format("%.2f",(50f/100));
        System.out.print(ss);
    } */

    private static CoinsChangeInfo coinsChangeRequestByBill(int bill, List<Coins> coins) {

        /* Calculate all the number of coins required for each bill amount */
        bill*=100;

        /*Construct a map to hold coin denominations and its count*/
        Map<String,Integer> coinsCount = coins.stream().collect(Collectors.toMap( k -> getIntKey(k.getDenomination()), v-> v.getCoinsCount() ));

        List< CoinsChangeInfo> table = new ArrayList<>();
        CoinsChangeInfo changeForZero = new CoinsChangeInfo(Collections.emptyList(), 0);
        table.add(0,changeForZero);

        /*Calculate coins required for each of the amount for all the previous/lower amount until ZERO */
        for(int i =1 ; i<= bill; i++){
                table.add(i, minimumCoinsFromPreviousBill(table,i,coinsCount) );
        }

        CoinsChangeInfo coinsChangeInfo = table.get(bill)!=null? convertToActualDenominations(table.get(bill)): null; bill/=100;
        logger.info("Coins Denomination as follows for bill:{} [{}]",bill,coinsChangeInfo == null ? "No Denominations Available":coinsChangeInfo);
        return  coinsChangeInfo ;
    }

    private static CoinsChangeInfo convertToActualDenominations(CoinsChangeInfo coinsChangeInfo) {
        List<Coins> coins =  coinsChangeInfo.getCoins().stream().map( c -> getFloatKey(c)).collect(Collectors.toList());
        coinsChangeInfo.setCoins(coins);
        return coinsChangeInfo;
    }

    private static Coins getFloatKey(Coins coin) {
       String denomination =  String.format("%.2f",(Float.valueOf(coin.getDenomination())/100f));
       coin.setDenomination(denomination);
       return coin;
    }

    private static String getIntKey(String denomination) {
        String returnInt = String.format("%.0f",(100*Float.valueOf(denomination)));
        return returnInt;
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
                 boolean checkCoinsAvailability =
                         obj.isPresent() ? obj.get().getCoinsCount() < coins.get(coin) : coins.get(coin) > 0;

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