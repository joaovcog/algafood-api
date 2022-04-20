package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.filter.VendaDiariaFilter;
import com.algaworks.algafood.domain.model.dto.VendaDiaria;
import com.algaworks.algafood.domain.repository.EstatisticaVendaRepository;

@RestController
@RequestMapping(path = "/estatisticas")
public class EstatisticasController {
	
	@Autowired
	private EstatisticaVendaRepository estatisticaVendaRepository;
	
	@GetMapping("/vendas-diarias")
	public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro) {
		return estatisticaVendaRepository.consultarVendasDiarias(filtro);
	}
	
}
