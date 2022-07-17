package com.algaworks.algafood.dto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.controller.CozinhaController;
import com.algaworks.algafood.domain.Cozinha;

@Component
public class CozinhaModelAssembler extends RepresentationModelAssemblerSupport<Cozinha, CozinhaDto> {

	public CozinhaModelAssembler() {
		super(CozinhaController.class, CozinhaDto.class);
	}

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CozinhaDto toModel(Cozinha cozinha) {
		CozinhaDto cozinhaDto = createModelWithId(cozinha.getId(), cozinha);
		modelMapper.map(cozinha, cozinhaDto);
		cozinhaDto.add(WebMvcLinkBuilder.linkTo(CozinhaController.class).withRel("cozinhas"));

		return cozinhaDto;
	}
	
	@Override
	public CollectionModel<CozinhaDto> toCollectionModel(Iterable<? extends Cozinha> entities) {
		return super.toCollectionModel(entities)
				.add(WebMvcLinkBuilder.linkTo(CozinhaController.class).withSelfRel());
	}

}
