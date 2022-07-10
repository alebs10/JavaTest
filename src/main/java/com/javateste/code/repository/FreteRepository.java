package com.javateste.code.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javateste.code.model.Frete;

@Repository
public interface FreteRepository extends JpaRepository <Frete, Long> {

	public List <Frete> findByValorTotalFreteIn(List<BigDecimal> vlTotalFrete);
}
