package com.javateste.code.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.javateste.code.model.Entrega;

@Repository
public interface EntregaRepository extends JpaRepository <Entrega, Long> {

	public List <Entrega> findAllByNomeDestinatarioContainingIgnoreCase(@Param("nomeDestinatario") String nomeDestinatario);
	
	public List <Entrega> findByCepOrigem(String cepOrigem);
	
	public List <Entrega> findByCepDestino(String cepDestino);
}
