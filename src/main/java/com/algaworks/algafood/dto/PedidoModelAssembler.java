package com.algaworks.algafood.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.Pedido;

@Component
public class PedidoModelAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public PedidoDto toModel(Pedido pedido) {
		return modelMapper.map(pedido, PedidoDto.class);
	}

	public List<PedidoDto> toCollectionModel(List<Pedido> pedidos) {
		return pedidos.stream().map(pedido -> toModel(pedido)).collect(Collectors.toList());
	}
}
