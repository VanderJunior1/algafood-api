package com.algaworks.algafood.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

import com.algaworks.algafood.domain.Usuario;
import com.algaworks.algafood.dto.UsuarioDto;
import com.algaworks.algafood.dto.UsuarioInputDisassembler;
import com.algaworks.algafood.dto.UsuarioModelAssembler;
import com.algaworks.algafood.dto.input.SenhaInput;
import com.algaworks.algafood.dto.input.UsuarioInput;
import com.algaworks.algafood.service.impl.UsuarioServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;
	
	@Autowired
	private UsuarioModelAssembler usuarioModelAssembler;

	@Autowired
	private UsuarioInputDisassembler usuarioInputDisassembler;  

	@GetMapping
	public Page<UsuarioDto> listar(@PageableDefault(size = 10) Pageable pageable) {
		log.info("Listando usuarios");	
		Page<Usuario> cozinhasPage = usuarioServiceImpl.findAll(pageable);
		List<UsuarioDto> usuarioDtos =  usuarioModelAssembler
				.toCollectionModel(usuarioServiceImpl.findAll());
		
		Page<UsuarioDto> usuarioPageDtos = new PageImpl<>(usuarioDtos, pageable, 
				cozinhasPage.getTotalElements());
		
		return usuarioPageDtos;
	}

	@GetMapping("/{id}")
	public UsuarioDto findById(@PathVariable Long id) {
		log.info("Buscando usuario de id {}", id);	
		return usuarioModelAssembler.toModel(usuarioServiceImpl.buscar(id));	
	}

	@PutMapping("/{id}")
	public UsuarioDto atualizar(@PathVariable Long id, @RequestBody @Valid UsuarioInput usuarioInput) {
		log.info("Atualizando usuario de id {}", id);	
		Usuario usuarioAtual = usuarioServiceImpl.buscar(id);

		usuarioInputDisassembler.copyToDomainObject(usuarioInput, usuarioAtual);
		usuarioAtual = usuarioServiceImpl.save(usuarioAtual);
	    return usuarioModelAssembler.toModel(usuarioAtual);
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void remover(@PathVariable Long id) {
		log.info("Removendo usuario do id {}", id);
		usuarioServiceImpl.deleteById(id);
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public UsuarioDto adicionar(@RequestBody @Valid UsuarioInput usuarioInput) {
		log.info("Cadastrando novo usuario de nome {}", usuarioInput.getNome());
		 Usuario usuario = usuarioInputDisassembler.toDomainObject(usuarioInput);
		 usuario = usuarioServiceImpl.save(usuario);
		 return usuarioModelAssembler.toModel(usuario);
	}
	
	@PutMapping("/{usuarioId}/senha")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void alterarSenha(@PathVariable Long usuarioId, @RequestBody @Valid SenhaInput senha) {
		usuarioServiceImpl.alterarSenha(usuarioId, senha.getSenhaAtual(), senha.getNovaSenha());
	} 

}
