package com.algaworks.algafood.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PedidoDto {

	@ApiModelProperty(example = "1")
	private String codigo;
	
	@ApiModelProperty(example = "10.00")
	private BigDecimal subtotal;
	
	@ApiModelProperty(example = "5.00")
	private BigDecimal taxaFrete;
	
	@ApiModelProperty(example = "15.00")
	private BigDecimal valorTotal;
	
	@ApiModelProperty(example = "CRIADO")
	private String status;
	
		private OffsetDateTime dataCriacao;
	private OffsetDateTime dataConfirmacao;
	private OffsetDateTime dataEntrega;
	private OffsetDateTime dataCancelamento;
	private RestauranteResumoDto restaurante;
	private UsuarioDto cliente;
	private FormaPagamentoDto formaPagamento;
	private EnderecoDto enderecoEntrega;
	private List<ItemPedidoDto> itens;
}
