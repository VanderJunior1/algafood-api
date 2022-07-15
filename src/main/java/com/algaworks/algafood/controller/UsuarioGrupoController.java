package com.algaworks.algafood.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.controller.openapi.UsuarioGrupoControllerOpenApi;
import com.algaworks.algafood.domain.Usuario;
import com.algaworks.algafood.dto.GrupoDto;
import com.algaworks.algafood.dto.GrupoModelAssembler;
import com.algaworks.algafood.service.impl.UsuarioServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "/usuarios/{usuarioId}/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioGrupoController implements UsuarioGrupoControllerOpenApi{

	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;

	@Autowired
	private GrupoModelAssembler grupoModelAssembler;

	@GetMapping
	public List<GrupoDto> listar(@PathVariable Long usuarioId) {
		log.info("Buscando lista de permissoes do usuario de id {}", usuarioId);
		Usuario usuario = usuarioServiceImpl.buscar(usuarioId);
		return grupoModelAssembler.toCollectionModel(usuario.getGrupos());
	}

	@DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
		log.info("Desassociando permissao com id: {} para o grupo com id: {}", usuarioId, grupoId);
		usuarioServiceImpl.desassociarGrupo(usuarioId, grupoId);
    }
    
    @PutMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
    	log.info("Associando permissao com id: {} para o usuario com id: {}", usuarioId, grupoId);
    	usuarioServiceImpl.associarGrupo(usuarioId, grupoId);
    }     

}
