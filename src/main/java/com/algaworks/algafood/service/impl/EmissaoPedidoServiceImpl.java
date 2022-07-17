package com.algaworks.algafood.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.Cidade;
import com.algaworks.algafood.domain.FormaPagamento;
import com.algaworks.algafood.domain.Pedido;
import com.algaworks.algafood.domain.Produto;
import com.algaworks.algafood.domain.Restaurante;
import com.algaworks.algafood.domain.Usuario;
import com.algaworks.algafood.exception.NegocioException;
import com.algaworks.algafood.exception.PedidoNaoEncontradoException;
import com.algaworks.algafood.repository.PedidoRepository;
import com.algaworks.algafood.service.EmissaoPedidoService;

@Service
public class EmissaoPedidoServiceImpl implements EmissaoPedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private CidadeServiceImpl cidadeServiceImpl;
	
	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;
	
	@Autowired
	private RestauranteServiceImpl restauranteServiceImpl;
	
	@Autowired
	private FormaPagamentoServiceImpl formaPagamentoServiceImpl;
	
	@Autowired
	private ProdutoServiceImpl produtoServiceImpl;
	
	@Override
	public List<Pedido> findAll() {
		return pedidoRepository.findAll();
	}
	
	@Override
	public Page<Pedido> findAll(Pageable pageable) {
		return pedidoRepository.findAll(pageable);
	}

	@Override
	public Pedido buscar(String codigoPedido) {
		return pedidoRepository	.findByCodigo(codigoPedido).orElseThrow(
				() -> new PedidoNaoEncontradoException(codigoPedido));
	}
	
	@Override
	@Transactional
	public Pedido emitir(Pedido pedido) {
	    validarPedido(pedido);
	    validarItens(pedido);

	    pedido.setTaxaFrete(pedido.getRestaurante().getTaxaFrete());
	    pedido.calcularValorTotal();

	    return pedidoRepository.save(pedido);
	}

	private void validarPedido(Pedido pedido) {
	    Cidade cidade = cidadeServiceImpl.buscar(pedido.getEnderecoEntrega().getCidade().getId());
	    Usuario cliente = usuarioServiceImpl.buscar(pedido.getCliente().getId());
	    Restaurante restaurante = restauranteServiceImpl.buscar(pedido.getRestaurante().getId());
	    FormaPagamento formaPagamento = formaPagamentoServiceImpl.buscar(pedido.getFormaPagamento().getId());

	    pedido.getEnderecoEntrega().setCidade(cidade);
	    pedido.setCliente(cliente);
	    pedido.setRestaurante(restaurante);
	    pedido.setFormaPagamento(formaPagamento);
	    
	    if (restaurante.naoAceitaFormaPagamento(formaPagamento)) {
	        throw new NegocioException(String.format("Forma de pagamento '%s' não é aceita por esse restaurante.",
	                formaPagamento.getDescricao()));
	    }
	}

	private void validarItens(Pedido pedido) {
	    pedido.getItens().forEach(item -> {
	        Produto produto = produtoServiceImpl.buscar(
	                pedido.getRestaurante().getId(), item.getProduto().getId());
	        
	        item.setPedido(pedido);
	        item.setProduto(produto);
	        item.setPrecoUnitario(produto.getPreco());
	    });
	}

}
