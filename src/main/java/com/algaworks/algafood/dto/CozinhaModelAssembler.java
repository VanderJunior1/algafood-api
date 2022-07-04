package com.algaworks.algafood.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.Cozinha;

@Component
public class CozinhaModelAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public CozinhaDto toModel(Cozinha cozinha) {
        return modelMapper.map(cozinha, CozinhaDto.class);
    }
    
    public List<CozinhaDto> toCollectionModel(List<Cozinha> cozinhas) {
        return cozinhas.stream()
                .map(cozinha -> toModel(cozinha))
                .collect(Collectors.toList());
    } 

}
