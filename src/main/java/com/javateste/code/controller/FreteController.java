package com.javateste.code.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javateste.code.model.Frete;
import com.javateste.code.repository.FreteRepository;

@RestController
@RequestMapping("/frete")
@CrossOrigin(origins = "*", allowedHeaders ="*")
public class FreteController {

	@Autowired
	private FreteRepository freteRepository;
	
	@GetMapping
	public ResponseEntity<List<Frete>> getAll(){
		return ResponseEntity.ok(freteRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Frete> getById(@PathVariable Long id){
		return freteRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/lista_valor_frete/{p1}/{p2}/{p3}")
	public ResponseEntity<List<Frete>> getByListaPreco(@PathVariable BigDecimal p1, @PathVariable BigDecimal p2, @PathVariable BigDecimal p3){
		List<BigDecimal> listaPreco = List.of(p1, p2, p3);
		return ResponseEntity.ok(freteRepository.findByValorTotalFreteIn(listaPreco));
	}
	
	@PostMapping
	public ResponseEntity<Frete> postFrete(@Valid @RequestBody Frete frete){
		return ResponseEntity.status(HttpStatus.CREATED).body(freteRepository.save(frete));
	}
	
	@PutMapping
	public ResponseEntity<Frete> putFrete(@Valid @RequestBody Frete frete){
		return freteRepository.findById(frete.getId())
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteFrete(@PathVariable Long id){
		return freteRepository.findById(id)
				.map(resposta -> {
					freteRepository.deleteById(id);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				})
				.orElse(ResponseEntity.notFound().build());
	}

}
