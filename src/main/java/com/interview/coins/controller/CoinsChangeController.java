package com.interview.coins.controller;

import com.interview.coins.dao.Coins;
import com.interview.coins.dao.CoinsChangeInfo;
import com.interview.coins.exception.ApplicationException;
import com.interview.coins.service.CoinChangeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "coins")
public class CoinsChangeController {

    Logger logger = LoggerFactory.getLogger(CoinsChangeController.class);


    private CoinChangeService service;

    @Autowired
    public CoinsChangeController(CoinChangeService service) {
        this.service = service;
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<Coins>> listChange(){

      List<Coins> coins = service.getAllCoinsCount();

      Optional.ofNullable( coins ).filter( c -> !c.isEmpty())
              .orElseThrow( () -> new ApplicationException("There are NO coin(s) denominations available",HttpStatus.OK));

      logger.info( "Size of Coins denomination : {}",coins.size());
      return new ResponseEntity<List<Coins>>(coins, HttpStatus.OK);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<List<Coins>> createCoinType(@RequestBody List<Coins> coins){
        List<Coins> savedData = service.addCoins(coins);
        return new ResponseEntity<List<Coins>>(savedData, HttpStatus.CREATED);
    }

    @PutMapping(path = "/change/{bill}")
    public ResponseEntity<CoinsChangeInfo> returnUpdatedCoins( @PathVariable("bill") int bill){
        CoinsChangeInfo changeCoins = service.getChangeByBill( bill);
        return new ResponseEntity<CoinsChangeInfo>(changeCoins, HttpStatus.OK);
    }

}
