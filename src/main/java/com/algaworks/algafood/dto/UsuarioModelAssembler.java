package com.algaworks.algafood.dto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.controller.UsuarioController;
import com.algaworks.algafood.controller.UsuarioGrupoController;
import com.algaworks.algafood.domain.Usuario;

@Component
public class UsuarioModelAssembler extends RepresentationModelAssemblerSupport<Usuario, UsuarioDto> {

	@Autowired
	private ModelMapper modelMapper;
	
	public UsuarioModelAssembler() {
		super(UsuarioController.class, UsuarioDto.class);
	}

	@Override
	public UsuarioDto toModel(Usuario usuario) {
		UsuarioDto usuarioDto = createModelWithId(usuario.getId(), usuario);
		modelMapper.map(usuario, usuarioDto);
		
		usuarioDto.add(WebMvcLinkBuilder.linkTo(UsuarioController.class).withRel("usuarios"));

		usuarioDto.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioGrupoController.class)
                .listar(usuario.getId())).withRel("grupos-usuario"));
		return usuarioDto;
	}
	
	@Override
	public CollectionModel<UsuarioDto> toCollectionModel(Iterable<? extends Usuario> entities) {
		return super.toCollectionModel(entities)
				.add(WebMvcLinkBuilder.linkTo(UsuarioController.class).withSelfRel());
	}
}
