package com.algaworks.algafood.dto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.Estado;
import com.algaworks.algafood.dto.input.EstadoInput;

@Component
public class EstadoInputDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Estado toDomainObject(EstadoInput estadoInput) {
        return modelMapper.map(estadoInput, Estado.class);
    }
    
    public void copyToDomainObject(EstadoInput estadoInput, Estado estado) {
        modelMapper.map(estadoInput, estado);
    } 

}
