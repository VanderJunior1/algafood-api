package com.algaworks.algafood.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.Estado;

@Component
public class EstadoModelAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public EstadoDto toModel(Estado estado) {
        return modelMapper.map(estado, EstadoDto.class);
    }
    
    public List<EstadoDto> toCollectionModel(List<Estado> estados) {
        return estados.stream()
                .map(estado -> toModel(estado))
                .collect(Collectors.toList());
    }

}
