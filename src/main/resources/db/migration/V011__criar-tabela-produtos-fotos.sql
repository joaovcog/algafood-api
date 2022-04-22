create table produtos_fotos (
	cod_produto bigint not null, 
	nome_arquivo varchar(150) not null, 
	descricao varchar(150), 
	content_type varchar(80) not null, 
	tamanho int not null, 
	
	primary key (cod_produto), 
	constraint fk_produtos_fotos_produtos foreign key (cod_produto) references produtos (codigo)
) engine=InnoDB default charset=utf8mb4;