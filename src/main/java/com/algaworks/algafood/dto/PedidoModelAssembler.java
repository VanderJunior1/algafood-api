package com.algaworks.algafood.dto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.controller.CidadeController;
import com.algaworks.algafood.controller.FormaPagamentoController;
import com.algaworks.algafood.controller.PedidoController;
import com.algaworks.algafood.controller.RestauranteController;
import com.algaworks.algafood.controller.UsuarioController;
import com.algaworks.algafood.domain.Pedido;

@Component
public class PedidoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoDto> {

	@Autowired
	private ModelMapper modelMapper;
	
	public PedidoModelAssembler() {
        super(PedidoController.class, PedidoDto.class);
	}

	@Override
    public PedidoDto toModel(Pedido pedido) {
        PedidoDto pedidoDto = createModelWithId(pedido.getCodigo(), pedido);
        modelMapper.map(pedido, pedidoDto);
        
        pedidoDto.add(WebMvcLinkBuilder.linkTo(PedidoController.class).withRel("pedidos"));
        
        pedidoDto.getRestaurante().add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestauranteController.class)
                .findById(pedido.getRestaurante().getId())).withSelfRel());
        
        pedidoDto.getCliente().add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class)
                .findById(pedido.getCliente().getId())).withSelfRel());
        
        // Passamos null no segundo argumento, porque é indiferente para a
        // construção da URL do recurso de forma de pagamento
        pedidoDto.getFormaPagamento().add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(FormaPagamentoController.class)
                .findById(pedido.getFormaPagamento().getId())).withSelfRel());
        
        pedidoDto.getEnderecoEntrega().getCidade().add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CidadeController.class)
                .findById(pedido.getEnderecoEntrega().getCidade().getId())).withSelfRel());
        
        
        return pedidoDto;
    }
}
