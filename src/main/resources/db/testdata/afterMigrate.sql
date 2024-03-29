set foreign_key_checks = 0;

lock tables cidade write, cozinha write, estado write, forma_pagamento write,
grupo write, grupo_permissao write, permissao write,
produto write, restaurante write, restaurante_forma_pagamento write,
usuario write, usuario_grupo write,
pedido write, item_pedido write, foto_produto write, oauth_client_details write; 

delete from cidade;
delete from cozinha;
delete from estado;
delete from forma_pagamento;
delete from grupo;
delete from grupo_permissao;
delete from permissao;
delete from produto;
delete from restaurante;
delete from restaurante_forma_pagamento;
delete from usuario;
delete from usuario_grupo;
delete from pedido;
delete from item_pedido;
delete from foto_produto;
delete from oauth_client_details;

set foreign_key_checks = 1;

alter table cidade auto_increment = 1;
alter table cozinha auto_increment = 1;
alter table estado auto_increment = 1;
alter table forma_pagamento auto_increment = 1;
alter table grupo auto_increment = 1;
alter table permissao auto_increment = 1;
alter table produto auto_increment = 1;
alter table restaurante auto_increment = 1;
alter table usuario auto_increment = 1;
alter table pedido auto_increment = 1;
alter table item_pedido auto_increment = 1;

insert into cozinha (id, nome) values (1, 'Tailandesa');
insert into cozinha (id, nome) values (2, 'Indiana');
insert into cozinha (id, nome) values (3, 'Argentina');
insert into cozinha (id, nome) values (4, 'Brasileira');

insert into estado (id, nome) values (1, 'Minas Gerais');
insert into estado (id, nome) values (2, 'São Paulo');
insert into estado (id, nome) values (3, 'Ceará');

insert into cidade (id, nome, estado_id) values (1, 'Uberlândia', 1);
insert into cidade (id, nome, estado_id) values (2, 'Belo Horizonte', 1);
insert into cidade (id, nome, estado_id) values (3, 'São Paulo', 2);
insert into cidade (id, nome, estado_id) values (4, 'Campinas', 2);
insert into cidade (id, nome, estado_id) values (5, 'Fortaleza', 3);

insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro, ativo, aberto) values (1, 'Thai Gourmet', 10, 1, utc_timestamp, utc_timestamp, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro', true, true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro, ativo, aberto) values (2, 'Thai Delivery', 10, 1, utc_timestamp, utc_timestamp, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro', true, true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro, ativo, aberto) values (3, 'Tuk Tuk Comida Indiana', 10, 1, utc_timestamp, utc_timestamp, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro', true, true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro, ativo, aberto) values (4, 'Java Steakhouse', 10, 1, utc_timestamp, utc_timestamp, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro', true, true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro, ativo, aberto) values (5, 'Lanchonete do Tio Sam', 10, 1, utc_timestamp, utc_timestamp, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro', true, true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro, ativo, aberto) values (6, 'Bar da Maria', 10, 1, utc_timestamp, utc_timestamp, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro', true, true);

insert into forma_pagamento (id, descricao) values (1, 'Cartão de crédito');
insert into forma_pagamento (id, descricao) values (2, 'Cartão de débito');
insert into forma_pagamento (id, descricao) values (3, 'Dinheiro');

insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissao (id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');
insert into permissao (id, nome, descricao) values (3, 'CONSULTAR_FORMAS_PAGAMENTO', 'Permite consultar formas de pagamento');
insert into permissao (id, nome, descricao) values (4, 'EDITAR_FORMAS_PAGAMENTO', 'Permite criar ou editar formas de pagamento');
insert into permissao (id, nome, descricao) values (5, 'CONSULTAR_CIDADES', 'Permite consultar cidades');
insert into permissao (id, nome, descricao) values (6, 'EDITAR_CIDADES', 'Permite criar ou editar cidades');
insert into permissao (id, nome, descricao) values (7, 'CONSULTAR_ESTADOS', 'Permite consultar estados');
insert into permissao (id, nome, descricao) values (8, 'EDITAR_ESTADOS', 'Permite criar ou editar estados');
insert into permissao (id, nome, descricao) values (9, 'CONSULTAR_USUARIOS', 'Permite consultar usuários');
insert into permissao (id, nome, descricao) values (10, 'EDITAR_USUARIOS', 'Permite criar ou editar usuários');
insert into permissao (id, nome, descricao) values (11, 'CONSULTAR_RESTAURANTES', 'Permite consultar restaurantes');
insert into permissao (id, nome, descricao) values (12, 'EDITAR_RESTAURANTES', 'Permite criar, editar ou gerenciar restaurantes');
insert into permissao (id, nome, descricao) values (13, 'CONSULTAR_PRODUTOS', 'Permite consultar produtos');
insert into permissao (id, nome, descricao) values (14, 'EDITAR_PRODUTOS', 'Permite criar ou editar produtos');
insert into permissao (id, nome, descricao) values (15, 'CONSULTAR_PEDIDOS', 'Permite consultar pedidos');
insert into permissao (id, nome, descricao) values (16, 'GERENCIAR_PEDIDOS', 'Permite gerenciar pedidos');
insert into permissao (id, nome, descricao) values (17, 'GERAR_RELATORIOS', 'Permite gerar relatórios');

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3), (4, 1), (4, 2), (5, 1), (5, 2), (6, 3);

