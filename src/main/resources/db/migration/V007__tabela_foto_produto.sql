create table foto_produto(
produto_id bigint not null, nome_arquivo varchar(150) not null, descricao varchar(150), 
content_type varchar(80) not null, tamanho int not NULL, 
primary key(produto_id), 
CONSTRAINT fk_foto_produto foreign KEY (produto_id) references produto(id)) engine=INNODB;

