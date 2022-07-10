package com.javateste.code.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_entrega")
public class Entrega {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	
	@NotBlank(message = "Nome do destinatário é obrigatório.")
	@Size(min = 3, max = 255, message = "No mínimo 5 caracteres e no máximo 255.")
	private String nomeDestinatario;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@NotBlank(message = "O campo peso precisa ser inserido.")
	@Positive(message = "O peso deve ser maior que zero.")
	private BigDecimal peso;
	
	@NotBlank(message = "O campo CEP de origem é obrigatório.")
	@Pattern(regexp = "\\d{5}-\\d{3}")
	private String cepOrigem;
	
	@NotBlank(message = "O campo CEP de destino é obrigatório.")
	@Pattern(regexp = "\\d{5}-\\d{3}")
	private String cepDestino;
	
	@ManyToOne
	@JsonIgnoreProperties("entrega")
	private Frete frete;
	

	public Frete getFrete() {
		return frete;
	}

	public void setFrete(Frete frete) {
		this.frete = frete;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeDestinatario() {
		return nomeDestinatario;
	}

	public void setNomeDestinatario(String nomeDestinatario) {
		this.nomeDestinatario = nomeDestinatario;
	}

	public BigDecimal getPeso() {
		return peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public String getCepOrigem() {
		return cepOrigem;
	}

	public void setCepOrigem(String cepOrigem) {
		this.cepOrigem = cepOrigem;
	}

	public String getCepDestino() {
		return cepDestino;
	}

	public void setCepDestino(String cepDestino) {
		this.cepDestino = cepDestino;
	}
	
}
