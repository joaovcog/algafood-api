package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.assembler.RestauranteDtoAssembler;
import com.algaworks.algafood.api.dto.input.RestauranteInputDto;
import com.algaworks.algafood.api.dto.output.RestauranteOutputDto;
import com.algaworks.algafood.domain.exception.CidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.RestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired
	private RestauranteDtoAssembler restauranteDtoAssembler;
	
	@GetMapping
	public List<RestauranteOutputDto> listar() {
		return restauranteDtoAssembler.toCollectionOutputDtoFromDomainEntity(restauranteRepository.findAll(), RestauranteOutputDto.class);
	}

	@GetMapping("/{codigo}")
	public RestauranteOutputDto buscar(@PathVariable Long codigo) {
		Restaurante restaurante = restauranteService.buscarOuFalhar(codigo);
		
		return restauranteDtoAssembler.toOutputDtoFromDomainEntity(restaurante, RestauranteOutputDto.class);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RestauranteOutputDto adicionar(@RequestBody @Valid RestauranteInputDto restauranteInput) {
		try {
			Restaurante restaurante = restauranteDtoAssembler.toDomainObjectFromInputDto(restauranteInput, Restaurante.class);
			
			return restauranteDtoAssembler.toOutputDtoFromDomainEntity(restauranteService.salvar(restaurante), RestauranteOutputDto.class);
		} catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@PutMapping("/{codigo}")
	public RestauranteOutputDto atualizar(@PathVariable Long codigo, @RequestBody @Valid RestauranteInputDto restauranteInput) {
		//Restaurante restaurante = restauranteDtoAssembler.toDomainObjectFromInputDto(restauranteInput, Restaurante.class);
		Restaurante restauranteAtual = restauranteService.buscarOuFalhar(codigo);

		restauranteDtoAssembler.copyFromInputDtoToDomainObject(restauranteInput, restauranteAtual);
		
		//BeanUtils.copyProperties(restaurante, restauranteAtual, "codigo", "formasPagamentos", "endereco", "dataCadastro", "produtos");
		
		try {
			return restauranteDtoAssembler.toOutputDtoFromDomainEntity(restauranteService.salvar(restauranteAtual), RestauranteOutputDto.class);
		} catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@PutMapping("/{codRestaurante}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void ativar(@PathVariable Long codRestaurante) {
		restauranteService.ativar(codRestaurante);
	}
	
	@DeleteMapping("/{codRestaurante}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void inativar(@PathVariable Long codRestaurante) {
		restauranteService.inativar(codRestaurante);
	}

	/*
	@PatchMapping("/{codigo}")
	public Restaurante atualizarParcial(@PathVariable Long codigo, @RequestBody Map<String, Object> campos, HttpServletRequest request) {
		Restaurante restauranteAtual = restauranteService.buscarOuFalhar(codigo);

		merge(campos, restauranteAtual, request);
		validate(restauranteAtual, "restaurante");

		return atualizar(codigo, restauranteAtual);
	}

	private void validate(Restaurante restaurante, String objectName) {
		BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(restaurante, objectName);
		
		validator.validate(restaurante, bindingResult);
		
		if (bindingResult.hasErrors()) {
			throw new ValidacaoException(bindingResult);
		}
	}

	private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino, HttpServletRequest request) {
		ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request);
		
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

			Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

			dadosOrigem.forEach((chave, valor) -> {
				Field field = ReflectionUtils.findField(Restaurante.class, chave);
				field.setAccessible(true);

				Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

				ReflectionUtils.setField(field, restauranteDestino, novoValor);
			});
		} catch (IllegalArgumentException ex) {
			Throwable rootCause = ExceptionUtils.getRootCause(ex);
			throw new HttpMessageNotReadableException(ex.getMessage(), rootCause, serverHttpRequest);
		}
	}
	*/
}