insert into produto (id, nome, descicao, preco, ativo, restaurante_id) values (1, 'Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, 1, 1);
insert into produto (id, nome, descicao, preco, ativo, restaurante_id) values (2, 'Camarão tailandês', '16 camarões grandes ao molho picante', 110, 1, 1);
insert into produto (id, nome, descicao, preco, ativo, restaurante_id) values (3, 'Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina', 87.20, 1, 2);
insert into produto (id, nome, descicao, preco, ativo, restaurante_id) values (4, 'Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, 1, 3);
insert into produto (id, nome, descicao, preco, ativo, restaurante_id) values (5, 'Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, 1, 3);
insert into produto (id, nome, descicao, preco, ativo, restaurante_id) values (6, 'Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura', 79, 1, 4);
insert into produto (id, nome, descicao, preco, ativo, restaurante_id) values (7, 'T-Bone', 'Corte muito saboroso, com um osso em formato de T', 89, 1, 4);
insert into produto (id, nome, descicao, preco, ativo, restaurante_id) values (8, 'Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada', 19, 1, 5);
insert into produto (id, nome, descicao, preco, ativo, restaurante_id) values (9, 'Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, 1, 6);

insert into grupo (id, nome) values (1, 'Gerente'), (2, 'Vendedor'), (3, 'Secretária'), (4, 'Cadastrador');

# Adiciona todas as permissoes no grupo do gerente
insert into grupo_permissao (grupo_id, permissao_id)
select 1, id from permissao;

# Adiciona permissoes no grupo do vendedor
insert into grupo_permissao (grupo_id, permissao_id)
select 2, id from permissao where nome like 'CONSULTAR_%';

insert into grupo_permissao (grupo_id, permissao_id) values (2, 14);

# Adiciona permissoes no grupo do auxiliar
insert into grupo_permissao (grupo_id, permissao_id)
select 3, id from permissao where nome like 'CONSULTAR_%';

# Adiciona permissoes no grupo cadastrador
insert into grupo_permissao (grupo_id, permissao_id)
select 4, id from permissao where nome like '%_RESTAURANTES' or nome like '%_PRODUTOS';

insert into usuario (id, nome, email, senha, data_cadastro) values
(1, 'João da Silva', 'joao.ger@algafood.com', '$2a$12$xTeIAppqXRM57NgAsyu1aeT57SMzPqGvG/QWXL8ii2fOH1hBMAXSq', utc_timestamp),
(2, 'Maria Joaquina', 'maria.vnd@algafood.com', '$2a$12$xTeIAppqXRM57NgAsyu1aeT57SMzPqGvG/QWXL8ii2fOH1hBMAXSq', utc_timestamp),
(3, 'José Souza', 'jose.aux@algafood.com', '$2a$12$xTeIAppqXRM57NgAsyu1aeT57SMzPqGvG/QWXL8ii2fOH1hBMAXSq', utc_timestamp),
(4, 'Sebastião Martins', 'sebastiao.cad@algafood.com', '$2a$12$xTeIAppqXRM57NgAsyu1aeT57SMzPqGvG/QWXL8ii2fOH1hBMAXSq', utc_timestamp); 

insert into usuario_grupo (usuario_id, grupo_id) values (1, 1), (1, 2), (2, 2), (3,3), (4,4);

insert into pedido (id, codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, 
	    endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
	    status, data_criacao, subtotal, taxa_frete, valor_total)
	values (1, '9c70a176-fdf9-11ec-848c-641c679e0b8d', 1, 1, 1, 1, '38400-000', 'Rua Floriano Peixoto', '500', 'Apto 801', 'Brasil',
	'CRIADO', utc_timestamp, 298.90, 10, 308.90);

	insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
	values (1, 1, 1, 1, 78.9, 78.9, null);

	insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
	values (2, 1, 2, 2, 110, 220, 'Menos picante, por favor');


	insert into pedido (id, codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, 
	        endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
	        status, data_criacao, subtotal, taxa_frete, valor_total)
	values (2, 'c0ffa12a-c33d-44e8-b07e-64371e6613ce', 4, 1, 2, 1, '38400-111', 'Rua Acre', '300', 'Casa 2', 'Centro',
	'CRIADO', utc_timestamp, 79, 0, 79);

	insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
	values (3, 2, 6, 1, 79, 79, 'Ao ponto');

	insert into oauth_client_details (
			  client_id, resource_ids, client_secret, 
			  scope, authorized_grant_types, web_server_redirect_uri, authorities,
			  access_token_validity, refresh_token_validity, autoapprove
			)
			values (
			  'algafood-web', null, '$2a$12$8HQ0.YVVi8LPFISnR8P9.e/oK5u/bw1YPNoICIUkx9LCPjXd3NwIa',
			  'READ,WRITE', 'password', null, null,
			  60 * 60 * 6, 60 * 24 * 60 * 60, null
			);

			insert into oauth_client_details (
			  client_id, resource_ids, client_secret, 
			  scope, authorized_grant_types, web_server_redirect_uri, authorities,
			  access_token_validity, refresh_token_validity, autoapprove
			)
			values (
			  'foodanalytics', null, '$2y$12$fahbH37S2pyk1RPuIHKP.earzFmgAJJGo26rE.59vf4wwiiTKHnzO',
			  'READ,WRITE', 'authorization_code', 'http://www.foodanalytics.local:8082', null,
			  null, null, null
			);

			insert into oauth_client_details (
			  client_id, resource_ids, client_secret, 
			  scope, authorized_grant_types, web_server_redirect_uri, authorities,
			  access_token_validity, refresh_token_validity, autoapprove
			)
			values (
			  'faturamento', null, '$2y$12$fHixriC7yXX/i1/CmpnGH.RFyK/l5YapLCFOEbIktONjE8ZDykSnu',
			  'READ,WRITE', 'client_credentials', null, 'CONSULTAR_PEDIDOS,GERAR_RELATORIOS',
			  null, null, null
			);

 unlock tables;