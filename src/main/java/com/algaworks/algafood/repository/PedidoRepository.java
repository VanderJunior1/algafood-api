package com.algaworks.algafood.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	@Query("from Pedido p join fetch p.cliente "
			  + "join fetch p.restaurante "
			  + "left join fetch p.itens i "
			  + "left join i.produto pi "
			  + "left join fetch p.enderecoEntrega.cidade "
			  + "left join fetch p.enderecoEntrega.cidade.estado "
			  + "join fetch p.restaurante.cozinha "
			  + "join fetch p.formaPagamento")
	List<Pedido> findAll();
	
	Optional<Pedido> findByCodigo(String codigo);
}
