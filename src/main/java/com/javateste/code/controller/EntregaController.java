package com.javateste.code.controller;

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

import com.javateste.code.model.Entrega;
import com.javateste.code.repository.EntregaRepository;
import com.javateste.code.repository.FreteRepository;

@RestController
@RequestMapping("/entrega")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EntregaController {
 
	@Autowired
	private EntregaRepository entregaRepository;
	
	@Autowired
	private FreteRepository freteRepository;
	
	
	@GetMapping
	public ResponseEntity<List<Entrega>> getAll(){
		return ResponseEntity.ok(entregaRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Entrega> getById(@PathVariable Long id){
		return entregaRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/destinatario/{nomeDestinatario}")
	public ResponseEntity<List<Entrega>> getByNomeDestinatario(@PathVariable String nomeDestinatario){
		return ResponseEntity.ok(entregaRepository.findAllByNomeDestinatarioContainingIgnoreCase
				(nomeDestinatario));
	}
	
	@PostMapping 
	public ResponseEntity<Entrega> postEntrega(@Valid @RequestBody Entrega entrega){ 
		if (freteRepository.existsById(entrega.getFrete().getId()))
			return ResponseEntity.status(HttpStatus.CREATED).body(entregaRepository.save(entrega));

		return ResponseEntity.badRequest().build();
	}
	
	@PutMapping
	public ResponseEntity<Entrega> putProduto(@Valid @RequestBody Entrega entrega) {
					
		if (entregaRepository.existsById(entrega.getId())) {
		
			if (freteRepository.existsById(entrega.getFrete().getId())) 
				return ResponseEntity.ok(entregaRepository.save(entrega));
			else
				return ResponseEntity.badRequest().build();
					
		}
		
		return ResponseEntity.notFound().build();

	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteEntrega(@PathVariable Long id){
		return entregaRepository.findById(id)
				.map(resposta -> {
					entregaRepository.deleteById(id);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				})
				.orElse(ResponseEntity.notFound().build());
	}
}
