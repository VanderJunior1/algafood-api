package com.algaworks.algafood.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.Grupo;
import com.algaworks.algafood.dto.PermissaoDto;
import com.algaworks.algafood.dto.PermissaoModelAssembler;
import com.algaworks.algafood.service.impl.GrupoServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/grupos/{grupoId}/permissoes")
public class GrupoPermissaoController {

	@Autowired
	private GrupoServiceImpl grupoServiceImpl;

	@Autowired
	private PermissaoModelAssembler permissaoModelAssembler;

	@GetMapping
	public List<PermissaoDto> listar(@RequestParam Long grupoId) {
		log.info("Buscando lista de permissoes de id {}", grupoId);
		Grupo grupo = grupoServiceImpl.buscar(grupoId);
		return permissaoModelAssembler.toCollectionModel(grupo.getPermissoes());
	}

	@DeleteMapping("/{permissaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desassociar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		log.info("Desassociando permissao com id: {} para o grupo com id: {}", permissaoId, grupoId);
		grupoServiceImpl.desassociarPermissao(grupoId, permissaoId);
	}

	@PutMapping("/{permissaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		log.info("Associando permissao com id: {} para o grupo com id: {}", permissaoId, grupoId);
		grupoServiceImpl.associarPermissao(grupoId, permissaoId);
	}

}
