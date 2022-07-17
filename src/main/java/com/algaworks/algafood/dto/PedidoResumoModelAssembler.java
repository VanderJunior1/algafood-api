package com.algaworks.algafood.dto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.controller.PedidoController;
import com.algaworks.algafood.controller.RestauranteController;
import com.algaworks.algafood.controller.UsuarioController;
import com.algaworks.algafood.domain.Pedido;

@Component
public class PedidoResumoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoResumoDto> {

	@Autowired
	private ModelMapper modelMapper;
	
	public PedidoResumoModelAssembler() {
        super(PedidoController.class, PedidoResumoDto.class);
    }

	@Override
    public PedidoResumoDto toModel(Pedido pedido) {
        PedidoResumoDto pedidoModel = createModelWithId(pedido.getCodigo(), pedido);
        modelMapper.map(pedido, pedidoModel);
        
        pedidoModel.add(WebMvcLinkBuilder.linkTo(PedidoController.class).withRel("pedidos"));
        
        pedidoModel.getRestaurante().add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestauranteController.class)
                .findById(pedido.getRestaurante().getId())).withSelfRel());
        
        pedidoModel.getCliente().add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class)
                .findById(pedido.getCliente().getId())).withSelfRel());
        
        return pedidoModel;
    }
}
