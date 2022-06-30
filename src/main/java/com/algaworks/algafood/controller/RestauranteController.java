package com.algaworks.algafood.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.Restaurante;
import com.algaworks.algafood.service.impl.RestauranteServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteServiceImpl restauranteServiceImpl;

	@GetMapping
	public List<Restaurante> listar() {
		log.info("Listando restaurantes");
		return restauranteServiceImpl.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Restaurante> findById(@PathVariable Long id) {
		log.info("Buscando restaurante do id {}", id);
		Restaurante retorno = restauranteServiceImpl.buscar(id);
		if (retorno == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(retorno);
	}

	@PutMapping()
	@Transactional
	public ResponseEntity<Restaurante> atualizar(@RequestBody Restaurante restaurante) {
		log.info("Atualizando restaurante do id {}", restaurante.getId());
		Restaurante restauranteAtual = restauranteServiceImpl.buscar(restaurante.getId());
		if (restauranteAtual == null) {
			return ResponseEntity.notFound().build();
		}

		BeanUtils.copyProperties(restaurante, restauranteAtual, 
				"id", "formasPagamentos", "endereco", "dataCadastro", "produto");
		restauranteServiceImpl.save(restauranteAtual);
		return ResponseEntity.ok(restauranteAtual);
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	@Transactional
	public void remover(@PathVariable Long id) {
		log.info("Removendo restaurante do id {}", id);
		restauranteServiceImpl.deleteById(id);
	}

	@PostMapping
	@Transactional
	public ResponseEntity<Restaurante> adicionar(@RequestBody Restaurante restaurante) {
		log.info("Cadastrando novo restaurante de nome {}", restaurante.getNome());
		Restaurante salvo = restauranteServiceImpl.save(restaurante);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
	}

}
