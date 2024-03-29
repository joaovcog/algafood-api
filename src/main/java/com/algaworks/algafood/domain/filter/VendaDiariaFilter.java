package com.algaworks.algafood.domain.filter;

import java.time.OffsetDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendaDiariaFilter {
	
	private Long codRestaurante;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	private OffsetDateTime dataCriacaoInicio;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	private OffsetDateTime dataCriacaoFim;
	
	public boolean temCodRestaurante() {
		return codRestaurante != null;
	}
	
	public boolean temDataCriacaoInicio() {
		return dataCriacaoInicio != null;
	}
	
	public boolean temDataCriacaoFim() {
		return dataCriacaoFim != null;
	}
	
}
