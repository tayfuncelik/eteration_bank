package com.eteration.simplebanking.dao;

import org.springframework.data.repository.CrudRepository;

import com.eteration.simplebanking.model.DepositTransaction;

public interface DepositTransactionRepository extends CrudRepository<DepositTransaction, Long> {


}
