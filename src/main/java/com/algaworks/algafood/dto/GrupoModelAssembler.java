package com.algaworks.algafood.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.Grupo;

@Component
public class GrupoModelAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public GrupoDto toModel(Grupo grupo) {
		return modelMapper.map(grupo, GrupoDto.class);
	}

	public List<GrupoDto> toCollectionModel(List<Grupo> grupos) {
		return grupos.stream().map(grupo -> toModel(grupo)).collect(Collectors.toList());
	}
}
