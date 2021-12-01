package com.interview.coins.repository;

import com.interview.coins.dao.Coins;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface CoinChangeRepository extends CrudRepository<Coins, Serializable> {
    List<Coins> findAllByDenominationIn(List<String> denominations);
}
