package com.algaworks.algafood;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.algaworks.algafood.domain.Cozinha;
import com.algaworks.algafood.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.exception.EntidadeEmUsoException;
import com.algaworks.algafood.service.impl.CozinhaServiceImpl;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CadastroCozinhaIntegrationTests {

	@Autowired
	private CozinhaServiceImpl cozinhaService;

	@Test
	public void whenCadastroCozinhaCorreto_ThenDeveAtribuirId() {

		// Cenário
		var novaCozinha = new Cozinha();
		novaCozinha.setNome("Chineza");

		// Ação
		var cozinhaSalva = cozinhaService.save(novaCozinha);

		// Validação
		assertThat(cozinhaSalva).isNotNull();
		assertThat(cozinhaSalva.getId()).isNotNull();
		assertThat(cozinhaSalva.getNome()).isNotNull();

	}

	@Test
	public void BuscarUnicoRegistro() {
		
		// Cenário
		var novaCozinha = new Cozinha();
		

		// Ação
		novaCozinha = cozinhaService.buscar(3L);

		// Validação
		assertThat(novaCozinha.getId()).isNotNull();
	}
	
	@Test
	public void BuscarTodos() {

		// Cenário
		List<Cozinha> lista = null;

		// Ação
		lista = cozinhaService.findAll();

		// Validação
		assertThat(lista).size().isGreaterThan(1);

	}

	@Test
	public void whenCadastroCozinhaInCorreto_ThenDeveLancarExcesso() {

		// Cenário
		Cozinha cadastroCozinha = new Cozinha();
		cadastroCozinha.setNome(null);

		// Ação
		ConstraintViolationException erroEsperado = Assertions.assertThrows(ConstraintViolationException.class, () -> {
			cozinhaService.save(cadastroCozinha);
		});

		// Validação
		assertThat(erroEsperado).isNotNull();
	}

	@Test
	public void deveFalhar_QuandoExcluirCozinhaEmUso() {

		// Ação
		EntidadeEmUsoException erroEsperado = 
				Assertions.assertThrows(EntidadeEmUsoException.class, () -> {
			cozinhaService.deleteById(3L);
		});

		// Validação
		assertThat(erroEsperado).isNotNull();
	}

	@Test
	public void deveFalhar_QuandoExcluirCozinhaInexistente() {

		// Ação
		CozinhaNaoEncontradaException erroEsperado = 
				Assertions.assertThrows(CozinhaNaoEncontradaException.class,
				() -> {
					cozinhaService.deleteById(100000L);
				});

		// Validação
		assertThat(erroEsperado).isNotNull();
	}

}
