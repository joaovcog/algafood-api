create table pedidos (
	codigo bigint not null auto_increment, 
	cod_usuario_cliente bigint not null, 
	cod_restaurante bigint not null, 
	cod_forma_pagamento bigint not null, 
	
	endereco_logradouro varchar(100) not null, 
	endereco_numero varchar(20) not null,
	endereco_cep varchar(9) not null, 
	endereco_complemento varchar(60), 
	endereco_bairro varchar(60) not null, 
	endereco_cod_cidade bigint not null, 
	
	subtotal decimal(10, 2) not null, 
	taxa_frete decimal (10, 2) not null, 
	valor_total decimal(10, 2) not null, 
	
	data_criacao datetime not null, 
	data_confirmacao datetime, 
	data_cancelamento datetime, 
	data_entrega datetime, 
	
	status varchar(10) not null,
	
	primary key (codigo), 
	constraint fk_pedidos_usuarios_clientes foreign key (cod_usuario_cliente) references usuarios (codigo), 
	constraint fk_pedidos_restaurantes foreign key (cod_restaurante) references restaurantes (codigo), 
	constraint fk_pedidos_formas_pagamentos foreign key (cod_forma_pagamento) references formas_pagamentos (codigo), 
	constraint fk_pedidos_cidades foreign key (endereco_cod_cidade) references cidades (codigo)
) engine=InnoDB default charset=utf8mb4;

create table itens_pedidos (
	codigo bigint not null auto_increment, 
	cod_pedido bigint not null, 
	cod_produto bigint not null, 
	quantidade smallint(6) not null, 
	preco_unitario decimal(10, 2) not null, 
	preco_total decimal(10, 2) not null, 
	observacao varchar(255), 
	
	primary key (codigo), 
	
	unique key uk_itens_pedidos_produtos (cod_pedido, cod_produto), 
	
	constraint fk_itens_pedidos_pedidos foreign key (cod_pedido) references pedidos (codigo), 
	constraint fk_itens_pedidos_produtos foreign key (cod_produto) references produtos (codigo)
) engine=InnoDB default charset=utf8mb4;