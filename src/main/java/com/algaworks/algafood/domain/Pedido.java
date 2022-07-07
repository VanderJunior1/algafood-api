package com.algaworks.algafood.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import org.hibernate.annotations.CreationTimestamp;

import com.algaworks.algafood.domain.enuns.StatusPedido;
import com.algaworks.algafood.exception.NegocioException;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class Pedido implements Serializable {
	private static final long serialVersionUID = -4648821077374051079L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long Id;
	
	@Column(length = 36, nullable = false)
	private String codigo;

	private BigDecimal subtotal;
    private BigDecimal taxaFrete;
    private BigDecimal valorTotal;

    @Embedded
    private Endereco enderecoEntrega;
    
    @Enumerated(EnumType.STRING)
    private StatusPedido status = StatusPedido.CRIADO;
    
    @CreationTimestamp
    private OffsetDateTime dataCriacao;

    private OffsetDateTime dataConfirmacao;
    private OffsetDateTime dataCancelamento;
    private OffsetDateTime dataEntrega;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private FormaPagamento formaPagamento;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private Restaurante restaurante;
    
    @ManyToOne
    @JoinColumn(name = "usuario_cliente_id", nullable = false)
    private Usuario cliente;
    
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itens = new ArrayList<>();
    
    public void calcularValorTotal() {
    	getItens().forEach(ItemPedido::calcularPrecoTotal);
        
        this.subtotal = getItens().stream()
            .map(item -> item.getPrecoTotal())
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        this.valorTotal = this.subtotal.add(this.taxaFrete);
    }

    public void definirFrete() {
        setTaxaFrete(getRestaurante().getTaxaFrete());
    }

    public void atribuirPedidoAosItens() {
        getItens().forEach(item -> item.setPedido(this));
    }
    
    public void confirmar() {
    	setStatus(StatusPedido.CONFIRMADO);
    	setDataConfirmacao(OffsetDateTime.now());
    }
    
    public void cancelar() {
    	setStatus(StatusPedido.CANCELADO);
    	setDataCancelamento(OffsetDateTime.now());
    }
    
    public void entregar() {
    	setStatus(StatusPedido.ENTREGUE);
    	setDataEntrega(OffsetDateTime.now());
    }
    
    private void setStatus(StatusPedido novoStatus) {
    	if(getStatus().naoPodeAlterarPara(novoStatus)) {
    		 throw new NegocioException(
 	                String.format("Status do pedido %s não pode ser alterado de %s para %s",
 	                        getCodigo(), getStatus().getDescricao(), 
 	                       novoStatus.getDescricao()));
    	}
    	this.status = novoStatus;
    }
    
    @PrePersist
    private void gerarCodigo() {
    	setCodigo(UUID.randomUUID().toString());
    }
}
