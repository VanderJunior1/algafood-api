package com.algaworks.algafood.dto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.controller.EstadoController;
import com.algaworks.algafood.domain.Estado;

@Component
public class EstadoModelAssembler extends RepresentationModelAssemblerSupport<Estado, EstadoDto> {

	@Autowired
	private ModelMapper modelMapper;

	public EstadoModelAssembler() {
        super(EstadoController.class, EstadoDto.class);
    }
    
    @Override
    public EstadoDto toModel(Estado estado) {
        EstadoDto estadoDto = createModelWithId(estado.getId(), estado);
        modelMapper.map(estado, estadoDto);
        estadoDto.add(WebMvcLinkBuilder.linkTo(EstadoController.class).withRel("estados"));
        
        return estadoDto;
    }
    
    @Override
    public CollectionModel<EstadoDto> toCollectionModel(Iterable<? extends Estado> entities) {
        return super.toCollectionModel(entities)
            .add(WebMvcLinkBuilder.linkTo(EstadoController.class).withSelfRel());
    }  

}
