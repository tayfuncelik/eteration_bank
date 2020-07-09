package com.eteration.simplebanking.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eteration.simplebanking.model.WithdrawalTransaction;

public interface WithDrawalRepository extends JpaRepository<WithdrawalTransaction, Long> {

}
