create table formas_pagamentos (
	codigo bigint not null auto_increment, 
	descricao varchar(60) not null, 
	primary key (codigo)
) engine=InnoDB default charset=utf8mb4;

create table grupos (
	codigo bigint not null auto_increment, 
	nome varchar(60) not null, 

	primary key (codigo)
) engine=InnoDB default charset=utf8mb4;

create table grupos_permissoes (
	cod_grupo bigint not null, 
	cod_permissao bigint not null, 
	
	primary key (cod_grupo, cod_permissao)
) engine=InnoDB default charset=utf8mb4;

create table permissoes (
	codigo bigint not null auto_increment, 
	descricao varchar(80) not null, 
	nome varchar(60) not null, 
	
	primary key (codigo)
) engine=InnoDB default charset=utf8mb4;

create table produtos (
	codigo bigint not null auto_increment, 
	descricao text not null, 
	nome varchar(80) not null, 
	preco decimal(10, 2) not null, 
	cod_restaurante bigint not null, 
	ativo bit not null, 
	
	primary key (codigo)
) engine=InnoDB default charset=utf8mb4;

create table restaurantes (
	codigo bigint not null auto_increment,  
	nome varchar(30) not null, 
	taxa_frete decimal(19,2) not null, 
	cod_cozinha bigint not null,
	data_atualizacao datetime not null, 
	data_cadastro datetime not null,
	
	endereco_logradouro varchar(100), 
	endereco_numero varchar(20),
	endereco_cep varchar(9), 
	endereco_complemento varchar(60), 
	endereco_bairro varchar(60), 
	endereco_cod_cidade bigint,  
	
	primary key (codigo)
) engine=InnoDB default charset=utf8mb4;

create table restaurantes_formas_pagamentos (
	cod_restaurante bigint not null, 
	cod_forma_pagamento bigint not null, 
	
	primary key (cod_restaurante, cod_forma_pagamento)
) engine=InnoDB default charset=utf8mb4;

create table usuarios (
	codigo bigint not null auto_increment, 
	nome varchar(80) not null, 
	email varchar(255) not null, 
	senha varchar(255) not null, 
	data_cadastro datetime not null, 
	
	primary key (codigo)
) engine=InnoDB default charset=utf8mb4;

create table usuarios_grupos (
	cod_usuario bigint not null, 
	cod_grupo bigint not null, 
	
	primary key (cod_usuario, cod_grupo)
) engine=InnoDB default charset=utf8mb4;


alter table grupos_permissoes add constraint fk_grupos_permissoes_permissoes 
foreign key (cod_permissao) references permissoes (codigo);

alter table grupos_permissoes add constraint fk_grupos_permissoes_grupos 
foreign key (cod_grupo) references grupos (codigo);

alter table produtos add constraint fk_produtos_restaurantes 
foreign key (cod_restaurante) references restaurantes (codigo);

alter table restaurantes add constraint fk_restaurantes_cozinhas 
foreign key (cod_cozinha) references cozinhas (codigo);

alter table restaurantes add constraint fk_restaurantes_cidades 
foreign key (endereco_cod_cidade) references cidades (codigo);

alter table restaurantes_formas_pagamentos add constraint fk_rest_formas_pgtos_formas_pgtos 
foreign key (cod_forma_pagamento) references formas_pagamentos (codigo);

alter table restaurantes_formas_pagamentos add constraint fk_rest_formas_pgtos_restaurantes 
foreign key (cod_restaurante) references restaurantes (codigo);

alter table usuarios_grupos add constraint fk_usuarios_grupos_grupos 
foreign key (cod_grupo) references grupos (codigo);

alter table usuarios_grupos add constraint fk_usuarios_grupos_usuarios 
foreign key (cod_usuario) references usuarios (codigo);