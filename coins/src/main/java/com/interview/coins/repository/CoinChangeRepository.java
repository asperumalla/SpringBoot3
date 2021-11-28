package com.interview.coins.repository;

import com.interview.coins.dao.Coins;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface CoinChangeRepository extends CrudRepository<Coins, Serializable> {
}
