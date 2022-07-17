package com.algaworks.algafood.dto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.controller.CidadeController;
import com.algaworks.algafood.controller.EstadoController;
import com.algaworks.algafood.domain.Cidade;

@Component
public class CidadeModelAssembler extends RepresentationModelAssemblerSupport<Cidade, CidadeDto> {

	public CidadeModelAssembler() {
		super(CidadeController.class, CidadeDto.class);
	}

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CidadeDto toModel(Cidade cidade) {
		CidadeDto cidadeDto = createModelWithId(cidade.getId(), cidade);
		
		modelMapper.map(cidade, cidadeDto);
		
		cidadeDto.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CidadeController.class)
				.listar()).withRel("cidades"));

		cidadeDto.getEstado().add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EstadoController.class)
				.findById(cidadeDto.getEstado().getId())).withSelfRel());
		return cidadeDto;
	}
	
	@Override
	public CollectionModel<CidadeDto> toCollectionModel(Iterable<? extends Cidade> entities) {
		return super.toCollectionModel(entities)
				.add(WebMvcLinkBuilder.linkTo(CidadeController.class).withSelfRel());
	}

}
