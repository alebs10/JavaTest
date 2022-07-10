package com.javateste.code.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_frete")
public class Frete {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@Positive
	private BigDecimal vlTotalFrete;
	
	@FutureOrPresent
	private LocalDateTime dataPrevistaEntrega;
	
	@PastOrPresent
	private LocalDateTime dataConsulta;
	
	@NotBlank(message = "O campo do DDD precisa ser obrigatório")
	@Pattern(regexp = "\\d{2}")
	private String ddd;
	
	@OneToMany(mappedBy = "frete", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("frete")
	private List<Entrega> entrega;

	public List<Entrega> getEntrega() {
		return entrega;
	}

	public void setEntrega(List<Entrega> entrega) {
		this.entrega = entrega;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getVlTotalFrete() {
		return vlTotalFrete;
	}

	public void setVlTotalFrete(BigDecimal vlTotalFrete) {
		this.vlTotalFrete = vlTotalFrete;
	}

	public LocalDateTime getDataPrevistaEntrega() {
		return dataPrevistaEntrega;
	}

	public void setDataPrevistaEntrega(LocalDateTime dataPrevistaEntrega) {
		this.dataPrevistaEntrega = dataPrevistaEntrega;
	}

	public LocalDateTime getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConsulta(LocalDateTime dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}
	
	
}
